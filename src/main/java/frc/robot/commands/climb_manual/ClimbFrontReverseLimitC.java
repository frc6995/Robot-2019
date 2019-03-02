/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbFrontReverseLimitC extends Command {
  public ClimbFrontReverseLimitC() {
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorReverse();
    Robot.m_drivebaseS.arcadeDrive(-RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
  }

  @Override
  protected boolean isFinished() {
    if (Robot.m_ClimbFrontS.cSwitchFront()) {
      return false;
    } else {
    return true;
    } 
  }

  @Override
  protected void end() {
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_drivebaseS.arcadeDrive(0,0,0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
