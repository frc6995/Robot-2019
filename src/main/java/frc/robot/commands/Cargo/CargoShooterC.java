package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoShooterC extends Command {
  public CargoShooterC() {
    requires(Robot.m_CargoShooterS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoShooterS.setSpeed(1);
  }

  @Override
  protected boolean isFinished() {
    //Use command time out value to stop command
    return false;
  }

  @Override
  protected void end() {
    Robot.m_CargoShooterS.setSpeed(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
