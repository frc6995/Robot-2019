package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderChangeLevelC extends Command {
  public LadderChangeLevelC() {

  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_oi.xbox.a() == true) { //Change buttons to button board once available.
        Robot.m_ladderS.setNextLadderLevel(1);
    }
    else if (Robot.m_oi.xbox.b() == true) {
      Robot.m_ladderS.setNextLadderLevel(2);
    }
    else if (Robot.m_oi.xbox.x() == true) {
      Robot.m_ladderS.setNextLadderLevel(3);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
