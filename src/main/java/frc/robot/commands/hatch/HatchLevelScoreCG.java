package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.*;
import frc.robot.subsystems.LadderS.LadderLevel;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;

public class HatchLevelScoreCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchLevelScoreCG(LadderLevel level) {

    //set ladder level first.
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(new LadderMoveUpPIDC());
    
    //Score hatch
    addParallel(new LadderHoldPIDC());
    addSequential(new HatchMechToggleCG());

    //wait 1 second
    addSequential(new WaitCommand(1));
    
    //Return to level 0
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(new LadderMoveDownPIDC());
  }
}
