/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * This xbox command uses the two triggers for forwards and back with the left stick acting as rotation and does not contain a throttle
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveArcadeXboxC extends Command {
  private double forwardSpeed = 0;
  private double backwardSpeed = 0; 
  private double moveSpeed = 0;
  private double rotSpeed = 0;
  private double throt = 0;

  public DriveArcadeXboxC() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivebaseS);
    throt = SmartDashboard.getNumber("XboxThrottle", 0.8);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    forwardSpeed = -Robot.m_oi.xbox.left_trigger();
    backwardSpeed = -Robot.m_oi.xbox.right_trigger();
    //forwardSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_LEFT_TRIGGER);
    //backwardSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_RIGHT_TRIGGER);

    moveSpeed = forwardSpeed - backwardSpeed;
    rotSpeed = Robot.m_oi.xbox.left_stick_x();
    //rotSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_LEFT_X_AXIS);
    
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
