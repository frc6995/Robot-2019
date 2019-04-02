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
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class WheelHatchScoreCG extends CommandGroup {
  public WheelHatchScoreCG(LadderLevel level) {
    addSequential(new LadderSetLevelC(level));
    //Move up to the set ladder level, and swap to holding
    addSequential(Robot.m_ladderMoveUpPIDC);
    
    addParallel(Robot.m_ladderHoldPIDC);
    //Score hatch
    addSequential(new RunWheelsForTimeC(1, 1)); //I don't think this is the correct timeout...

    //wait 1 second
    addSequential(new WaitCommand(1));
    
    //Return to level 0
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(Robot.m_ladderMoveDownPIDC);
  }
}
