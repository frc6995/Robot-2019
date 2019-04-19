package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveDownC;
//import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
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
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());

    // --SCORE--
    //Score hatch
    addSequential(new HatchScoreCG());
    
    // --LOWER--
    //Return to level 0
    addSequential(new LadderMoveDownC());
  }

  @Override
  protected void end() {
    System.out.println("LadderLevelCargoScoreCG Ended");
  }

  @Override
  protected void interrupted() {
    System.out.println("LadderLevelCargoScoreCG Interrupted");
  }
}
