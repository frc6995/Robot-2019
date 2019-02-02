
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeLadderLevelC extends Command {
  public ChangeLadderLevelC(int ladderLevel) {
    Robot.m_ladderS.SetNextLadderLevel(ladderLevel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {  
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
