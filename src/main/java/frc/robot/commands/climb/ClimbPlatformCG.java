package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformCG extends CommandGroup {
  /**
   * Climb one box
   */
  public ClimbPlatformCG() {
    addParallel(new ClimbFrontLiftC());
    addSequential(new ClimbRearLiftC());
    addSequential(new ClimbFrontLimitLowerC());
    addSequential(new ClimbRearLimitLowerC());
  }
}
