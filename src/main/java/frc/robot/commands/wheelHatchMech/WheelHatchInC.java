package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WheelHatchInC extends Command {
  private double intakePower = -0.7;

  public WheelHatchInC() {
    requires(Robot.m_WheelHatchMechS);
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Run wheels in
    Robot.m_WheelHatchMechS.setPower(intakePower);
  }

  @Override
  protected boolean isFinished() {
    //Finish when the hatch mech is pressed
    return false;//Robot.m_WheelHatchMechS.hasHatch();
  }

  @Override
  protected void end() {
    Robot.m_WheelHatchMechS.setPower(0.1);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
