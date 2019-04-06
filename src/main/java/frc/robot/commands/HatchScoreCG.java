package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
//import frc.robot.commands.ladder.LadderSetLevelC;
//import frc.robot.subsystems.LadderS.LadderLevel;
import frc.robot.commands.hatch.HatchRunWheelsForTimeC;

public class HatchScoreCG extends CommandGroup {
  public HatchScoreCG(){       //(LadderLevel level) {
    //addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    //addSequential(Robot.m_ladderMoveUpPIDC);
    
    //addParallel(Robot.m_ladderHoldPIDC);
    //Score hatch
    //addParallel(new HatchRunWheelsForTimeC(1, 2)); //I don't know if this is the correct timeout...
    addSequential(new HatchDrawerDeployC());
    addSequential(new WaitCommand(0.4));

    //If that ^ ^ doesn't work well try this. It may have slightly different timing.
    //addParallel(new HatchDrawerDeployC());
    //addSequential(new HatchRunWheelsForTimeC(1, 1))

    addParallel(new HatchRunWheelsForTimeC(-0.8,2));
    //wait 1 second
    addSequential(new WaitCommand(0.4)); //maybe speed up by switching to 0.5
    
    //Return to level 0
    addSequential(new HatchDrawerRetractC()); //Perhaps wait a really small amo unt before we lower
    //addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    //addSequential(Robot.m_ladderMoveDownPIDC);
  }
}