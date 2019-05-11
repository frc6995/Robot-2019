package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Kill everything but the drivebase to save power.
 */
public class LowPowerModeDrivebaseC extends InstantCommand {
  /**
   * 
   */
  public LowPowerModeDrivebaseC() {
    super();
    requires(Robot.m_ladderS);
    requires(Robot.m_CargoShooterS);
    requires(Robot.m_hatchMechWheelsS);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_ladderS.lowPowerModeOn(true);
    Robot.m_compressor.stop();
    Robot.m_CargoShooterS.lowPowerModeOn(true, true);
    Robot.m_hatchMechWheelsS.lowPowerModeOn(true, true);
  }

}
