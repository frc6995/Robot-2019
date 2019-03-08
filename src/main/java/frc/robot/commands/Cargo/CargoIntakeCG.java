/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.DriveArcadeXboxC;
import frc.robot.commands.drive.DriveForwardC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.VisionAlignTargetC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class CargoIntakeCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoIntakeCG() {

    //Aligns the robot to the cargo intake area
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_VISION));
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());
    addSequential(new VisionAlignTargetC());

    //Changes the ladder to the position needed to intake the cargo
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CARGO_INTAKE));
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());
    addParallel(new DriveForwardC(0.3, 3));
    addParallel(new CargoIntakeC());
  }
}
