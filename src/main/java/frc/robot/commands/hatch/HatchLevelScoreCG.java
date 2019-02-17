package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.*;


public class HatchLevelScoreCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchLevelScoreCG(int level) {

    //HOW WILL THE CORRECT LEVEL GET SET???? SEEMS BETTER TO KEEP VISION ALIGN SEPARATE FROM LADDER/SCORING

    //set ladder level first.
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(new LadderMovePIDC());
    
    //Score hatch
    addParallel(new LadderHoldPIDC());
    addSequential(new HatchMechToggleCG());

    //Return to level 0
    addSequential(new LadderSetLevelC(0));
    addSequential(new LadderMovePIDC());
    
    //Insert back away?
  }
}
