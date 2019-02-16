package frc.robot.commands.climb_manual;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

// Used entirely for testing, not even backup. Simply turns the motors on. Use MotorsStopC to end.

public class ClimbMotorsForwardC extends Command {
  public ClimbMotorsForwardC() {
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorForward();
    Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
