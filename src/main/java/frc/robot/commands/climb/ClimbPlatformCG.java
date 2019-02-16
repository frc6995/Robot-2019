package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformCG extends CommandGroup {
  /**
   * Climb one box
   */
  public ClimbPlatformCG() {
    addParallel(new ClimbFrontLiftC());
    addSequential(new ClimbRearLiftC());
    addSequential(new ClimbRearLimitLowerC()); // The robot is backing onto the platforms, so rear first.
    addSequential(new ClimbFrontLimitLowerC()); // The motors are on the front cylinders
  }
}
