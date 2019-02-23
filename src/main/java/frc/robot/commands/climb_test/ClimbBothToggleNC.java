/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbBothToggleNC extends Command {
  public ClimbBothToggleNC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_ClimbFrontS.getExtended() == true && Robot.m_ClimbRearS.getExtended() == true) {
      Robot.m_ClimbFrontS.deployFront();
      Robot.m_ClimbRearS.deployRear();
    }
    else if (Robot.m_ClimbFrontS.getExtended() == false && Robot.m_ClimbRearS.getExtended() == false) {
      Robot.m_ClimbFrontS.retractFront();
      Robot.m_ClimbRearS.retractRear();
    }
    else {
      System.out.println("Returns are not equal: FAILED!");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
