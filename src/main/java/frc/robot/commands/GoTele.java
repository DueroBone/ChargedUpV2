package frc.robot.commands;

import java.text.MessageFormat;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DeviceConstants;
import frc.robot.Controllers.*;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import java.lang.Math;

public class GoTele extends CommandBase {
  @Override
  public void initialize() {
    System.out.println(MessageFormat.format("**Started {0} ", this.getName()));
  }

  // private double teleLeft = 0;
  // private double teleRight = 0;
  private boolean drivingEnabled = true;
  private boolean armEnabled = true;
  private double deadzone = -1;
  private final DriveTrain drivetrain;
  private double speedMultiplier = 1;
  private final double armUpMax = DeviceConstants.armUpMax;
  private final double armDownMax = DeviceConstants.armDownMax;
  private final double armInMax = DeviceConstants.armInMax;
  private final double armOutMax = DeviceConstants.armOutMax;
  private double armDeadzone = -1;
  private int counter = 0;
  private static boolean armManual = false;
  static boolean isHoldingArm = false;

  /**
   * Identifies active driving controller and activates drivetrain
   */
  public GoTele(boolean drivingEnabled, boolean armEnabled, double driveDeadzone, double topSpeed, double armDeadzone) {
    this.drivetrain = RobotContainer.m_driveTrain; // get driveTrain object from RobotContainer
    this.drivingEnabled = drivingEnabled;
    this.deadzone = driveDeadzone;
    this.speedMultiplier = topSpeed;
    this.armDeadzone = armDeadzone;
    this.armEnabled = armEnabled;
    this.counter = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drivetrain);
  }

  @Override
  public void execute() {
    if (counter == 0) {
      if (!drivingEnabled) {
        DriverStation.reportWarning("## Driving is disabled ##", false);
      }
      if (!armEnabled) {
        DriverStation.reportWarning("## Arm manual control is disabled ##", false);
      }
    }
    // double deadzone = 0.0;
    double teleLeft = 0;
    double teleRight = 0;
    double armLift = 0;
    double armExtend = 0;

    ControllerBase driverController = RobotContainer.DriveControllerChooser.getSelected();
    ControllerBase secondController = RobotContainer.SecondControllerChooser.getSelected();
    ControllerBase fullController = RobotContainer.FullControllerChooser.getSelected();
    ControllerBase guestController = RobotContainer.GuestControllerChooser.getSelected();

    boolean usingConDriver = Math.abs(driverController.object.getLeftY()) > deadzone
        || Math.abs(driverController.object.getRightY()) > deadzone;

    boolean usingConFull = Math.abs(fullController.object.getLeftY()) > deadzone
        || Math.abs(fullController.object.getRightY()) > deadzone;

    boolean usingConGuest = Math.abs(guestController.object.getLeftY()) > deadzone
        || Math.abs(guestController.object.getRightY()) > deadzone;

    // Driving controls
    if (driverController.object.isConnected() && usingConDriver) {
      teleLeft = driverController.object.getLeftY() * -1;
      teleRight = driverController.object.getRightY() * -1;

      if (driverController.LeftTrigger.getAsBoolean() == true) {
        teleLeft = (driverController.object.getLeftY() +
            driverController.object.getRightY()) / (-2);
        teleRight = teleLeft;
      }
    } else if (fullController.object.isConnected() && usingConFull) {
      teleLeft = fullController.object.getLeftY();
      teleRight = fullController.object.getRightY();
    } else if (guestController.object.isConnected() && usingConGuest) {
      teleLeft = guestController.object.getLeftY();
      teleRight = guestController.object.getRightY();
    }

    // arm controls
    if (secondController.object.isConnected() && armManual) {
      armLift = secondController.object.getLeftY() * -1;
      armExtend = secondController.object.getRightY() * -1;
    } else if (fullController.object.isConnected() && armManual) {
      armLift = fullController.object.getLeftY() * -1;
      armExtend = fullController.object.getRightY() * -1;
    } else {
      armLift = 0;
      armExtend = 0;
    }

    teleLeft = procDz(teleLeft, deadzone);
    teleRight = procDz(teleRight, deadzone);
    armLift = procDz(armLift, deadzone);
    armExtend = procDz(armExtend, deadzone);

    if (armLift != 0) {
      if (armLift > 0) {
        armLift = armLift * 1.5 * armUpMax;
      } else {
        armLift = armLift * -1.5 * armDownMax;
      }
    }
    if (armExtend != 0) {
      if (armExtend > 0) {
        armExtend = armExtend * armOutMax;
      } else {
        armExtend = armExtend * -armInMax;
      }
    }

    /*
     * // depricated slow mode
     * if (drivingEnabled) {
     * if (driverController.RightBumper.getAsBoolean() ||
     * driverController.LeftBumper.getAsBoolean()) {
     * DriveTrain.doTankDrive(teleLeft / 3, teleRight / 3);
     * } else {
     * DriveTrain.doTankDrive(teleLeft, teleRight);
     * }
     * }
     */

    if (armEnabled && armManual) {
      if (armLift != 0) {
        Arm.setLifter(armLift);
        isHoldingArm = false;
      } else {
        if (isHoldingArm) {
          Arm.holdLifter();
        } else {
          Arm.stopLifter();
          isHoldingArm = true;
        }
      }
      if (armExtend != 0) {
        Arm.setExtender(armExtend);
      } else {
        Arm.stopExtender();
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  /** Proccess deadzone */
  private double procDz(double input, double deadzone) {
    double a = 1 - deadzone;
    if (Math.abs(input) > deadzone) {
      if (input > 0) {
        input = input - deadzone;
      } else {
        input = input + deadzone;
      }
      input = input * a;
      input = smartSquare(input, Constants.DriveConstants.drivingExponent);
      input = input * speedMultiplier;
    } else {
      input = 0;
    }
    return input;
  }

  private static double smartSquare(double input, int exponent) {
    double output = Math.pow(input, exponent);
    if (Math.signum(input) != Math.signum(output)) {
      output = output * -1;
    }
    return output;
  }

  public static void enableArmManual() {
    armManual = true;
    System.out.println("Arm under manual control");
  }

  public static void disableArmManual() {
    armManual = false;
    System.out.println("Arm NOT under manual control");
  }
}
