// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AutoBalance;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoStartPos3 extends SequentialCommandGroup {
  /** Drive into field and then balance */
  public AutoStartPos3(DriveTrain m_driveTrain) {
    addCommands(
        new InstantCommand(() -> DriveTrain.stop(), m_driveTrain), // make sure stopped
        new moveToScoringPositionCommand(),
        new WaitCommand(0.25),
        new InstantCommand(() -> Claw.open()),
        new WaitCommand(0.5),
        new moveToDrivingPositionCommand(),
        new AutoDriveStraightTime(-0.75, 3), // go straight off starting line
        new WaitCommand(0.25),
        new AutoBalance(true),
        new InstantCommand(() -> DriveTrain.stop(), m_driveTrain) // make sure stopped
    );
  }
}
