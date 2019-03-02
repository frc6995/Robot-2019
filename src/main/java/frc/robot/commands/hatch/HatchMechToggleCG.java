package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchMechToggleCG extends CommandGroup {

  public HatchMechToggleCG() {
    addSequential(new HatchMechDeployC());
    addSequential(new WaitCommand(0.25),0.25);
    addSequential(new HatchMechRetractC());
  }
}