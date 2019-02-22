package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

// Used entirely for testing, not even backup. Simply turns the motors on. Use MotorsStopC to end.

public class ClimbMotorsReverseC extends Command {
  public ClimbMotorsReverseC() {
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorReverse();
    Robot.m_drivebaseS.arcadeDrive(-RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
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
