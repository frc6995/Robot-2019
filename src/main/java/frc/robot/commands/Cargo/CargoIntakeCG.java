/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.DriveForwardC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.VisionAlignTargetC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class CargoIntakeCG extends CommandGroup {
  /**
   * Command Group for aligning and intaking cargo. <p>
   * <b>Order of Operations:</b> <p>
   * 1: Sets ladder level to vision level <p>
   * 2: moves the ladder up <p>
   * 3: a: holds ladder position <p>
   * 3: b: aligns to the target <p>
   * 4: Sets ladder level to cargo intake <p>
   * 5: moves the ladder up <p>
   * 6: a: holds ladder position <p>
   * 6: b: moves forward <p>
   * 7: runs the cargo intake command.
   */
  public CargoIntakeCG() {
    
    //Changes the ladder to the position needed to intake the cargo
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CARGO_INTAKE));
    //TODO - moves the ladder up to the position (MAKE SURE THIS GETS CHANGED TO DOWN IF THE CARGO INTAKE IS BELOW VISION)
    addSequential(new LadderMoveUpPIDC());
    //holds the ladder position.
    addParallel(new LadderHoldPIDC());
    //drives forward for a set period of time at a set power.
    //addParallel(new DriveForwardC(0.3, 5));
    //runs the intake command.
    addSequential(new CargoIntakeC());

    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(new LadderMoveDownPIDC());
    System.out.println("LadderHatchScoreCG Finished");
  }
}
