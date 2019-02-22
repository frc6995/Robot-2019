package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// Possibly can be used as an override for the command group as well as for ForwardC/ReverseC

public class ClimbMotorsStopC extends Command {
  public ClimbMotorsStopC() {
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_drivebaseS.arcadeDrive(0, 0, 0);
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
