/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.DriveForTimeC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class WheelHatchIntakeCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public WheelHatchIntakeCG() {
    //set ladder level first.
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_VISION));

    //Move up to the set ladder level turn on the wheels and drive backwards
    addParallel(new WheelHatchInC(), 3.0);
    addSequential(new WaitCommand(0.5));
    addSequential(new DriveForTimeC(1,-0.2));
   
    //Return to level 1
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_ONE));
    addSequential(new LadderMoveDownPIDC());
  }
}
