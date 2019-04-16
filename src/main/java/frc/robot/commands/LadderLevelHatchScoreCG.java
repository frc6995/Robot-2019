package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class LadderLevelHatchScoreCG extends CommandGroup {
  /**
   * Move to the desired ladder level and score hatch
   */
  public LadderLevelHatchScoreCG(LadderLevel level) {
    // --LIFT--
    //set ladder level first.
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(Robot.m_ladderMoveUpPIDC);
    addParallel(Robot.m_ladderHoldPIDC);

    // --SCORE--
    //Score hatch
    addSequential(Robot.m_hatchScoreCG);
    
    // --LOWER--
    //Return to level 0
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(Robot.m_ladderMoveDownPIDC);
  }
}
