
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchMechDeployC extends Command {
  public HatchMechDeployC() {
    requires(Robot.m_hatchMechS);
  }

  @Override
  protected void initialize() {
  }


  @Override
  protected void execute() {
    //Deploy the hatch mech
    Robot.m_hatchMechS.deploy();
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
