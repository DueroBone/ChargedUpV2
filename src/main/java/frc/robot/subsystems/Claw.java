package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.DeviceConstants;

public class Claw {
  static CANSparkMax clawMotor = new CANSparkMax(DeviceConstants.clawMotorId, MotorType.kBrushless);
  static CANSparkMax clawMotor2 = new CANSparkMax(10, MotorType.kBrushless);
  public static void setup() {
    clawMotor.setIdleMode(IdleMode.kBrake);
    clawMotor.setSmartCurrentLimit(DeviceConstants.clawAmpsMax);
    clawMotor.burnFlash();

    clawMotor2.setIdleMode(IdleMode.kBrake);
    clawMotor2.setSmartCurrentLimit(DeviceConstants.clawAmpsMax);
    clawMotor2.setInverted(true);
    clawMotor2.burnFlash();
  }

  public static void open() {
    clawMotor.set(0.15);
    clawMotor2.set(0.15);
  }

  public static void close() {
    clawMotor.set(-0.25);
    clawMotor2.set(-0.25);
  }

  public static void stop() {
    clawMotor.stopMotor();
    clawMotor2.stopMotor();
  }
}