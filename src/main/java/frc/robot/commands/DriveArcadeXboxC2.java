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

public class DriveArcadeXboxC2 extends Command {
  private double forwardBackSpeed = 0;
  private double rotationSpeed = 0;
  private double throt = 1;

  private int numberPressed = 0;

  public DriveArcadeXboxC2() {
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
    forwardBackSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_LEFT_Y_AXIS);
    rotationSpeed = Robot.m_oi.xbox.getRawAxis(RobotMap.DRIVE_XBOX_RIGHT_X_AXIS);

    if(Robot.m_oi.xbox.getBButtonPressed()); {
      switch(numberPressed) {
        case 0: throt = 0.80; numberPressed = 1; break;
        case 1: throt = 0.50; numberPressed = 2; break;
        case 2: throt = 0.20; numberPressed = 3; break;
        case 3: throt = 1.00; numberPressed = 0; break;
        default: throt = 1.00; numberPressed = 0; break;
      }
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
