package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.hatch.HatchRunWheelsForTimeC;

public class HatchIntakeCG extends CommandGroup {
  public HatchIntakeCG() {
    //Run the wheels in
    addParallel(new HatchRunWheelsForTimeC(-0.9, 2));

    //Push the hatch mech out
    addSequential(Robot.m_hatchDrawerDeployC);

    //Wait for hatch to be pulled on
    addSequential(new WaitCommand(1.75));

    //Retract
    addSequential(Robot.m_hatchDrawerRetractC);
  }
}