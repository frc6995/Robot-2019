package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * IMPORTANT!!! MAKE SURE "IGNORE NETWORK TABLES" IS SET TO FALSE.
 * To set to false, Open Shuffleboard, Click on File, Preferences, Camera Server ??? Need to confirm
 */
public class VisionAlignTargetC extends Command {
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

  public VisionAlignTargetC() {
    requires(Robot.m_drivebaseS);
    pipelineEntry.setDouble(0);
  }

  @Override
  protected void initialize() {
    this.setInterruptible(false); //Prevents drivebase from overriding this command.
    pipelineEntry.setDouble(0); //Sets pipeline to Vision Align pipeline
  }

  @Override
  protected void execute() {
    cam = camMode.getDouble(0);
    tx = txEntry.getDouble(0.0); //get offsets from limelight
    ty = tyEntry.getDouble(0.0);
    ta = taEntry.getDouble(0.0);

    SmartDashboard.putNumber("Vision Tx", tx);
    SmartDashboard.putNumber("VisionTy", ty);
    SmartDashboard.putNumber("VisionTa", ta);
    SmartDashboard.putNumber("Vision Heading error", heading_error);
    //Pull control constants from smartdashboard.
    KpAim = SmartDashboard.getNumber("Vision kpAim", KpAim); 
    KpDistance = SmartDashboard.getNumber("Vision kpDistance", KpDistance);

    double heading_error = -tx;
    double distance_error = ty;
    double max_steering = 0.1;

    //basic proportional control
    steering_adjust = heading_error * KpAim; 
    distance_adjust = KpDistance * distance_error;

    //Adds a maximum and miniumum to the Robots turning speed
    if (steering_adjust > max_steering) {
      steering_adjust = max_steering;
    }
    else if (steering_adjust < -max_steering) {
      steering_adjust = -max_steering;
    }
    //Adds a maximum/minimum to the forward-backward speed
    if (distance_adjust > max_steering) {
      distance_adjust = max_steering;
    }
    else if (distance_adjust < -max_steering) {
      distance_adjust = -max_steering;
    }
    SmartDashboard.putNumber("Vision Steer Adj", steering_adjust);
    SmartDashboard.putNumber("Vision Dist Adj", distance_adjust);
    Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
  }

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

  @Override
  protected void end() {
    pipelineEntry.setDouble(1);
  } 

  @Override
  protected void interrupted() {
    end();
  }
}
