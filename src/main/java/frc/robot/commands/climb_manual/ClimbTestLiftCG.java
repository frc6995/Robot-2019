/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.climb.ClimbFrontLiftC;
import frc.robot.commands.climb.ClimbRearLiftC;

public class ClimbTestLiftCG extends CommandGroup {
  /**
   * This command worked for our manual climbing test on 2/19/19. Wait might be necessary so that we
   * finish deploying before moving on (per Mr. Shue)
   */
  public ClimbTestLiftCG() {
    addParallel(new ClimbFrontLiftC());
    // !!Post Change to try to give the heavy side time to rise
    addSequential(new WaitCommand(.1));
    addSequential(new ClimbRearLiftC());
    addSequential(new WaitCommand(1));
  }
}
