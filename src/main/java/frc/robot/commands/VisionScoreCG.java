package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionScoreCG extends CommandGroup {

  public VisionScoreCG() {

    addSequential(new AlignTargetC());
    addSequential(new DriveForTimeC(3), 3);

  }
}
