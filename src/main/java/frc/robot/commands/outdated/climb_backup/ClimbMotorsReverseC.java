package frc.robot.commands.outdated.climb_backup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

// Used entirely for testing, not even backup. Simply turns the motors on. Use MotorsStopC to end.
// !!! Drivebase motors did not move. We used the xbox controller to move instead.
// I bet the problem is that the drivebase uses Talons and the default behavior is to brake when
// not applying speed to motors (where as the crawler is using a spark that doesn't have that issue).
// Since the command finishes immediately, the drivebase stops.


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
    System.out.println("ClimbMotorsReverseC");
    Robot.m_ClimbCrawlerS.motorSet(-RobotMap.CLIMB_MOTORS_SPEED);
    Robot.m_drivebaseS.arcadeDrive(-RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
  }

  @Override
  protected boolean isFinished() {
    //Should this return false instead and require a stop command?
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