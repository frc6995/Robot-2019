package frc.robot.commands.climb_manual;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorsForwardToggleC extends Command {
  public ClimbMotorsForwardToggleC() {
    requires(Robot.m_ClimbMotorControlS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbMotorControlS.motorForward();
    Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1); //might have to switch to visionDrive.
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_ClimbMotorControlS.motorStop();
    Robot.m_drivebaseS.arcadeDrive(0, 0, 0); //probably has to switch to visionDrive
  }

  @Override
  protected void interrupted() {
    end();
  }
}
