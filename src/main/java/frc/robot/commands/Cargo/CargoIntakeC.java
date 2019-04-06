package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoIntakeC extends Command {

  public CargoIntakeC() {
    requires(Robot.m_CargoShooterS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_CargoShooterS.setSpeed(-0.25);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_CargoShooterS.getCargoLimit();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_CargoShooterS.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}