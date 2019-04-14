package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntakeC extends Command {
  public CargoIntakeC() {
    requires(Robot.m_CargoShooterS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoShooterS.setSpeed(-0.5);
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoShooterS.getCargoLimit() || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_CargoShooterS.setSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}