package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.DriveForTimeC;

public class VisionAlignAndDriveCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public VisionAlignAndDriveCG(boolean rocketCargo) {
    //Align to target
    addSequential(new VisionAlignTargetC(rocketCargo));

    //Drive forward a bit
    addSequential(new DriveForTimeC(0.5,0.3));
  }
}