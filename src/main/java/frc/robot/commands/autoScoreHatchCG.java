package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatch.HatchMechToggleCG;
import frc.robot.commands.ladder.*;
import frc.robot.commands.limelight.AlignTargetC;


public class AutoScoreHatchCG extends CommandGroup {
  
  public AutoScoreHatchCG() {

    //Only uncomment and test when we have merged with the auto align branch 
    //addSequential(new AlignTargetC());

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
