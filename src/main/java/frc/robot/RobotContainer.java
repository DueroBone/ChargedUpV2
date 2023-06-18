// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
import frc.robot.subsystems.ControllerTracking;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final DriveTrain m_driveTrain = new DriveTrain();

  // Compressor c = new Compressor(PneumaticsModuleType.REVPH);
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
  public static boolean guestAllowed = true;
  public static String allianceColor;
  public static int startPosition;
  static Command AutoBalanceCommand;

  // ** set up autonomous chooser
  SendableChooser<Command> autoChooser = new SendableChooser<Command>();
  static SendableChooser<Boolean> resetOnStartChooser = new SendableChooser<Boolean>();

  public static SendableChooser<ControllerBase> DriveControllerChooser = new SendableChooser<ControllerBase>();
  public static SendableChooser<ControllerBase> SecondControllerChooser = new SendableChooser<ControllerBase>();
  public static SendableChooser<ControllerBase> FullControllerChooser = new SendableChooser<ControllerBase>();
  public static SendableChooser<ControllerBase> GuestControllerChooser = new SendableChooser<ControllerBase>();

  public RobotContainer() {

    m_driveTrain.setDefaultCommand(new GoTele(true, true, 0.15, 1, 0.15));
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

    if (true) {// controller selection (shrinkable)
      // DriveControllerChooser.addOption("None", null);
      DriveControllerChooser.setDefaultOption("Port 0", Controllers.Zero);
      DriveControllerChooser.addOption("Port 1", Controllers.One);
      DriveControllerChooser.addOption("Port 2", Controllers.Two);
      DriveControllerChooser.addOption("Port 3", Controllers.Three);
      DriveControllerChooser.addOption("Port 4", Controllers.Four);
      DriveControllerChooser.addOption("Port 5", Controllers.Five);
      // DriveControllerChooser.addOption("Dynamic Xbox", Controllers.dynamicXbox);
      // DriveControllerChooser.addOption("Dynamic Playstation",
      // Controllers.dynamicPlaystation);
      // DriveControllerChooser.addOption("Dynamic Joystick",
      // Controllers.dynamicJoystick);
      SmartDashboard.putData("Driving controller", DriveControllerChooser);

      // SecondControllerChooser.addOption("None", null);
      SecondControllerChooser.addOption("Port 0", Controllers.Zero);
      SecondControllerChooser.setDefaultOption("Port 1", Controllers.One);
      SecondControllerChooser.addOption("Port 2", Controllers.Two);
      SecondControllerChooser.addOption("Port 3", Controllers.Three);
      SecondControllerChooser.addOption("Port 4", Controllers.Four);
      SecondControllerChooser.addOption("Port 5", Controllers.Five);
      // SecondControllerChooser.addOption("Dynamic Xbox", Controllers.dynamicXbox);
      // SecondControllerChooser.addOption("Dynamic Playstation",
      // Controllers.dynamicPlaystation);
      // SecondControllerChooser.addOption("Dynamic Joystick",
      // Controllers.dynamicJoystick.object);
      SmartDashboard.putData("Secondary controller", SecondControllerChooser);

      // FullControllerChooser.addOption("None", null);
      FullControllerChooser.addOption("Port 0", Controllers.Zero);
      FullControllerChooser.addOption("Port 1", Controllers.One);
      FullControllerChooser.addOption("Port 2", Controllers.Two);
      FullControllerChooser.setDefaultOption("Port 3", Controllers.Three);
      FullControllerChooser.addOption("Port 4", Controllers.Four);
      FullControllerChooser.addOption("Port 5", Controllers.Five);
      // FullControllerChooser.addOption("Dynamic Xbox",
      // Controllers.dynamicXbox.object);
      // FullControllerChooser.addOption("Dynamic Playstation",
      // Controllers.dynamicPlaystation.object);
      // FullControllerChooser.addOption("Dynamic Joystick",
      // Controllers.dynamicJoystick.object);
      SmartDashboard.putData("Full controller", FullControllerChooser);

      // GuestControllerChooser.addOption("None", null);
      GuestControllerChooser.addOption("Port 0", Controllers.Zero);
      GuestControllerChooser.addOption("Port 1", Controllers.One);
      GuestControllerChooser.addOption("Port 2", Controllers.Two);
      GuestControllerChooser.addOption("Port 3", Controllers.Three);
      GuestControllerChooser.setDefaultOption("Port 4", Controllers.Four);
      GuestControllerChooser.addOption("Port 5", Controllers.Five);
      // GuestControllerChooser.addOption("Dynamic Xbox", Controllers.dynamicXbox);
      // GuestControllerChooser.addOption("Dynamic Playstation",
      // Controllers.dynamicPlaystation);
      // GuestControllerChooser.addOption("Dynamic Joystick",
      // Controllers.dynamicJoystick.object);
      SmartDashboard.putData("Guest controller", GuestControllerChooser);
    }

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

    CommandScheduler.getInstance().getActiveButtonLoop().clear();

    ControllerBase SelectedDriver = DriveControllerChooser.getSelected();
    ControllerBase SelectedSecondary = SecondControllerChooser.getSelected();
    ControllerBase SelectedFull = FullControllerChooser.getSelected();
    ControllerBase SelectedGuest = GuestControllerChooser.getSelected();

    if (SelectedDriver != null && SelectedFull == null) {
      System.out.println("Main controller on port " + SelectedDriver.object.getPort());
      SelectedDriver.A.onTrue(new InstantCommand(() -> Arm.customPosition(-12, 0)))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedDriver.LeftStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(true)));
      SelectedDriver.RightStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(false)));

      SelectedDriver.Options.onTrue(new InstantCommand(() -> AutoBalanceCommand.schedule()));
      SelectedDriver.Share.onTrue(new InstantCommand(() -> AutoBalanceCommand.cancel()));

      SelectedDriver.RightTrigger.onTrue(new InstantCommand(() -> System.out.println("Hello world")));

      SelectedDriver.LeftBumper.or(SelectedDriver.RightBumper)
          .onTrue(new InstantCommand(() -> DriveTrain.doSlowMode(true)))
          .onFalse(new InstantCommand(() -> DriveTrain.doSlowMode(false)));
    }

    if (SelectedSecondary != null && SelectedFull == null) {
      System.out.println("Secondary driver on port " + SelectedSecondary.object.getPort());
      SelectedSecondary.A.onTrue(new InstantCommand(() -> Arm.drivingPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedSecondary.X.onTrue(new InstantCommand(() -> Arm.bottomPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedSecondary.B.onTrue(new InstantCommand(() -> Arm.scoringPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedSecondary.Y.onTrue(new InstantCommand(() -> Arm.startingPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedSecondary.LeftBumper.whileTrue(new InstantCommand(() -> Claw.open()))
          .onFalse(new InstantCommand(() -> Claw.stop()));

      SelectedSecondary.RightBumper.whileTrue(new InstantCommand(() -> Claw.close()))
          .onFalse(new InstantCommand(() -> Claw.stop()));

      SelectedSecondary.LeftTrigger.onTrue(new InstantCommand(() -> GoTele.enableArmManual()))
          .onFalse(new InstantCommand(() -> GoTele.disableArmManual()));

      SelectedSecondary.RightTrigger.onTrue(new InstantCommand(() -> safteyEnabled = false))
          .onTrue(new InstantCommand(() -> System.out.println("SAFTEY LIMITS DISABLED")))
          .onFalse(new InstantCommand(() -> safteyEnabled = true))
          .onFalse(new InstantCommand(() -> System.out.println("SAFTEY LIMITS ENABLED")));

      // SelectedSecondary.Touchpad.onTrue(new InstantCommand(() ->
      // Arm.info.resetEncoders()));

      SelectedSecondary.Options.onTrue(new InstantCommand(() -> Arm.stopArm()))
          .whileTrue(new InstantCommand(() -> Arm.holdLifter()));
      SelectedSecondary.Share.onTrue(new InstantCommand(() -> Arm.setLifter(0)));
    }

    if (SelectedFull != null) {
      System.out.println("Full controll on port " + SelectedFull.object.getPort());
      SelectedFull.LeftStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(true)));
      SelectedFull.RightStickPress.onTrue(new InstantCommand(() -> DriveTrain.doHighGear(false)));

      SelectedFull.A.onTrue(new InstantCommand(() -> Arm.drivingPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedFull.X.onTrue(new InstantCommand(() -> Arm.bottomPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedFull.B.onTrue(new InstantCommand(() -> Arm.scoringPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedFull.Y.onTrue(new InstantCommand(() -> Arm.startingPosition()))
          .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
          .onFalse(new InstantCommand(() -> Arm.stopArm()));

      SelectedFull.LeftBumper.whileTrue(new InstantCommand(() -> Claw.open()))
          .onFalse(new InstantCommand(() -> Claw.stop()));

      SelectedFull.RightBumper.whileTrue(new InstantCommand(() -> Claw.close()))
          .onFalse(new InstantCommand(() -> Claw.stop()));

      SelectedFull.LeftTrigger.onTrue(new InstantCommand(() -> GoTele.enableArmManual()))
          .onFalse(new InstantCommand(() -> GoTele.disableArmManual()));

      SelectedFull.Options.onTrue(new InstantCommand(() -> Arm.info.resetEncoders()));

      SelectedFull.RightTrigger.onTrue(new InstantCommand(() -> safteyEnabled = false))
          .onTrue(new InstantCommand(() -> System.out.println("SAFTEY LIMITS DISABLED")))
          .onFalse(new InstantCommand(() -> safteyEnabled = true))
          .onFalse(new InstantCommand(() -> System.out.println("SAFTEY LIMITS ENABLED")));

      SelectedFull.Options.onTrue(new InstantCommand(() -> guestAllowed = !guestAllowed))
          .onTrue(new InstantCommand(() -> System.out.println("Guest control set to " + guestAllowed)));
    }

    if (SelectedGuest != null) {
      System.out.println("Guest controller on port " + SelectedGuest.object.getPort());
      SelectedGuest.LeftTrigger.or(SelectedGuest.RightTrigger).and(() -> guestAllowed)
          .onTrue(new InstantCommand(() -> GoTele.enableArmManual()))
          .onFalse(new InstantCommand(() -> GoTele.disableArmManual()));

      SelectedGuest.LeftBumper.whileTrue(new InstantCommand(() -> Claw.open()))
          .onFalse(new InstantCommand(() -> Claw.stop()));

      SelectedGuest.RightBumper.whileTrue(new InstantCommand(() -> Claw.close()))
          .onFalse(new InstantCommand(() -> Claw.stop()));
    }

    /*
     * dynamicXbox.A.onTrue(new InstantCommand(() -> Arm.customPosition(-12, 0)))
     * .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
     * .onFalse(new InstantCommand(() -> Arm.stopArm()));
     * 
     * dynamicXbox.LeftStickPress.onTrue(new InstantCommand(() ->
     * DriveTrain.doHighGear(true)));
     * dynamicXbox.RightStickPress.onTrue(new InstantCommand(() ->
     * DriveTrain.doHighGear(false)));
     * 
     * dynamicXbox.Options.onTrue(new InstantCommand(() ->
     * AutoBalanceCommand.schedule()));
     * dynamicXbox.Share.onTrue(new InstantCommand(() ->
     * AutoBalanceCommand.cancel()));
     * 
     * dynamicXbox.RightTrigger.onTrue(new InstantCommand(() ->
     * System.out.println("Hello world")));
     * 
     * // Bumpers are slow speed
     */
    // Playstation

    /*
     * dynamicPlaystation.A.onTrue(new InstantCommand(() -> Arm.drivingPosition()))
     * .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
     * .onFalse(new InstantCommand(() -> Arm.stopArm()));
     * 
     * dynamicPlaystation.X.onTrue(new InstantCommand(() -> Arm.bottomPosition()))
     * .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
     * .onFalse(new InstantCommand(() -> Arm.stopArm()));
     * 
     * dynamicPlaystation.B.onTrue(new InstantCommand(() -> Arm.scoringPosition()))
     * .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
     * .onFalse(new InstantCommand(() -> Arm.stopArm()));
     * 
     * dynamicPlaystation.Y.onTrue(new InstantCommand(() -> Arm.startingPosition()))
     * .whileTrue(new InstantCommand(() -> Arm.moveToPreset()))
     * .onFalse(new InstantCommand(() -> Arm.stopArm()));
     * 
     * dynamicPlaystation.LeftBumper.whileTrue(new InstantCommand(() ->
     * Claw.open()))
     * .onFalse(new InstantCommand(() -> Claw.stop()));
     * 
     * dynamicPlaystation.RightBumper.whileTrue(new InstantCommand(() ->
     * Claw.close()))
     * .onFalse(new InstantCommand(() -> Claw.stop()));
     * 
     * dynamicPlaystation.LeftTrigger.onTrue(new InstantCommand(() ->
     * GoTele.enableArmManual()))
     * .onFalse(new InstantCommand(() -> GoTele.disableArmManual()));
     * 
     * dynamicPlaystation.RightTrigger.onTrue(new InstantCommand(() -> safteyEnabled
     * = false))
     * .onTrue(new InstantCommand(() ->
     * System.out.println("SAFTEY LIMITS DISABLED")))
     * .onFalse(new InstantCommand(() -> safteyEnabled = true))
     * .onFalse(new InstantCommand(() ->
     * System.out.println("SAFTEY LIMITS ENABLED")));
     * 
     * dynamicPlaystation.Touchpad.onTrue(new InstantCommand(() ->
     * Arm.info.resetEncoders()));
     * 
     * dynamicPlaystation.Options.onTrue(new InstantCommand(() -> Arm.stopArm()))
     * .whileTrue(new InstantCommand(() -> Arm.holdLifter()));
     * dynamicPlaystation.Share.onTrue(new InstantCommand(() -> Arm.setLifter(0)));
     */
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
    System.out.print("**Mapping controllers ");
    CommandScheduler.getInstance().getActiveButtonLoop().clear();
    ControllerTracking.updatePortNumbers();
    RobotContainer.configureButtonBindings();
    System.out.println(" * Done**");
  }
}