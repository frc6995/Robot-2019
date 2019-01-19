
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchMechToggleCG extends CommandGroup {

  public HatchMechToggleCG() {
    addSequential(new HatchMechDeployC());
    addSequential(new HatchMechRectractC());
   
  }
}
