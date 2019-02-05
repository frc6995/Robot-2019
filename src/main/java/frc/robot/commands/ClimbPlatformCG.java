package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformCG extends CommandGroup {
  /**
   * Climb one box
   */
  public ClimbPlatformCG() {
    addParallel(new ClimbFrontDeployC());
    addSequential(new ClimbRearDeployC());
    addSequential(new ClimbFrontLimitRetractC());
    addSequential(new ClimbRearLimitRetractC());
  }
}
