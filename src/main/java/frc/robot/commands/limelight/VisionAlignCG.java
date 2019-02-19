package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderSetLevelC;
import frc.robot.commands.limelight.VisionAlignTargetC;
import frc.robot.subsystems.LadderS.LadderLevel;

public class VisionAlignCG extends CommandGroup {

  public VisionAlignCG() {
    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_VISION));
    addSequential(new LadderMoveUpPIDC());
    addParallel(new LadderHoldPIDC());

    addSequential(new VisionAlignTargetC());
    addSequential(new DriveForTimeC(1), 1);

    addSequential(new LadderSetLevelC(LadderLevel.LEVEL_ONE));
    addSequential(new LadderMoveDownPIDC());
  }
}
