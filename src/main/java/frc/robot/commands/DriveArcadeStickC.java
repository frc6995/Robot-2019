/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveArcadeStickC extends Command {
  private double moveSpeed = 0;
  private double rotSpeed = 0;
  private double throttle = 0;
  public DriveArcadeStickC() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivebaseS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    moveSpeed = Robot.m_oi.stick.getRawAxis(RobotMap.DRIVE_STICK_MOVE_AXIS);
    rotSpeed = Robot.m_oi.stick.getRawAxis(RobotMap.DRIVE_STICK_LEFTRIGHT_AXIS);
    throttle = Robot.m_oi.stick.getRawAxis(RobotMap.DRIVE_STICK_THROT_AXIS);
    throttle = -throttle; //Flip sign of throttle
    throttle = (throttle+1)/2; //Convert into proper range.

    Robot.m_drivebaseS.arcadeDrive(moveSpeed, rotSpeed, throttle);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
