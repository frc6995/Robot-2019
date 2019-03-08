package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.Cargo.CargoShooterC;
import frc.robot.commands.hatch.HatchMechCG;
import frc.robot.subsystems.LadderS.LadderLevel;

public class LadderLevelScoreCG extends CommandGroup {
  /**
   * Move to the desired ladder level and deploy Hatch Cover or launch Cargo
   * If thumb pressed, then shoot cargo, else deploy hatch
   */
  public LadderLevelScoreCG(boolean shootCargo,LadderLevel level) {

      boolean thumbHeld = Robot.m_oi.buttonBoard.thumb();
      //set ladder level first.
      addSequential(new LadderSetLevelC(level));
      //Move up to the set ladder level, and swap to holding
      addSequential(new LadderMoveUpPIDC());
      
      addParallel(new LadderHoldPIDC());
      //Score cargo or hatch
      if (thumbHeld){
        addSequential(new CargoShooterC());
      } else {
        addSequential(new HatchMechCG());
      }
  
      //wait 1 second
      addSequential(new WaitCommand(1));
      
      //Return to level 0
      addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
      addSequential(new LadderMoveDownPIDC());
  }
}
