/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.cargo.CargoIntakeC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class CargoIntakeCG extends CommandGroup {
  /**
   * Command Group for aligning and intaking cargo. <p>
   * <b>Order of Operations:</b>
   * 4: Sets ladder level to cargo intake <p>
   * 5: moves the ladder up <p>
   * 6: a: holds ladder position <p>
   * 7: runs the cargo intake command.
   */
  public CargoIntakeCG() {
    
    //  --LIFT--
    //Move the ladder up
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CARGO_INTAKE));
    addSequential(new LadderMoveUpPIDC());

    //holds the ladder position.
    addParallel(new LadderHoldPIDC());

    //drives forward for a set period of time at a set power.
    //addParallel(new DriveForwardC(0.3, 5));

    // --INTAKE--
    //runs the intake command.
    addSequential(new CargoIntakeC());

    //  --LOWER--
    //Once we have intook, lower the ladder
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_CUSHION));
    addSequential(new LadderMoveDownPIDC());
  }
}
