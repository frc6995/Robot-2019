package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchWheelInC extends Command {
  private double intakePower = 0.7;

  public HatchWheelInC() {
    requires(Robot.m_hatchMechWheelsS);
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Run wheels in
    Robot.m_hatchMechWheelsS.setPower(intakePower);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_hatchMechWheelsS.setPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}