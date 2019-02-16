package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForTimeC extends Command {
  public DriveForTimeC(double time) {
    requires(Robot.m_drivebaseS);
    this.setTimeout(time);
    this.setInterruptible(false); //This command cannot be overruled by other commands (it can still be canceled though)
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivebaseS.visionDrive(0.1, 0); //Moves the robot really slowly straight forwards
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
