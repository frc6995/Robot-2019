/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbPlatformBetterCG extends CommandGroup {
  /**
   * Climb the endgame platform
   * Needs to already be lifted
   */
  public ClimbPlatformBetterCG() {
    addSequential(new ClimbDriveTillLimitC(1));
    addSequential(new ClimbRetractStageC(1));
    addSequential(new ClimbDriveTillLimitC(2));
    addSequential(new ClimbRetractStageC(2));
    addSequential(new ClimbDriveTillLimitC(3), 0.4); //turn on the motors again but kill after a short timeout
  }
}
