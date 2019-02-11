package frc.robot.commands.climb_manual;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbMotorsForwardC extends Command {
  public ClimbMotorsForwardC() {
    requires(Robot.m_ClimbMotorControlS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbMotorControlS.motorForward();
    Robot.m_drivebaseS.arcadeDrive(0.1, 0, 1);
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
