package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRearLimitRetractC extends Command {
  public ClimbRearLimitRetractC() {
    requires(Robot.m_ClimbRearS);
    requires(Robot.m_ClimbCrawlerS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorForward();
    while(Robot.m_ClimbRearS.cSwitchRear() == true){
      Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1);//probably has to switch to visionDrive
    }
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_ClimbRearS.retractRear();
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
