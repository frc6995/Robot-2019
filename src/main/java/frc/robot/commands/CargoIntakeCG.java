package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.cargo.CargoIntakeC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveDownC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class CargoIntakeCG extends CommandGroup {
  /**
   * Command Group for intaking cargo.
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
    addSequential(new LadderMoveDownC());
  }
}
