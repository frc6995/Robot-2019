import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.drive.DriveForTimeC;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
import frc.robot.commands.hatch.HatchWheelInC;

public class HatchIntakeCG extends CommandGroup {
  public HatchIntakeCG() {
    //Run the wheels in for 3 seconds
    addParallel(new HatchWheelInC(), 3.0);
    //Push the hatch mech out
    addSequential(new HatchDrawerDeployC());

    addSequential(new WaitCommand(2.5));
    addSequential(new HatchDrawerRetractC());
    //Drive the robot backwards for 1 second
    //addSequential(new DriveForTimeC(1,-0.2));
  }
}