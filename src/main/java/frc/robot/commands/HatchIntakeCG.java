/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ladder.*;
import frc.robot.subsystems.LadderS.LadderLevel;
import frc.robot.commands.drive.DriveForTimeC;

public class HatchIntakeCG extends CommandGroup {
  /**
   * To remove hatch from intake without dropping it, we need to lift up
   * and back away.
   */
  public HatchIntakeCG() {

    //set ladder level first.
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_VISION));

    //Move up to the set ladder level and drive backwards
    addParallel(new LadderMoveUpPIDC());
    addSequential(new DriveForTimeC(1,-0.2));
   
    //Return to level 1
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_ONE));
    addSequential(new LadderMoveDownPIDC());
  }
}