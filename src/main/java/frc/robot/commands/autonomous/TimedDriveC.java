/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class TimedDriveC extends TimedCommand {
  /**
   * Add your docs here.
   */
  public double leftSpeed = 0;
  public double rightSpeed = 0;
  public TimedDriveC(double timeout, double left, double right) {
    super(timeout);
    requires(Robot.m_drivebaseS);
    leftSpeed = left;
    rightSpeed = right;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivebaseS.autoDrive(leftSpeed, rightSpeed);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.m_drivebaseS.autoDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
