package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * IMPORTANT!!! MAKE SURE "IGNORE NETWORK TABLES" IS SET TO FALSE.
 * To set to false, Open Shuffleboard, Click on File, Preferences, Camera Server ??? 
 * Need to confirm instructions
 * Can we set this in the code?- SRigg - I couldn't find out how.
 */
public class VisionAlignTargetC extends Command {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tyEntry = table.getEntry("ty");
  NetworkTableEntry taEntry = table.getEntry("ta");
  NetworkTableEntry ledMode = table.getEntry(("ledMode"));
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
  double KpAim = -0.025f;
  double KpDistance = -0.08f;
  double min_aim_command = 0.05f;
  double tx= 0.0;
  double ty = 0.0;
  double ta = 0.0;
  double heading_error = 0.0;
  double distance_error = 0.0;
  double steering_adjust = 0.0;
  double distance_adjust = 0.0;
  double pipeline = 0.0;
  int sumInRange = 0;

  public VisionAlignTargetC() {
    requires(Robot.m_drivebaseS);
    pipelineEntry.setDouble(1);
    ledMode.setDouble(0);
  }

  @Override
  protected void initialize() {
    //To interrupt this command, use the toggle feature
    this.setInterruptible(false); //Prevents drivebase from overriding this command.
  }

  @Override
  protected void execute() {
    pipelineEntry.setDouble(0);  //Sets pipeline to Vision Align pipeline
    ledMode.setDouble(3);        //Force LED Light On.

    //Get offsets from limelight
    tx = txEntry.getDouble(0.0);
    ty = tyEntry.getDouble(0.0);
    ta = taEntry.getDouble(0.0);

    SmartDashboard.putNumber("VisionTx", tx);
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
    }
    else {
      sumInRange = 0;
    } 
    
    if(sumInRange>20){
      sumInRange = 0;
      return true;
    }else {
      return false;
    }
  }

  @Override
  protected void end() {
    pipelineEntry.setDouble(1);
    ledMode.setDouble(0);        //Use LED Mode in Pipeline.
  } 

  @Override
  protected void interrupted() {
    end();
  }
}
