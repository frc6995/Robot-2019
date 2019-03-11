/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.trigger_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.commands.hatch.HatchMechDeployC;

public class HatchDeployTriggerC extends Command {
  public HatchDeployTriggerC() {
    requires(Robot.m_hatchMechS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_oi.trigger.get()) {
      System.out.println("The Trigger Worked!! Yay!");
      Robot.m_hatchMechS.deploy();
    }
    else {
      Robot.m_hatchMechS.retract();
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
