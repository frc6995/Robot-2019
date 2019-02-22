/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbFrontReverseLimitRetractCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbFrontReverseLimitRetractCG() {
    addSequential(new ClimbFrontReverseLimitC());
    addSequential(new ClimbFrontRetractC());
  }
}
