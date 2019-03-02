package frc.robot.commands.climb_backup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbFrontRetractC extends Command {
  public ClimbFrontRetractC() {
    requires(Robot.m_ClimbFrontS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    System.out.println("ClimbFrontRetractC");
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
  }
}
