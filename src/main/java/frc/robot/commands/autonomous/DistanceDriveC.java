/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DistanceDriveC extends Command {
  int leftSpeed = 0;
  int rightSpeed = 0;
  double targetAngle;
  int distance;
  public DistanceDriveC(int distanceCounts, int speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivebaseS);
    distance = distanceCounts;
    leftSpeed = speed;
    rightSpeed = speed;
    Robot.m_drivebaseS.resetEncoders();
    Robot.m_drivebaseS.navX.reset();

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivebaseS.pidDrive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.m_drivebaseS.getLeftEncoder() + Robot.m_drivebaseS.getRightEncoder())/2 >= distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivebaseS.pidDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
    end();
  }
}
