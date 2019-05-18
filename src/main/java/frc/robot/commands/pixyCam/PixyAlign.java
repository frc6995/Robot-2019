/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pixycam;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PixyAlign extends Command {

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

  public PixyAlign() {
    requires(Robot.m_pixyCamS);
    requires(Robot.m_drivebaseS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.setInterruptible(false);
    firstLoop = true;
  }

  // Called repeatedly when this Command is scheduled to run
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

  // Make this return true when this Command no longer needs to run execute()
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

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Resets things
    rampTimer.stop();
    firstLoop = true;
    Robot.m_oi.xbox.setRumble(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  private static double clamp(double val, double mag) {
    return Math.max(-mag, Math.min(mag, val));
  }
}