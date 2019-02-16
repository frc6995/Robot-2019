package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.limelight.VisionAlignTargetC;

public class VisionScoreCG extends CommandGroup {

  public VisionScoreCG() {

    addSequential(new VisionAlignTargetC());
    addSequential(new DriveForTimeC(3), 3);

  }
}
