package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoScoreC extends Command {
  private double shootSpeed = 0.5;

  public CargoScoreC() {
    requires(Robot.m_CargoShooterS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(1);
  }

  @Override
  protected void execute() {
    Robot.m_CargoShooterS.setSpeed(shootSpeed);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
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