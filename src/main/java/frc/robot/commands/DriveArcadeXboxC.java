/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveArcadeXboxC extends Command {
  private double forwardSpeed = 0;
  private double backwardSpeed = 0; 
  private double moveSpeed = 0;
  private double rotSpeed = 0;
  private double throt = 0;
  public DriveArcadeXboxC(double throttle) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivebaseS);
    throt = throttle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    forwardSpeed = Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft);
    backwardSpeed = Robot.m_oi.xbox.getTriggerAxis(Hand.kRight);
    moveSpeed = forwardSpeed - backwardSpeed;
    rotSpeed = Robot.m_oi.xbox.getX(Hand.kLeft);
    Robot.m_drivebaseS.arcadeDrive(moveSpeed, rotSpeed, throt);
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
