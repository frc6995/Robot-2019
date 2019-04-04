package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
//import frc.robot.commands.hatch.HatchMechCG; //not used
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class LadderLevelHatchScoreCG extends CommandGroup {
  /**
   * Move to the desired ladder level and deploy Hatch Cover or launch Cargo
   * If thumb pressed, then shoot cargo, else deploy hatch
   */
  public LadderLevelHatchScoreCG(LadderLevel level) {
    this.setInterruptible(false);
    System.out.println("LadderLevelHatchScore Created for LadderLevel " + level);

      //set ladder level first.
      addSequential(new LadderSetLevelC(level));
      //Move up to the set ladder level, and swap to holding
      addSequential(Robot.m_ladderMoveUpPIDC);
      
      addParallel(Robot.m_ladderHoldPIDC);
      //Score hatch
      addSequential(Robot.m_hatchMechCG);
  
      //wait 1 second
      addSequential(new WaitCommand(1));
      
      //Return to level 0
      addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
      addSequential(Robot.m_ladderMoveDownPIDC);
  }
}
