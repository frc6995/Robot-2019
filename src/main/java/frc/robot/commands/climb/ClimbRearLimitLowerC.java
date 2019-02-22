package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRearLimitLowerC extends Command {
  public ClimbRearLimitLowerC() {
    requires(Robot.m_ClimbRearS);
    requires(Robot.m_ClimbCrawlerS);
    // !!Should require drivebase!! PostChange
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorReverse();
    // !! Instead of while loop, start drivebase, If switch = False, end command
    // Still keep drivebase/crawler moving; use stop command if needed
    /* PostChange
    while(Robot.m_ClimbRearS.cSwitchRear() == true){
      Robot.m_drivebaseS.arcadeDrive(-0.1, 0, 1);// may need negative throttle instead of move speed
    }
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_ClimbRearS.retractRear();
    */
    Robot.m_drivebaseS.arcadeDrive(-0.5, 0, 1);// may need negative throttle instead of move speed
  }

  @Override
  protected boolean isFinished() {
    // !! Instead only return true if limit switch is hit (circuit is not closed)
    //return true;
    return !Robot.m_ClimbRearS.cSwitchRear();
  }

  @Override
  protected void end() {
    //PostChange
    Robot.m_ClimbRearS.retractRear();
  }

  @Override
  protected void interrupted() {
    /*PostChange
    Robot.m_ClimbCrawlerS.motorStop();
    */
  }
}
