package frc.robot.commands.outdated.climb_backup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRearRetractC extends Command {
  public ClimbRearRetractC() {
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    System.out.println("ClimbRearRetractC");
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
  }
}
