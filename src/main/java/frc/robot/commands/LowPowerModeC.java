/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Minor current limiting to save more battery.
 */
public class LowPowerModeC extends InstantCommand {
  /**
   * 
   */
  public LowPowerModeC() {
    super();
    requires(Robot.m_drivebaseS);
    requires(Robot.m_ladderS);
    
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_drivebaseS.lowPowerModeOn(false);
    Robot.m_ladderS.lowPowerModeOn(false);
  }

}
