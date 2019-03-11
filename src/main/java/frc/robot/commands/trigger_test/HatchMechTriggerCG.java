package frc.robot.commands.trigger_test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.hatch.HatchMechDeployC;
import frc.robot.commands.hatch.HatchMechRetractC;
//import frc.robot.triggers.TestTriggerT;

public class HatchMechTriggerCG extends CommandGroup {

  public HatchMechTriggerCG() {
    if (Robot.m_oi.trigger.get()) {
      System.out.println("The Trigger Worked!! Yay!");
      addSequential(new HatchMechDeployC());
      addSequential(new WaitCommand(0.25),0.25);
      addSequential(new HatchMechRetractC());
    }
    else {
      addSequential(new HatchMechDeployC());
      addSequential(new WaitCommand(1),1);
      addSequential(new HatchMechRetractC());
    }
  }
}