package frc.robot.commands.climb_test;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbBothToggleNC extends Command {
  public ClimbBothToggleNC() {
    requires(Robot.m_ClimbFrontS);
    requires(Robot.m_ClimbRearS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_ClimbFrontS.getExtended() == Value.kForward && Robot.m_ClimbRearS.getExtended() == Value.kForward) {
      Robot.m_ClimbFrontS.deployFront();
      Robot.m_ClimbRearS.deployRear();
    }
    else if (Robot.m_ClimbFrontS.getExtended() == Value.kReverse && Robot.m_ClimbRearS.getExtended() == Value.kReverse) {
      Robot.m_ClimbFrontS.retractFront();
      Robot.m_ClimbRearS.retractRear();
    }
    else if (Robot.m_ClimbFrontS.getExtended() == Value.kOff && Robot.m_ClimbRearS.getExtended() == Value.kOff) {
      //if both are off, it is probably better to lower than lift
      Robot.m_ClimbFrontS.retractFront();
      Robot.m_ClimbRearS.retractRear();
    }
    else {
      System.out.println("Returns are not equal: FAILED!");
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
