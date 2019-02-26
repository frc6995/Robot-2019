package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbWhilePressedC extends Command {
  public ClimbWhilePressedC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_ClimbFrontS.deployFront();
    Robot.m_ClimbRearS.deployRear();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.m_ClimbFrontS.offFront();
    //Robot.m_ClimbRearS.offRear();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
