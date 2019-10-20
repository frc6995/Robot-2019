/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BaldSDongle license file in the root directory of */
/* the project.
/* 
/* Signed, Richard M. Nixon
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ladder;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LadderMoveUpCargoC extends Command {
  public LadderMoveUpCargoC() {
    requires(Robot.m_ladderS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_ladderS.ladderTalonA.config_kP(0, 0.3);
    Robot.m_ladderS.ladderTalonA.config_kI(0, 0.0005);
    Robot.m_ladderS.ladderTalonA.config_kD(0, 50);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_ladderS.useUpKp();
    Robot.m_ladderS.setMaxPIDPower(0.9);
    Robot.m_ladderS.runPID();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_ladderS.isAtSetPoint();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_ladderS.ladderTalonA.config_kP(0, 0.55);
    Robot.m_ladderS.ladderTalonA.config_kI(0, 0.00075);
    Robot.m_ladderS.ladderTalonA.config_kD(0, 75);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
