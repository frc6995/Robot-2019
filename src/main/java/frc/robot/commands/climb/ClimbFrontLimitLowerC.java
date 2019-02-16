package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//NOTE: The motors are on the *front* pnuematics, and it is reversing onto the platform

public class ClimbFrontLimitLowerC extends Command {
  public ClimbFrontLimitLowerC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbCrawlerS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorReverse(); //reversing onto the platform, this will be the last of the 2 to run.
    while (Robot.m_ClimbFrontS.cSwitchFront() == true){
        Robot.m_drivebaseS.arcadeDrive(-0.1, 0, 1); // deadzone is 0.02
    }
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_ClimbFrontS.retractFront();
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
    Robot.m_ClimbCrawlerS.motorStop();
  }
}
