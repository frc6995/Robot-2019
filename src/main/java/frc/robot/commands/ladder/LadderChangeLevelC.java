package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderChangeLevelC extends Command {
  public LadderChangeLevelC(int nextLevel) {
    Robot.m_ladderS.setNextLadderLevel(nextLevel);
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
