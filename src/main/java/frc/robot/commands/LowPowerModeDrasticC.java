package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * More current limiting, and compressor off.
 */
public class LowPowerModeDrasticC extends InstantCommand {

  /**
   * 
   */
  public LowPowerModeDrasticC() {
    super();
    requires(Robot.m_drivebaseS);
    requires(Robot.m_ladderS);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_drivebaseS.lowPowerModeOn(true);
    Robot.m_ladderS.lowPowerModeOn(true);
    Robot.m_compressor.stop();
  }

}
