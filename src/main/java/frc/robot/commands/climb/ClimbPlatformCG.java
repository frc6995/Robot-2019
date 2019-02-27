package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.climb_manual.ClimbMotorsStopC;

public class ClimbPlatformCG extends CommandGroup {
  /**
   * Climb one box backwards (robot must enter platform backwards)
   * !! Did nothing!!
   */
  public ClimbPlatformCG(boolean climbconfirmed) {
    if (climbconfirmed) {
      addParallel(new ClimbFrontLiftC());
      addSequential(new ClimbRearLiftC());
      // should we have put a wait here???? PostChange
      addSequential(new WaitCommand(1));
      addSequential(new ClimbRearLimitLowerC());
      addSequential(new ClimbFrontLimitLowerC());
      // May need to increase drivebase speed
      // May need another wait so we don't fall off platform PostChange
      addSequential(new WaitCommand(1));
      addSequential(new ClimbMotorsStopC());

    }
  }
}
