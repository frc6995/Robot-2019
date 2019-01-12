/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AlignTargetC extends Command {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tyEntry = table.getEntry("ty");
  double KpAim = -0.1f;
  double KpDistance = -0.1f;
  double min_aim_command = 0.05f;
  double tx=0.0;
  double ty = 0.0;
  double left_command = 0.0;
  double right_command = 0.0;

  public AlignTargetC() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivebaseS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  tx = txEntry.getDouble(0.0);
  ty = tyEntry.getDouble(0.0);

if (Robot.m_oi.stick.getRawButton(9) || Robot.m_oi.xbox.getRawButton(3));
{
        double heading_error = -tx;
        double distance_error = -ty;
        double steering_adjust = 0.0f;

        if (tx > 1.0)
        {
                steering_adjust = KpAim*heading_error - min_aim_command;
        }
        else if (tx < 1.0)
        {
                steering_adjust = KpAim*heading_error + min_aim_command;
        }

        double distance_adjust = KpDistance * distance_error;

        left_command += steering_adjust + distance_adjust;
        right_command -= steering_adjust + distance_adjust;
        Robot.m_drivebaseS.tankDrive(left_command, right_command);
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
