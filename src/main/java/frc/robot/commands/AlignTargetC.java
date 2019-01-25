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
  NetworkTableEntry camMode = table.getEntry("camMode");
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
  double KpAim = -0.035f;
  double KpDistance = -0.1f;
  double min_aim_command = 0.05f;
  double cam = 0.0;
  double tx= 0.0;
  double ty = 0.0;
  double left_command = 0.0;
  double right_command = 0.0;
  double heading_error = 0.0;
  double distance_error = 0.0;
  double steering_adjust = 0.0;
  double distance_adjust = 0.0;
  double pipeline = 0.0;

  public AlignTargetC(double pipeline) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivebaseS);
    this.pipeline = pipeline;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(SmartDashboard.getNumber("kpAim", -2000.0) == -2000.0){
      SmartDashboard.putNumber("kpAim", KpAim);
    }
    if(SmartDashboard.getNumber("kpDistance", -2000.0) == -2000.0){
      SmartDashboard.putNumber("kpDistance", KpDistance);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  cam = camMode.getDouble(0); //shueja-personal:Unused

        tx = txEntry.getDouble(0.0); //get offsets from limelight
        ty = tyEntry.getDouble(0.0);

        SmartDashboard.putNumber("Tx", tx);
        SmartDashboard.putNumber("Ty", ty);
        KpAim = SmartDashboard.getNumber("kpAim", KpAim); //pull control constants from smartdashboard.
        KpDistance = SmartDashboard.getNumber("kpDistance", KpDistance);

        double heading_error = -tx;
        double distance_error = ty; //shueja-personal: Might be negative, need to test.

        steering_adjust = heading_error * KpAim; //basic proportional control
        distance_adjust = KpDistance * distance_error;
        
        
        SmartDashboard.putNumber("Steering", steering_adjust);

        /*left_command = steering_adjust;// + distance_adjust;
        SmartDashboard.putNumber("left_command", left_command);

        right_command = steering_adjust;// + distance_adjust;
        SmartDashboard.putNumber("right_command", right_command);*/ //shueja-personal: Unused
        Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
}

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //tx = txEntry.getDouble(0.0);
    //ty = tyEntry.getDouble(0.0);
    /*if (tx == 0.0 && ty == 0.0) {
      return true;
    }
    else {
      return false;
    }*/
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
