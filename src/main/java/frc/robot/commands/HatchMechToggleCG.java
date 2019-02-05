package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;

public class HatchMechToggleCG extends CommandGroup {

  public HatchMechToggleCG() {
    //Deploy
    addSequential(Robot.m_hatchMechDeployC);
    //Wait 0.25 seconds, NEED TO TUNE ONCE WE GET THE FINAL SUBSYSTEM
    addSequential(new WaitCommand(0.25),0.25);
    //Retract
    addSequential(Robot.m_hatchMechRetractC);
  }

}