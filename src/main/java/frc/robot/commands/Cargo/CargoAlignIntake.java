/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class CargoAlignIntake extends Command {

  private CommandGroup m_CargoIntakeCommandGroup;
  private Command m_CargoIntakeCommand;

  public CargoAlignIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivebaseS);

    m_CargoIntakeCommandGroup = new CargoIntakeCG();
    m_CargoIntakeCommand = new CargoIntakeC();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivebaseS.arcadeDrive(0, 0, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_CargoIntakeCommandGroup.start();
    if(!m_CargoIntakeCommandGroup.isRunning()) {
      Robot.m_drivebaseS.arcadeDrive(Robot.m_oi.xbox.left_stick_y(), 0, 0.5);
      m_CargoIntakeCommand.start();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
