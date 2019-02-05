package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRearToggleC extends Command {
  public boolean extended = false;

  public ClimbRearToggleC() {
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_ClimbRearS.deployRear();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_ClimbRearS.retractRear();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
