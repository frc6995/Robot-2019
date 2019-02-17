package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LadderS.LadderLevel;

//Method to set the level the ladder to should travel to next

public class LadderSetLevelC extends Command {
  LadderLevel nextLadderLevel = LadderLevel.LEVEL_ONE;

  public LadderSetLevelC(LadderLevel nextLevel) {
    nextLadderLevel = nextLevel;
  }

  @Override
  protected void initialize() {
    if(nextLadderLevel == LadderLevel.LEVEL_ONE && Robot.m_ladderS.getCurrentLadderLevel() != LadderLevel.LEVEL_ONE) {
      nextLadderLevel = LadderLevel.LEVEL_VISION; // this makes a "cushion" so it can drift down to level one.
    }
  }

  @Override
  protected void execute() {
    System.out.println("Changed to level: " + nextLadderLevel);
    Robot.m_ladderS.setNextLadderLevel(nextLadderLevel);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.m_ladderS.setNextLadderLevel(nextLadderLevel);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
