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
  double KpAim = -0.025f;
  double KpDistance = -0.08f;
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
    
  }

  @Override
  protected void execute() {
    pipelineEntry.setDouble(0);  //Sets pipeline to Vision Align pipeline
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
    double max_power = 0.5;

    //basic proportional control
    steering_adjust = heading_error * KpAim; 
    distance_adjust = KpDistance * distance_error;

    //Adds a maximum and miniumum to the Robots turning speed
    if (steering_adjust > max_power) {
      steering_adjust = max_power;
    }
    else if (steering_adjust < -max_power) {
      steering_adjust = -max_power;
    }
    //Adds a maximum/minimum to the forward-backward speed
    if (distance_adjust > max_power) {
      distance_adjust = max_power;
    }
    else if (distance_adjust < -max_power) {
      distance_adjust = -max_power;
    }
    SmartDashboard.putNumber("Vision Steer Adj", steering_adjust);
    SmartDashboard.putNumber("Vision Dist Adj", distance_adjust);
    Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
  }

  @Override
  protected boolean isFinished() {
    tx = txEntry.getDouble(0.0);
    ty = tyEntry.getDouble(0.0);

    SmartDashboard.putNumber("sumInRange", sumInRange);
    
    if (Math.abs(tx) <= 1 && Math.abs(ty) <= 1) {
      sumInRange+=1;
      //return true;     
    }
    else {
      sumInRange = 0;
      //return false;
    } 
    
    if(sumInRange>20){
      pipelineEntry.setDouble(1);
      sumInRange = 0;
      return true;
    }else {
      pipelineEntry.setDouble(0);
      return false;
    }
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
