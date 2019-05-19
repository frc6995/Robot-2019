package frc.robot.commands.pixycam;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive.DriveForTimeC;
import frc.robot.subsystems.PixyCamS;

public class PixyAlignAndDriveCG extends CommandGroup {
  /**
   * Align using the {@link PixyCamS}
   */
  public PixyAlignAndDriveCG() {
    addSequential(new PixyAlignC());
    addSequential(new DriveForTimeC(0.5,0.3));
  }
}
