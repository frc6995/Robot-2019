package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * 
 * IMPORTANT!!! MAKE SURE "IGNORE NETWORK TABLES" is set to false.
 */
public class AlignTargetC extends Command {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tyEntry = table.getEntry("ty");
  NetworkTableEntry taEntry = table.getEntry("ta");
  NetworkTableEntry camMode = table.getEntry("camMode");
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
  double KpAim = -0.035f;
  double KpDistance = -0.1f;
  double min_aim_command = 0.05f;
  double cam = 0.0;
  double tx= 0.0;
  double ty = 0.0;
  double ta = 0.0;
  double left_command = 0.0;
  double right_command = 0.0;
  double heading_error = 0.0;
  double heading_sum_error = 0.0;
  double distance_error = 0.0;
  double distance_sum_error = 0.0;


  double steering_adjust = 0.0;
  double distance_adjust = 0.0;
  double pipeline = 0.0;
  int sumInRange = 0;

  

  public AlignTargetC() {
    requires(Robot.m_drivebaseS);
    pipelineEntry.setDouble(0);

  }

  @Override
  protected void initialize() {
    this.setInterruptible(false); //Prevents drivebase from overriding this command.
    pipelineEntry.setDouble(0); //Sets pipeline to Vision Align pipeline
    // pipeline 0 is the 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      cam = camMode.getDouble(0);
        tx = txEntry.getDouble(0.0); //get offsets from limelight
        ty = tyEntry.getDouble(0.0);
        ta = taEntry.getDouble(0.0);

        SmartDashboard.putNumber("Tx", tx);
        SmartDashboard.putNumber("Ty", ty);
        SmartDashboard.putNumber("Ta", ta);
        SmartDashboard.putNumber("Heading error", heading_error);
        KpAim = SmartDashboard.getNumber("kpAim", KpAim); //pull control constants from smartdashboard.
        KpDistance = SmartDashboard.getNumber("kpDistance", KpDistance);

        double heading_error = -tx;
        double distance_error = ty; //shueja-personal: Might be negative, need to test.

        steering_adjust = heading_error * KpAim; //basic proportional control
        distance_adjust = KpDistance * distance_error;
        
        double max_steering = 0.75;

        if (steering_adjust > max_steering) {
          steering_adjust = max_steering;
        }
        else if (steering_adjust < -max_steering) {
          steering_adjust = -max_steering;
        }
        if (distance_adjust > max_steering) {
          distance_adjust = max_steering;
        }
        else if (distance_adjust < -max_steering) {
          distance_adjust = -max_steering;
        }
        //Adds a maximum and miniumum to the Robots turning speed
        //and adds a maximum/minimum to the forward-backward speed
 
        SmartDashboard.putNumber("Steering", steering_adjust);

        Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
      }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    tx = txEntry.getDouble(0.0);
    ty = tyEntry.getDouble(0.0);
    if (Math.abs(tx) <= 0.5 && Math.abs(ty) <= 1) {
     // sumInRange+=1;

      return true;     
    }
    else {
      return false;
    }/*   else if(sumInRange>40){
      pipelineEntry.setDouble(1);
      return true;
    }
    else {
      sumInRange = 0;
      pipelineEntry.setDouble(0);
      return false;
    }*/
   // return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pipelineEntry.setDouble(1);
  } 
  @Override
  protected void interrupted() {
  }
}
