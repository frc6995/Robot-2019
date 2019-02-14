package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.LadderChangeLevelC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMovePIDC;


public class autoScoreHatchCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autoScoreHatchCG() {

    //Insert auto align procedure

    //Move up to the set ladder level, and swap to holding
    addSequential(new LadderMovePIDC());
    addParallel(new LadderHoldPIDC());

    //Score hatch
    addSequential(new HatchMechToggleCG());

    //Return to level 0
    addSequential(new LadderChangeLevelC(0));
    addSequential(new LadderMovePIDC());
  }
}
