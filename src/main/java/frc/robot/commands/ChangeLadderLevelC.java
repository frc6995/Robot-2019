
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeLadderLevelC extends Command {
  public ChangeLadderLevelC(int ladderLevel) {
    Robot.m_ladderS.setNextLadderLevel(ladderLevel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {  
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
  
  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
