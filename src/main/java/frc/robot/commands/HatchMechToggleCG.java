package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchMechToggleCG extends CommandGroup {

  public HatchMechToggleCG() {
    //Deploy
    addSequential(new HatchMechDeployC());
    //Wait 0.25 seconds, NEED TO TUNE ONCE WE GET THE FINAL SUBSYSTEM
    addSequential(new WaitCommand(0.25),0.25);
    //Retract
    addSequential(new HatchMechRectractC());
  }

}