package frc.robot.commands.climb_backup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbBothLiftC extends Command {
  private boolean enabled;
  public ClimbBothLiftC(boolean enabled) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
    this.enabled = enabled;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (this.enabled) {
      Robot.m_ClimbFrontS.deployFront();
      Robot.m_ClimbRearS.deployRear();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
