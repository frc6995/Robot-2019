/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class WheelHatchScoreCG extends CommandGroup {
  public WheelHatchScoreCG(LadderLevel level) {
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(Robot.m_ladderMoveUpPIDC);
    
    addParallel(Robot.m_ladderHoldPIDC);
    //Score hatch
    addParallel(new RunWheelsForTimeC(1, 2)); //I don't know if this is the correct timeout...
    addSequential(new HatchDrawerDeployC());

    //If that ^ ^ doesn't work well try this. It may have slightly different timing.
    //addParallel(new HatchDrawerDeployC());
    //addSequential(new RunWheelsForTimeC(1, 1));

    //wait 1 second
    addSequential(new WaitCommand(1)); //maybe speed up by switching to 0.5
    
    //Return to level 0
    addSequential(new HatchDrawerRetractC());
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(Robot.m_ladderMoveDownPIDC);
  }
}
