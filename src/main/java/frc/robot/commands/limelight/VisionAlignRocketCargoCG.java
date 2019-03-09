package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.VisionAlignTargetC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class VisionAlignRocketCargoCG extends CommandGroup {

  public VisionAlignRocketCargoCG() {
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_ROCKET_CARGO_VISION));
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());

    addSequential(new VisionAlignTargetC());
    addSequential(new LadderMoveDownPIDC());
    // TODO Are we driving forward enough?
    //addSequential(new DriveForTimeC(2, 0.2));
  }
}