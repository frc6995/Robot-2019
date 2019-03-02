package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbMotorsDriveC extends Command {
  private double x = 0;
  private double y = 0;
  public ClimbMotorsDriveC() {
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    this.y = Robot.m_oi.stick.stick_y();
    this.x = Robot.m_oi.stick.stick_x();
    if (this.y<0.1) {
      this.y = 0.0;
    }
    if (this.x<0.1) {
      this.x = 0.0;
    }
    Robot.m_ClimbCrawlerS.motorSet(this.y);
    Robot.m_drivebaseS.arcadeDrive(this.y, this.x, 0.7); //possibly 1
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_ClimbCrawlerS.motorSet(0);
  }

  @Override
  protected void interrupted() {
  }
}
