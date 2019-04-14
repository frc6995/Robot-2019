package frc.robot.commands.outdated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
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
    addSequential(new HatchDrawerDeployC());

    //   --WAIT--
    //Waits for a hatch to be grabbed
    addSequential(new WaitForHatchLimitC());

    //   --RETRACT--
    //Retract hatchMech
    addSequential(new HatchDrawerRetractC());
    //Waits a bit to give the hatchMech a sec to pull the hatch out
    addSequential(new WaitCommand(0.2));
    //Stops the wheels with a timeout of 0.1 seconds, forcing the command to stop
    addSequential(new HatchRunWheelsForTimeC(0.0, 0.1));
  }
}
