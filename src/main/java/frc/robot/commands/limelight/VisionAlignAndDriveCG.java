package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionAlignAndDriveCG extends CommandGroup {
  public VisionAlignAndDriveCG(boolean rocketCargo) {
    //Align to target
    addSequential(new VisionAlignTargetC(rocketCargo));

    //Drive forward a bit
    //addSequential(new DriveForTimeC(0.5,0.3));
  }
}