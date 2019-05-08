/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.limelight.VisionAlignTargetC;

public class AutoCG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoCG() {
    addSequential(new autoinitC());
    addSequential(new VisionAlignTargetC(false));
    addSequential(new HatchScoreCG());
  }
}
