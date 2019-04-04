package frc.robot.commands.outdated.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformBetterCG extends CommandGroup {
  /**
   * Climb the endgame platform
   * Needs to already be lifted
   */
  public ClimbPlatformBetterCG(boolean enabled) {
    if (enabled) {
      addSequential(new ClimbDriveTillLimitC(1));
      addSequential(new ClimbRetractStageC(1));
      addSequential(new ClimbDriveTillLimitC(2));
      addSequential(new ClimbRetractStageC(2));
      addSequential(new ClimbDriveTillLimitC(3), 0.4); //turn on the motors again but kill after a short timeout
    }
  }
}
