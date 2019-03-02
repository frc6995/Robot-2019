package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

//NOTE: The motors are on the *front* pnuematics, and it is reversing onto the platform

public class ClimbFrontLimitLowerC extends Command {
  public ClimbFrontLimitLowerC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbCrawlerS);
    // !!Should require drivebase!! PostChange
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbCrawlerS.motorReverse(); //reversing onto the platform, this will be the last of the 2 to run.
    /* PostChange 
<<<<<<< HEAD
    while (Robot.m_ClimbFrontS.cSwitchFront() == true){
        Robot.m_drivebaseS.arcadeDrive(-0.1, 0, 1); 
=======
    while (Robot.m_ClimbFrontS.cSwitchFront() == false){
        Robot.m_drivebaseS.arcadeDrive(-0.1, 0, 1); // deadzone is 0.02
>>>>>>> a6e1fb1c8006a0cf7e98485b153ffa1156796257
    }
    Robot.m_ClimbCrawlerS.motorStop();
    Robot.m_ClimbFrontS.retractFront();
    */
    Robot.m_drivebaseS.arcadeDrive(-RobotMap.CLIMB_MOTORS_SPEED, 0, 1);
  }

  @Override
  protected boolean isFinished() {
    // PostChange
    //return true;
    return Robot.m_ClimbFrontS.cSwitchFront(); //removed ! - Sam 2-28
  }

  @Override
  protected void end() {
    //PostChange
    Robot.m_ClimbFrontS.retractFront();
  }

  @Override
  protected void interrupted() {
    /* PostChange don't stop motors as you don't know if it is needed yet
    Robot.m_ClimbCrawlerS.motorStop();
    */
  }
}
