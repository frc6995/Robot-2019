package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbLiftNC extends Command {
  public ClimbLiftNC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
    Robot.m_ClimbFrontS.deployFront();
    Robot.m_ClimbRearS.deployRear();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    //Robot.m_ClimbFrontS.offFront();
    //Robot.m_ClimbRearS.offRear();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
