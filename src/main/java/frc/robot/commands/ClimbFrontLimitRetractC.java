package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbFrontLimitRetractC extends Command {
  public ClimbFrontLimitRetractC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbMotorControlS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbMotorControlS.motorForward();
    while (Robot.m_ClimbFrontS.cSwitchFront() == true){
        Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1); //probably has to switch to visionDrive bc/ of deadzone.
    }
    Robot.m_ClimbMotorControlS.motorStop();
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
    Robot.m_ClimbMotorControlS.motorStop();
  }
}
