package frc.robot.commands.wheelHatchMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WheelHatchIntake extends Command {
  private double intakePower = -0.5;

  public WheelHatchIntake() {
    requires(Robot.m_WheelHatchMech);
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Run wheels in
    Robot.m_WheelHatchMech.setPower(intakePower);
  }

  @Override
  protected boolean isFinished() {
    //Finish when the hatch mech is pressed
    return Robot.m_WheelHatchMech.hasHatch();
  }

  @Override
  protected void end() {
    Robot.m_WheelHatchMech.setPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
