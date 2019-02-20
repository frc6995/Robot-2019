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
import frc.robot.subsystems.ClimbFrontS;
import frc.robot.subsystems.ClimbRearS;

public class ClimbTestLiftCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbTestLiftCG() {
    addParallel(new ClimbFrontLiftC());
    addSequential(new ClimbRearLiftC());
    addSequential(new WaitCommand(1));
  }
}
