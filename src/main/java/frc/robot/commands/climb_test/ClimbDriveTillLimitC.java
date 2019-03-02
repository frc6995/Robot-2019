package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbDriveTillLimitC extends Command {
  private int stage;
  private double speed;
  public ClimbDriveTillLimitC(int stage) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
    requires(Robot.m_ClimbCrawlerS);
    requires(Robot.m_drivebaseS);
    this.stage = stage;
    this.speed = RobotMap.CLIMB_MOTORS_SPEED;
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
    if (this.speed > 0 && this.stage == 1 && Robot.m_ClimbFrontS.cSwitchFront() == true) { //true is triggered
      return true;
    }
    else if (this.speed > 0 && this.stage == 2 && Robot.m_ClimbRearS.cSwitchRear() == true) {
      return true;
    }
    else if (this.speed < 0 && this.stage == 1 && Robot.m_ClimbRearS.cSwitchRear() == true) { //true is triggered
      return true;
    }
    else if (this.speed < 0 && this.stage == 2 && Robot.m_ClimbFrontS.cSwitchFront() == true) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  protected void end() {
    Robot.m_ClimbCrawlerS.motorStop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
