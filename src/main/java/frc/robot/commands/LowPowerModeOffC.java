package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Resets low power mode to how it is originally
 */
public class LowPowerModeOffC extends InstantCommand {
  /**
   * Add your docs here.
   */
  public LowPowerModeOffC() {
    super();
    requires(Robot.m_drivebaseS);
    requires(Robot.m_CargoShooterS);
    requires(Robot.m_hatchMechWheelsS);
    requires(Robot.m_ladderS);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_drivebaseS.lowPowerModeOff();
    Robot.m_CargoShooterS.lowPowerModeOff();
    Robot.m_hatchMechWheelsS.lowPowerModeOff();
    Robot.m_ladderS.lowPowerModeOff();
    Robot.m_compressor.start();
  }
}
