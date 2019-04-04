package frc.robot.commands.outdated.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbRetractStageC extends Command {
  private int stage;
  private double speed;
  
  public ClimbRetractStageC(int stage) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
    this.stage = stage;
    this.speed = RobotMap.CLIMB_MOTORS_SPEED;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (this.speed > 0) {
      if (this.stage == 1) {
        Robot.m_ClimbFrontS.retractFront();
      }
      else if (this.stage == 2) {
        Robot.m_ClimbRearS.retractRear();
      }
    }
    else if (this.speed < 0) {
      if (this.stage == 1) {
        Robot.m_ClimbRearS.retractRear();
      }
      else if (this.stage == 2) {
        Robot.m_ClimbFrontS.retractFront();
      }
    }
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
