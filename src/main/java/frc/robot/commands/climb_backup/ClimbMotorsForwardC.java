package frc.robot.commands.climb_backup;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

// Used entirely for testing, not even backup. Simply turns the motors on. Use MotorsStopC to end.
// !!! Drivebase motors did not move. 
// I bet the problem is that the drivebase uses Talons and the default behavior is to brake when
// not applying speed to motors (where as the crawler is using a spark that doesn't have that issue).
// Since the command finishes immediately, the drivebase stops.

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
    Robot.m_drivebaseS.arcadeDrive(RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
  }

  @Override
  protected boolean isFinished() {
    //return true;
    //PostChange
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
