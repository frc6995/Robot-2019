package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoShooterC extends Command {
  public CargoShooterC() {
    requires(Robot.m_CargoShooterS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(1);
  }

  @Override
  protected void execute() {
    System.out.println("Cargo Shoot running");
    Robot.m_CargoShooterS.setSpeed(1);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
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