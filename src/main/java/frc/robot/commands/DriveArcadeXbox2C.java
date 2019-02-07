/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * This is the xbox command that uses the left and right sticks to control movement with a toggleable throttle using the buttons B and A
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveArcadeXbox2C extends Command {
  private double forwardBackSpeed = 0;
  private double rotationSpeed = 0;
  private double throt = 1;

  private int numberPressed = 0;

  public DriveArcadeXbox2C() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivebaseS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    forwardBackSpeed = Robot.m_oi.xbox.left_stick_y();
    rotationSpeed = Robot.m_oi.xbox.right_stick_x();
    //forwardBackSpeed = -Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_LEFT_Y_AXIS);
    //rotationSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_RIGHT_X_AXIS);

    if(Robot.m_oi.xbox.b()) {
      switch(numberPressed) {
        case 0: throt = 0.80; numberPressed = 1; break;
        case 1: throt = 0.65; numberPressed = 2; break;
        case 2: throt = 0.50; numberPressed = 3; break;
        case 3: throt = 1.00; numberPressed = 0; break;
        default: throt = 1.00; numberPressed = 0; break;
      }
    } else if(Robot.m_oi.xbox.a()) {
      throt = 1;
      numberPressed = 0;
    }

    Robot.m_drivebaseS.arcadeDrive(forwardBackSpeed, rotationSpeed, throt);
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
