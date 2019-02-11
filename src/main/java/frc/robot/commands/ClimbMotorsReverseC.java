package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbMotorsReverseC extends Command {
  public ClimbMotorsReverseC() {
    requires(Robot.m_ClimbMotorControlS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbMotorControlS.motorReverse();
    Robot.m_drivebaseS.arcadeDrive(-0.1, 0, 1);
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
