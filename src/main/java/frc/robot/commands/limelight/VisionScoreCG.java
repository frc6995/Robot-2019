package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.limelight.AlignTargetC;

public class VisionScoreCG extends CommandGroup {

  public VisionScoreCG() {

    addSequential(new AlignTargetC());
    addSequential(new DriveForTimeC(3), 3);

  }
}
