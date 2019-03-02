package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Used to lift with Double Solenoid. Command finishes when button released.
public class ClimbBothDSLiftC extends Command {
  private boolean enabled;

  public ClimbBothDSLiftC(boolean enabled) {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
    if (enabled){
      Robot.m_ClimbFrontS.deployFront();
      Robot.m_ClimbRearS.deployRear();
    }
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
    Robot.m_ClimbFrontS.offFront();
    Robot.m_ClimbRearS.offRear();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
