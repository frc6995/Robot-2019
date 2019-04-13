package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchMechCG extends CommandGroup {

  public HatchMechCG() {
    addSequential(new HatchMechDeployC());
    addSequential(new WaitCommand(0.25),0.25);
    addSequential(new HatchMechRetractC());
  }
}