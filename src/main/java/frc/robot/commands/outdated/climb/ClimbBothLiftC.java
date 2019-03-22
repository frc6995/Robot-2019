package frc.robot.commands.outdated.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbBothLiftC extends Command {
  private boolean enabled;
  
  public ClimbBothLiftC(boolean enabled) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
    this.enabled = enabled;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (this.enabled) {
      Robot.m_ClimbFrontS.deployFront();
      Robot.m_ClimbRearS.deployRear();
    }
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
