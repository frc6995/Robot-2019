/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathFollowerC extends Command {
  Trajectory trajectory;
  EncoderFollower left;
  EncoderFollower right;
  TankModifier modifier;
  public PathFollowerC(String pathFileName) {
    requires(Robot.m_drivebaseS);
    trajectory = getTrajectoryFromNameJaci(pathFileName);
    modifier = new TankModifier(trajectory).modify(RobotMap.WHEEL_BASE);

        // Do something with the new Trajectories...
    EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
    EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    left.configureEncoder(Robot.m_drivebaseS.getLeftEncoder(), RobotMap.ENCODER_TICKS, RobotMap.WHEEL_DIAMETER);
    right.configureEncoder(Robot.m_drivebaseS.getRightEncoder(), RobotMap.ENCODER_TICKS, RobotMap.WHEEL_DIAMETER);
    // The first argument is the proportional gain. Usually this will be quite high
    // The second argument is the integral gain. This is unused for motion profiling
    // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
    // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
    //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
    // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
    left.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_VELOCITY, 0);
    right.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_VELOCITY, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double l = left.calculate(Robot.m_drivebaseS.getLeftEncoder());
    double r = right.calculate(Robot.m_drivebaseS.getRightEncoder());
    
    double gyro_heading = Robot.m_drivebaseS.navX.getAngle();// Assuming the gyro is giving a value in degrees
    double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
    
    // This allows the angle difference to respect 'wrapping', where 360 and 0 are the same value
    double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    angleDifference = angleDifference % 360.0;
    if (Math.abs(angleDifference) > 180.0) {
      angleDifference = (angleDifference > 0) ? angleDifference - 360 : angleDifference + 360;
    } 
    
    double turn = 0.8 * (-1.0/80.0) * angleDifference;
    
    Robot.m_drivebaseS.autoDrive(l+turn, r-turn);
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

  public static Trajectory getTrajectoryFromNameJaci(String trajectoryName)
  {
      File trajectoryFile = new File("/home/lvuser/paths/" + trajectoryName + ".csv");

      Trajectory trajectory; // = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile) : null;
      try{
          trajectory = Pathfinder.readFromCSV(trajectoryFile);
      } catch (IOException e) {
          trajectory = null;
      } 
      
      if(trajectory == null)
      {
          System.out.println("FILE DOES NOT EXIST");
          // trajectoryFile = new File("C:\\Users\\brian\\OneDrive\\Projects\\FRC_2018_Offseason\\PathPlanner\\Trajectories\\" + trajectoryName + "\\" + trajectoryName + "_source_detailed.traj");
          // trajectory = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile): null;
      }
      else
          System.out.println("CSV READ SUCCESSFUL");

      return trajectory;
  }
}
