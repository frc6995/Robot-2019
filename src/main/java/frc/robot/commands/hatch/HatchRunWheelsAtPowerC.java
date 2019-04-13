package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchRunWheelsAtPowerC extends Command {
  private double power = 0;

  public HatchRunWheelsAtPowerC(double power) {
    requires(Robot.m_hatchMechWheelsS);
    this.power = power;
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Run wheels at set power
    Robot.m_hatchMechWheelsS.setPower(power);
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
    //We actually don't want to call end if we are interupted, 
    //because that could cause the motors to be set to 0 and
    //then back to full power in the same cycle
  }
}
