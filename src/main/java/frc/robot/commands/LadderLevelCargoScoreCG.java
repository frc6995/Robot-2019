package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.cargo.CargoScoreC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
//import frc.robot.commands.cargo.CargoShooterC; //is this planned to be used?
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class LadderLevelCargoScoreCG extends CommandGroup {
  /**
   * Move to the desired ladder level and score cargo
   */
  public LadderLevelCargoScoreCG(LadderLevel level) {
    //  --LIFT--
    //set ladder level first.
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());

    //  --SCORE--
    //Score cargo
    addSequential(new CargoScoreC());

    //  --LOWER--
    //Return to level 0
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(new LadderMoveDownPIDC());
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