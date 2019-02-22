package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformCG extends CommandGroup {
  /**
   * Climb one box backwards (robot must enter platform backwards)
   */
  public ClimbPlatformCG(boolean climbconfirmed) {
    if (climbconfirmed) {
      addParallel(new ClimbFrontLiftC());
      addSequential(new ClimbRearLiftC());
      addSequential(new ClimbRearLimitLowerC());
      addSequential(new ClimbFrontLimitLowerC());
    }
  }
}
