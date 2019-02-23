package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// This is for testing/backup, it either deploys or retracts the cylinders.
// On a toggle command like this the first press creates and execute the command but it does not finish.
// The second press interrupts the command.

public class ClimbRearToggleNC extends Command {

  public ClimbRearToggleNC() {
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_ClimbRearS.getExtended() == true) {
      Robot.m_ClimbRearS.deployRear();
    }
    else {
      Robot.m_ClimbRearS.retractRear();
    }
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
