package frc.robot.commands.pixycam;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PixyAlignC extends Command {

  double KpAim = -0.01f;
  double KpDistance = -0.04f;
  double max_power = 0.35; //clamp
  double rampTime = 0.75; //seconds

  double xRange = 4; //degrees
  double yRange = 5; //degrees
  double waitInRange = 10; //cycles


  double errorX= 0.0;
  double errorY = 0.0;
  double ta = 0.0;
  double heading_error = 0.0;
  double distance_error = 0.0;
  double steering_adjust = 0.0;
  double distance_adjust = 0.0;

  boolean firstLoop = true;

  int sumInRange = 0;

  Timer rampTimer = new Timer();

  public PixyAlignC() {
    requires(Robot.m_pixyCamS);
    requires(Robot.m_drivebaseS);
  }

  @Override
  protected void initialize() {
    this.setInterruptible(false);
    firstLoop = true;
  }

  @Override
  protected void execute() {
    Robot.m_oi.xbox.setRumble(0.2);

    Robot.m_pixyCamS.read();
    double offset = Robot.m_pixyCamS.getLastOffset();
    double offsetY = Robot.m_pixyCamS.getLastOffsetY();
    double errorX = offset - 160;
    double errorY = offsetY - 100;

    double heading_error = -errorX;
    double distance_error = -errorY;

    steering_adjust = heading_error * KpAim;
    distance_adjust = distance_error * KpDistance;

    double clampValue = clamp(rampTimer.get()/rampTime, 1) * max_power;

    //Enforces the max power
    steering_adjust = clamp(steering_adjust, clampValue);
    distance_adjust = clamp(distance_adjust, clampValue);

    Robot.m_drivebaseS.visionDrive(distance_adjust, steering_adjust);
  }

  @Override
  protected boolean isFinished() {
    if (Math.abs(errorX) <= xRange && Math.abs(errorY) <= yRange) {
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
    Robot.m_oi.xbox.setRumble(0);
  }

  @Override
  protected void interrupted() {
    end();
  }

  private static double clamp(double val, double mag) {
    return Math.max(-mag, Math.min(mag, val));
  }
}