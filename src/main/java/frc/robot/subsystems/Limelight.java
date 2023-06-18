// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.sql.Array;
import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  NetworkTable table;
  static double targetOffsetAngle_Horizontal;
  static double targetOffsetAngle_Vertical;
  static double targetArea;
  static double targetSkew;

  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    targetOffsetAngle_Horizontal = table.getEntry("tx").getDouble(0);
    targetOffsetAngle_Vertical = table.getEntry("ty").getDouble(0);
    targetArea = table.getEntry("ta").getDouble(0);
    targetSkew = table.getEntry("ts").getDouble(0);
  }

  public static double[] getPos() {
    double[] blah = {targetOffsetAngle_Horizontal, targetOffsetAngle_Vertical};
    return blah;
  }
}
