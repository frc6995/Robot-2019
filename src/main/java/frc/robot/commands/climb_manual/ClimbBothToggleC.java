/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/* When testing as the first command in a series of manual executed commands, we received 
** unpredictable results when we retracted front or rear (sometimes rear retracted front).
** Debug comments showed that this command, because it never finishes until toggled, continually
** deploys. Do not toggle commands that only occur once unless you do it in initialize.
*/

public class ClimbBothToggleC extends Command {
  public ClimbBothToggleC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbFrontS.deployFront();
    Robot.m_ClimbRearS.deployRear();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_ClimbFrontS.retractFront();
    Robot.m_ClimbRearS.retractRear();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
