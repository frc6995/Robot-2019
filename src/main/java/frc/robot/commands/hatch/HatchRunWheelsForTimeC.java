package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchRunWheelsForTimeC extends Command {
  private double power;

  public HatchRunWheelsForTimeC(double power, double timeout) {
    requires(Robot.m_hatchMechWheelsS);
    this.power = power;

    //Sets the timeout
    this.setTimeout(timeout);
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_hatchMechWheelsS.setPower(this.power);
  }

  @Override
  protected boolean isFinished() {
    //End if the command is timed out
    return isTimedOut();
  }

  @Override
  protected void end() {
    //Stops the motors
    Robot.m_hatchMechWheelsS.setPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}