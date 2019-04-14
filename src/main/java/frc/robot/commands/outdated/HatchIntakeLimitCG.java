package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.hatch.HatchRunWheelsForTimeC;

public class HatchIntakeLimitCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchIntakeLimitCG() {
    
    //   --EXTEND--
    //Starts wheels spinning
    addParallel(new HatchRunWheelsForTimeC(-0.7, 10));
    //Deploys the hatchMech
    addSequential(Robot.m_hatchDrawerDeployC);

    //   --WAIT--
    //Waits for a hatch to be grabbed
    addSequential(new WaitForHatchLimitC(true));

    //   --RETRACT--
    //Retract hatchMech
    addSequential(Robot.m_hatchDrawerRetractC);
    //Waits a bit to give the hatchMech a sec to pull the hatch out
    addSequential(new WaitCommand(0.2));
    //Stops the wheels with a timeout of 0.1 seconds, forcing the command to stop
    addSequential(new HatchRunWheelsForTimeC(0.0, 0.1));
  }
}
