package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRearLimitRetractC extends Command {
  public ClimbRearLimitRetractC() {
    requires(Robot.m_ClimbRearS);
    requires(Robot.m_ClimbMotorControlS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbMotorControlS.motorForwards();
    while(Robot.m_ClimbRearS.CSwitchRear() == false){
      Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1);
    }
    Robot.m_ClimbMotorControlS.motorStop();
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
    Robot.m_ClimbMotorControlS.motorStop();
  }
}
