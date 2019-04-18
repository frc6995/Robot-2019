package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * IMPORTANT!!! MAKE SURE "IGNORE NETWORK TABLES" IS SET TO FALSE.
 * To verify, connect to the limelight through the web interface and look for a button in the top middle
 */
public class VisionAlignTargetC extends Command {
  //Network tables
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tyEntry = table.getEntry("ty");
  NetworkTableEntry ledMode = table.getEntry(("ledMode"));
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
  NetworkTableEntry camMode = table.getEntry("camMode");

  //CONSTANTS
  //Change these if the align method is going too fast or too slow (or is oscilating)
  double KpAim = -0.005f;
  double KpDistance = -0.04f;
  double max_power = 0.35; //clamp
  double rampTime = 0.75; //seconds

  //Change these if the align method is stopping too soon or not soon enough
  double xRange = 4; //degrees
  double yRange = 5; //degrees
  double waitInRange = 10; //cycles

  //Variables that are set within the command
  double tx= 0.0;
  double ty = 0.0;
  double ta = 0.0;
  double steering_adjust = 0.0;
  double distance_adjust = 0.0;
  double pipeline = 0.0;

  boolean firstLoop = true;
  boolean rocketCargo = false;

  double clampValue = 0;
  double heading_error = 0;
  double distance_error = 0;

  int sumInRange = 0;

  Timer rampTimer = new Timer();


  public VisionAlignTargetC(boolean rocketCargo) {
    requires(Robot.m_drivebaseS);
    requires(Robot.m_limelight);
    pipelineEntry.setDouble(RobotMap.PIPELINE_DRIVER_CAM); //Normal camera
    ledMode.setDouble(0); //Leds off

    //Puts constants on smart dashboard
    SmartDashboard.putNumber("kpAim", KpAim);
    SmartDashboard.putNumber("kpDistance", KpDistance);
    SmartDashboard.putNumber("max_power", max_power);
    SmartDashboard.putNumber("xRange", xRange);
    SmartDashboard.putNumber("yRange", yRange);
    SmartDashboard.putNumber("waitInRange", waitInRange);

    this.rocketCargo = rocketCargo;
  }

  @Override
  protected void initialize() {
    //To interrupt this command, use the toggle feature
    this.setInterruptible(false); //Prevents drivebase from overriding this command.
    firstLoop = true;
  }

  @Override
  protected void execute() {
    //Rumbles the controller to let the driver know he doesn't have drivebase control
    Robot.m_oi.xbox.setRumble(0.2);

    //Only runs on the first excecute loop
    if(firstLoop) {
      //Starts the power ramp up timer
      rampTimer.reset();
      rampTimer.start();

      firstLoop = false;
    }

    //Sets the pipeline depending if we are aligning with Cargo on Rocket ship (target is higher)
    //or with Cargo Ship, Hatch on Rocket, or Hatch/Cargo at intake.
    if(rocketCargo){
      pipelineEntry.setDouble(RobotMap.PIPELINE_UPPER_TARGET); //Rocket cargo
    }else{
      pipelineEntry.setDouble(RobotMap.PIPELINE_LOWER_TARGET); //Everything else
    }

    //Force the limelight LED on
    ledMode.setDouble(3);

    //Get offsets from limelight
    tx = txEntry.getDouble(0.0);
    ty = tyEntry.getDouble(0.0);

    SmartDashboard.putNumber("Vision Heading error", heading_error);
    SmartDashboard.putNumber("Vision Distance error", heading_error);
    SmartDashboard.putNumber("RampTimer", rampTimer.get());

    //Pull control constants from smartdashboard. COMMENT WHEN WE ARE DONE TESTING
    //KpAim = SmartDashboard.getNumber("kpAim", KpAim);
    //KpDistance = SmartDashboard.getNumber("kpDistance", KpDistance);
    //max_power = SmartDashboard.getNumber("max_power", max_power);
    //xRange = SmartDashboard.getNumber("xRange", xRange);
    //yRange = SmartDashboard.getNumber("yRange", yRange);
    //waitInRange = SmartDashboard.getNumber("waitInRange", waitInRange);

    heading_error = -tx;
    distance_error = -ty;

    //Basic proportional control
    steering_adjust = heading_error * KpAim;
    distance_adjust = KpDistance * distance_error;

    //Ramps our PID to full over the period of ramp time
    clampValue = clamp(rampTimer.get()/rampTime, 1) * max_power;

    //Enforces the max power
    steering_adjust = clamp(steering_adjust, clampValue);
    distance_adjust = clamp(distance_adjust, clampValue);

    SmartDashboard.putNumber("Vision Steer Adj", steering_adjust);
    SmartDashboard.putNumber("Vision Dist Adj", distance_adjust);

    Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
  }

  @Override
  protected boolean isFinished() {
    //If we are within range, begin to add to the sumInRange command
    SmartDashboard.putNumber("sumInRange", sumInRange);
    if (Math.abs(tx) <= xRange && Math.abs(ty) <= yRange) {
      sumInRange+=1;
    } else {
      sumInRange = 0;
    }

    //If we are aligned, end the command
    if (sumInRange>waitInRange){
      sumInRange = 0;
      return true;
    } else {
      return false;
    }
    
  }

  @Override
  protected void end() {
    //Resets things
    rampTimer.stop();
    firstLoop = true;
    pipelineEntry.setDouble(RobotMap.PIPELINE_DRIVER_CAM);
    ledMode.setDouble(0);
    Robot.m_oi.xbox.setRumble(0);
  }

  @Override
  protected void interrupted() {
    end();
  }

  //Clamps a value between -mag and +mag
  private static double clamp(double val, double mag) {
    return Math.max(-mag, Math.min(mag, val));
  }
}
