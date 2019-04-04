package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForTimeC extends Command {
  private double drivePower = 0;

  public DriveForTimeC(double time, double power) {
    requires(Robot.m_drivebaseS);
    this.setTimeout(time);
    drivePower = power;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivebaseS.visionDrive(drivePower, 0); //Moves the robot straight forwards
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_drivebaseS.visionDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
