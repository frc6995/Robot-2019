package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.hatch.HatchMechCG;
import frc.robot.subsystems.LadderS.LadderLevel;

public class LadderLevelHatchScoreCG extends CommandGroup {
  /**
   * Move to the desired ladder level and deploy Hatch Cover or launch Cargo
   * If thumb pressed, then shoot cargo, else deploy hatch
   */
  public LadderLevelHatchScoreCG(LadderLevel level) {

    System.out.println("LadderLevelHatchScore Created for LadderLevel " + level);

      //set ladder level first.
      addSequential(new LadderSetLevelC(level));
      //Move up to the set ladder level, and swap to holding
      addSequential(new LadderMoveUpPIDC());
      
      addParallel(new LadderHoldPIDC());
      //Score hatch
      addSequential(new HatchMechCG());
  
      //wait 1 second
      addSequential(new WaitCommand(1));
      
      //Return to level 0
      addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
      addSequential(new LadderMoveDownPIDC());
  }
}
