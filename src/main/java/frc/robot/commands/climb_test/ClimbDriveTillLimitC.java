/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbDriveTillLimitC extends Command {
  private int stage;
  public ClimbDriveTillLimitC(int stage) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
    this.stage = stage;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorForward();
    Robot.m_drivebaseS.arcadeDrive(RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (RobotMap.CLIMB_MOTORS_SPEED > 0 && this.stage == 1 && Robot.m_ClimbFrontS.cSwitchFront() == false) { //false is tripped
      return true;
    }
    else if (RobotMap.CLIMB_MOTORS_SPEED > 0 && this.stage == 2 && Robot.m_ClimbRearS.cSwitchRear() == false) {
      return true;
    }
    else if (RobotMap.CLIMB_MOTORS_SPEED < 0 && this.stage == 1 && Robot.m_ClimbRearS.cSwitchRear() == false) { //false is tripped
      return true;
    }
    else if (RobotMap.CLIMB_MOTORS_SPEED < 0 && this.stage == 2 && Robot.m_ClimbFrontS.cSwitchFront() == false) {
      return true;
    }
    else {
      return false;
    }
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
