/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DistanceStraightDriveC extends PIDCommand {
  int leftSpeed = 0;
  int rightSpeed = 0;
  double targetAngle;
  int distance;
  public DistanceStraightDriveC(int distanceCounts, int speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(RobotMap.DRIVE_TURN_PID[0], RobotMap.DRIVE_TURN_PID[1], RobotMap.DRIVE_TURN_PID[2]);
    requires(Robot.m_drivebaseS);
    distance = distanceCounts;
    leftSpeed = speed;
    rightSpeed = speed;
    Robot.m_drivebaseS.resetEncoders();
    Robot.m_drivebaseS.navX.reset();
 }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    setSetpoint(0.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.m_drivebaseS.getLeftEncoder() + Robot.m_drivebaseS.getRightEncoder())/2 >= distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivebaseS.pidDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
    end();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.m_drivebaseS.navX.getYaw();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.m_drivebaseS.pidDrive(leftSpeed - output, rightSpeed + output);
    //Robot.m_drivebaseS.pidDrive(leftSpeed, rightSpeed);
    SmartDashboard.putNumber("Yaw PID Output", output);
    SmartDashboard.putNumber("Drivebase Yaw Error", Robot.m_drivebaseS.navX.getYaw());
  }
}
