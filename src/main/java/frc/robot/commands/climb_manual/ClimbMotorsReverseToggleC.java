package frc.robot.commands.climb_manual;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

// This is a real override/backup, it will toggle the drivebase and climb motors on/off.
// In combination with FrontToggleC and RearToggleC, the climb commands can be run independently.

public class ClimbMotorsReverseToggleC extends Command {
  boolean EnabledB;

  public ClimbMotorsReverseToggleC(boolean enabled) {
    EnabledB = enabled;
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (EnabledB == true) {
      System.out.print("Run motors");
      Robot.m_ClimbCrawlerS.motorReverse();
      Robot.m_drivebaseS.arcadeDrive(-RobotMap.CLIMB_MOTORS_SPEED, 0, 1); 
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_drivebaseS.arcadeDrive(0, 0, 0); 
  }

  @Override
  protected void interrupted() {
    end();
  }
}
