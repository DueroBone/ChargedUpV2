// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Controllers.*;
import frc.robot.autonomous.AutoStartPos1;
import frc.robot.autonomous.AutoStartPos2;
import frc.robot.autonomous.AutoStartPos3;
import frc.robot.autonomous.AutoStartPos4;
import frc.robot.commands.AutoBalanceV2;
import frc.robot.commands.GoTele;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final DriveTrain m_driveTrain = new DriveTrain();

  Compressor c = new Compressor(PneumaticsModuleType.REVPH);
  /*
   * private static Command autoDriveStraightGyroCommand;
   * private static Command autoDriveStraightCommand;
   * private static Command autoDriveUnitsCommand;
   * private static Command autoDriveSpinCommand;
   * private static Command autoDriveTurnCommand;
   */
  private static Command autoStartPos1Command;
  private static Command autoStartPos2Command;
  private static Command autoStartPos3Command;
  private static Command autoStartPos4Command;
  public static boolean inCompetition = false;
  public static boolean safteyEnabled = true;
  public static String allianceColor;
  public static int startPosition;
  static Command AutoBalanceCommand;

  // ** set up autonomous chooser
  SendableChooser<Command> autoChooser = new SendableChooser<Command>();
  static SendableChooser<Boolean> resetOnStartChooser = new SendableChooser<Boolean>();

  public RobotContainer() {

    m_driveTrain.setDefaultCommand(new GoTele(true, true, 0.1, 1, 0.1));
    autoStartPos1Command = new AutoStartPos1(m_driveTrain);
    autoStartPos2Command = new AutoStartPos2(m_driveTrain);
    autoStartPos3Command = new AutoStartPos3(m_driveTrain);
    autoStartPos4Command = new AutoStartPos4(m_driveTrain);
    autoChooser.setDefaultOption("Postion 2 (Gamepiece)", autoStartPos2Command);
    autoChooser.addOption("Postion 1 (Balance)", autoStartPos1Command);
    autoChooser.addOption("Postion 3 (Both)", autoStartPos3Command);
    autoChooser.addOption("Postion 4 (2 no drive)", autoStartPos4Command);
    autoChooser.addOption("Null", null);
    SmartDashboard.putData("Auto Choices", autoChooser);

    resetOnStartChooser.setDefaultOption("True", true);
    resetOnStartChooser.addOption("False", false);
    SmartDashboard.putData("Reset encoders on start", resetOnStartChooser);

    AutoBalanceCommand = new AutoBalanceV2();

    if (DriverStation.getAlliance() == Alliance.Blue) {
      allianceColor = "blue";
    } else {
      allianceColor = "red";
    }
    startPosition = DriverStation.getLocation();
    if (DriverStation.isFMSAttached()) {
      inCompetition = true;
    } else {
      inCompetition = false;
    }
    System.out.println(
        "start Positon: " + startPosition + " alliance: " + allianceColor + " in Competition: " + inCompetition);
  }

  public static void configureButtonBindings() {
    System.out.println("Assigning Buttons");

    dynamicXbox.A.onTrue(new InstantCommand(() -> Arm.customPosition(-12, 0)))
        .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
        .onFalse(new InstantCommand(() -> Arm.stopArm()));

    dynamicXbox.LeftStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(true)));
    dynamicXbox.RightStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(false)));

    dynamicXbox.Options.onTrue(new InstantCommand(() -> AutoBalanceCommand.schedule()));
    dynamicXbox.Share.onTrue(new InstantCommand(() -> AutoBalanceCommand.cancel()));

    dynamicXbox.RightTrigger.onTrue(new InstantCommand(() -> System.out.println("Hello world")));

    // Bumpers are slow speed

    // Playstation

    dynamicPlaystation.A.onTrue(new InstantCommand(() -> Arm.drivingPosition()))
        .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
        .onFalse(new InstantCommand(() -> Arm.stopArm()));

    dynamicPlaystation.X.onTrue(new InstantCommand(() -> Arm.bottomPosition()))
        .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
        .onFalse(new InstantCommand(() -> Arm.stopArm()));

    dynamicPlaystation.B.onTrue(new InstantCommand(() -> Arm.scoringPosition()))
        .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
        .onFalse(new InstantCommand(() -> Arm.stopArm()));

    dynamicPlaystation.Y.onTrue(new InstantCommand(() -> Arm.startingPosition()))
        .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
        .onFalse(new InstantCommand(() -> Arm.stopArm()));

    dynamicPlaystation.LeftBumper.whileTrue(new InstantCommand(() -> Claw.open()))
        .onFalse(new InstantCommand(() -> Claw.stop()));

    dynamicPlaystation.RightBumper.whileTrue(new InstantCommand(() -> Claw.close()))
        .onFalse(new InstantCommand(() -> Claw.stop()));

    dynamicPlaystation.LeftTrigger.onTrue(new InstantCommand(() -> GoTele.enableArmManual()))
        .onFalse(new InstantCommand(() -> GoTele.disableArmManual()));

    dynamicPlaystation.RightTrigger.onTrue(new InstantCommand(() -> safteyEnabled = false))
        .onTrue(new InstantCommand(() -> System.out.println("SAFTEY LIMITS DISABLED")))
        .onFalse(new InstantCommand(() -> safteyEnabled = true))
        .onFalse(new InstantCommand(() -> System.out.println("SAFTEY LIMITS ENABLED")));

    dynamicPlaystation.Touchpad.onTrue(new InstantCommand(() -> Arm.info.resetEncoders()));

    dynamicPlaystation.Options.onTrue(new InstantCommand(() -> Arm.stopArm()))
        .whileTrue(new InstantCommand(() -> Arm.holdLifter()));
    dynamicPlaystation.Share.onTrue(new InstantCommand(() -> Arm.setLifter(0)));
    // arm movement is in GoTele
  }

  public Command getAutonomousCommand() {
    System.out.println("***getting Autonomous command");
    return autoChooser.getSelected();
  }

  public boolean getResetEncoders() {
    return resetOnStartChooser.getSelected();
  }

  public static void RemapControllers() {
    System.out.print("***Mapping controllers ");
    dynamicXbox.updateController();
    System.out.print(" * ");
    dynamicPlaystation.updateController();
    System.out.print(" * ");
    dynamicJoystick.updateController();
    System.out.print(" * ");
    System.out.println(" * Done***");
  }
}