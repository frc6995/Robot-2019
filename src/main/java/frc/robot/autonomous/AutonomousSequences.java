package frc.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.autonomous.Constants;;
import frc.robot.Robot;
import frc.robot.autonomous.*;
import jaci.pathfinder.Trajectory;

public class AutonomousSequences 
{
	
	public static int autoStep = 0;
	private static Timer autoTimer = new Timer(); 


	public static DriveSignal driveSignal;
	public static Trajectory trajectory;
	public static RamseteFollower ramseteFollower;
	public static Trajectory.Segment current;
	public static double leftSpeed;
	public static double rightSpeed;
	public static double linearVelocity;
	public static double visionVelocityConstant = 2500;

	public static void autoInitFWD(String trajectoryName) 
	{
		Robot.m_drivebaseS.selectPIDF(Robot.autonomous.Constants.velocitySlotIdx, Constants.rightVelocityPIDF, Constants.leftVelocityPIDF);
		driveSignal = new DriveSignal();
		trajectory = TrajectoryUtil.getTrajectoryFromName(trajectoryName);
		ramseteFollower = new RamseteFollower(trajectory, MotionProfileDirection.FORWARD);
		Odometry.getInstance().setInitialOdometry(trajectory);
		Odometry.getInstance().odometryInit();
		autoTimer.stop();
		autoTimer.reset();
		// autoTimer.start();
		autoStep = 0;
	}

	public static void autoInitFWD2(String trajectoryName) 
	{
		Drivetrain.selectPIDF(Constants.velocitySlotIdx, Constants.rightVelocityPIDF, Constants.leftVelocityPIDF);
		driveSignal = new DriveSignal();
		trajectory = TrajectoryUtil.getTrajectoryFromName(trajectoryName);
		ramseteFollower = new RamseteFollower(trajectory, MotionProfileDirection.FORWARD);
	}

	public static void autoInitBWD(String trajectoryName) 
	{
		Drivetrain.selectPIDF(Constants.velocitySlotIdx, Constants.rightVelocityPIDF, Constants.leftVelocityPIDF);
		driveSignal = new DriveSignal();
		trajectory = TrajectoryUtil.getTrajectoryFromName(trajectoryName);
		ramseteFollower = new RamseteFollower(trajectory, MotionProfileDirection.BACKWARD);
	}

	public static void testing()
	{
		driveSignal = ramseteFollower.getNextDriveSignal();
    	current = ramseteFollower.currentSegment();

    	rightSpeed = Units.metersToEncoderTicks(driveSignal.getRight() / 10);
    	leftSpeed = Units.metersToEncoderTicks(driveSignal.getLeft() / 10);

		if(ramseteFollower.isFinished())
		{
			ramseteFollower = new RamseteFollower(trajectory, MotionProfileDirection.BACKWARD);
			Odometry.getInstance().setInitialOdometry(TrajectoryUtil.reversePath(trajectory));
		}
	}

	public static void ramsetePeriodic() 
	{
		driveSignal = ramseteFollower.getNextDriveSignal();
		current = ramseteFollower.currentSegment();
		rightSpeed = Units.metersToEncoderTicks(driveSignal.getRight() / 10);
		leftSpeed = Units.metersToEncoderTicks(driveSignal.getLeft() / 10);
		linearVelocity = Units.metersToEncoderTicks(ramseteFollower.linearVelocity / 10);
	}

	public static void runVision()
	{
		ramsetePeriodic();
		if(!ramseteFollower.pathFractionSegment(.5) && !ramseteFollower.isFinished())
		{
			Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
		}
		else if(ramseteFollower.pathFractionSegment(.5))
		{
			limelightClimber.center();
			Drivetrain.setAutoVelocity(leftSpeed + (visionVelocityConstant)*limelightClimber.leftSpeed, rightSpeed + (visionVelocityConstant)*limelightClimber.rightSpeed);
		}
		else if(ramseteFollower.isFinished() && limelightClimber.y > Constants.limelightYOffset)
		{
			limelightClimber.center();
			Drivetrain.setAutoVelocity((visionVelocityConstant)*limelightClimber.leftSpeed, (visionVelocityConstant)*limelightClimber.rightSpeed);
		}
		else
		{
			HatchGrabber.releaseHatch();
			Drivetrain.setAutoVelocity(-500, -500);
		}
	}

	public static void runPath()
	{
		ramsetePeriodic();
		Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
	}

	public static void frontRocketAuto(String LeftOrRight)
	{
		ramsetePeriodic();
		switch(autoStep)
		{
			case 0:
				if(!ramseteFollower.pathFractionSegment(.6) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
				}
				else
				{
					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					else
					{
						limelightClimber.set(VisionMode.kRight);
					}
					autoStep = 1;
				}
				break;
			case 1:
				if(ramseteFollower.pathFractionSegment(.6))
					SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1FORWARDS;
				if(ramseteFollower.pathFractionSegment(.6))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 4;
				}
				break;
			case 4:
				autoInitBWD(LeftOrRight + "FrontRocketToStation");
				limelightClimber.set(VisionMode.kBlack);
				limelightFourBar.set(VisionMode.kRight);
				autoStep = 5;
				break;
			case 5:
				if(!ramseteFollower.pathFractionSegment(.5) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.15))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1BACKWARDS;
						HatchGrabber.stopMotor();
					}
				}
				else
				{
					autoStep = 6;
				}
				break;
			case 6:
				if(ramseteFollower.pathFractionSegment(.5))
				{
					limelightFourBar.center();
					HatchGrabber.grabHatch();
					Drivetrain.setAutoVelocity(linearVelocity +  (visionVelocityConstant)*limelightFourBar.leftSpeed,  linearVelocity + (visionVelocityConstant)*limelightFourBar.rightSpeed);
				}
				else
				{
					autoInitFWD2("StationTo" + LeftOrRight + "FrontRocket");
					limelightFourBar.set(VisionMode.kBlack);

					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					else
					{
						limelightClimber.set(VisionMode.kRight);
					}

					autoStep = 10;
				}
				break;
			case 10:
				if(!ramseteFollower.pathFractionSegment(.5) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.2))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL2FORWARDS;
						HatchGrabber.runConstant();
					}	
				}
				else
				{
					autoStep = 11;
				}
				break;
			case 11:
				if(ramseteFollower.pathFractionSegment(.5))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 13;
				}
				break;
			case 13:
				if(autoTimer.get() < 0.3)
				{
					HatchGrabber.releaseHatch();
					Drivetrain.setAutoVelocity(-500, -500);
				}
				else
				{
					HatchGrabber.stopMotor();
					//Drivetrain.stop();
					autoStep = 14;
					autoTimer.stop();
					autoTimer.reset();
				}
				break;
			case 14:
				Robot.runAuto = false;
				Drivetrain.stop();
				HatchGrabber.stopMotor();
				break;
			default:
				Drivetrain.stop();
				break;
		}
	}

	public static void mixedRocketAuto(String LeftOrRight)
	{
		ramsetePeriodic();
		switch(autoStep)
		{
			case 0:
				if(ramseteFollower.pathFractionSegment(.65))
				{
					SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL2FORWARDS;
				}
				if(!ramseteFollower.pathFractionSegment(.9) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
				}
				else
				{
					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kRight);
					}
					else
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					autoStep = 1;
				}
				break;
			case 1:
				if(ramseteFollower.pathFractionSegment(.8))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 4;
				}
				break;
			case 4:
				autoInitBWD(LeftOrRight + "BackRocketToStation");
				limelightClimber.set(VisionMode.kBlack);
				if(LeftOrRight.equals("Left"))
				{
					limelightFourBar.set(VisionMode.kRight);
				}
				else
				{
					limelightFourBar.set(VisionMode.kLeft);
				}
				autoStep = 5;
				break;
			case 5:
				if(!ramseteFollower.pathFractionSegment(.6) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.15))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1BACKWARDS;
						HatchGrabber.stopMotor();
					}
				}
				else
				{
					autoStep = 6;
				}
				break;
			case 6:
				if(ramseteFollower.pathFractionSegment(.6))
				{
					limelightFourBar.center();
					HatchGrabber.grabHatch();
					Drivetrain.setAutoVelocity(linearVelocity +  (visionVelocityConstant)*limelightFourBar.leftSpeed,  linearVelocity + (visionVelocityConstant)*limelightFourBar.rightSpeed);
				}
				else
				{
					autoInitFWD2("StationTo" + LeftOrRight + "FrontRocket");
					limelightFourBar.set(VisionMode.kBlack);
					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					else
					{
						limelightClimber.set(VisionMode.kRight);
					}
					autoStep = 10;
				}
				break;
			case 10:
				if(!ramseteFollower.pathFractionSegment(.6) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.2))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL2FORWARDS;
						HatchGrabber.runConstant();
					}	
				}
				else
				{
					autoStep = 11;
				}
				break;
			case 11:
				if(ramseteFollower.pathFractionSegment(.6))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 13;
				}
				break;
			case 13:
				if(autoTimer.get() < 0.3)
				{
					HatchGrabber.releaseHatch();
					Drivetrain.setAutoVelocity(-500, -500);
				}
				else
				{
					HatchGrabber.stopMotor();
					//Drivetrain.stop();
					autoStep = 14;
					autoTimer.stop();
					autoTimer.reset();
				}
				break;
			case 14:
				Robot.runAuto = false;
				Drivetrain.stop();
				HatchGrabber.stopMotor();
				break;
			default:
				Drivetrain.stop();
				break;
		}
	}

	public static void sideCargoShipAuto(String LeftOrRight)
	{
		limelightFourBar.set(VisionMode.kClosest);
		limelightFourBar.center();
		
		ramsetePeriodic();
		switch(autoStep)
		{
			case 0:
				if(LeftOrRight.equals("Left"))
				{
					limelightClimber.set(VisionMode.kRight);
				}
				else
				{
					limelightClimber.set(VisionMode.kLeft);
				}
				if(!ramseteFollower.pathFractionSegment(.8) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
				}
				else
				{
					autoStep = 1;
				}
				break;
			case 1:
				if(ramseteFollower.pathFractionSegment(.8))
				{
					SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1FORWARDS;
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 4;
				}
				break;
			case 4:
				autoInitBWD(LeftOrRight + "CargoShipBay1ToStation");
				limelightClimber.set(VisionMode.kBlack);
				if(LeftOrRight.equals("Left"))
				{
					limelightClimber.set(VisionMode.kRight);
				}
				else
				{
					limelightClimber.set(VisionMode.kLeft);
				}
				autoStep = 5;
				break;
			case 5:
				if(!ramseteFollower.pathFractionSegment(.5) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.15))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1BACKWARDS;
						HatchGrabber.runConstant();
					}
				}
				else
				{
					autoStep = 6;
				}
				break;
			case 6:
				if(ramseteFollower.pathFractionSegment(.5))
				{
					limelightFourBar.center();
					HatchGrabber.grabHatch();
					Drivetrain.setAutoVelocity(linearVelocity +  (visionVelocityConstant)*limelightFourBar.leftSpeed,  linearVelocity + (visionVelocityConstant)*limelightFourBar.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					autoInitFWD2("StationTo" + LeftOrRight + "CargoShipBay2");
					limelightFourBar.set(VisionMode.kBlack);
					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kRight);
					}
					else
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					autoStep = 10;
				}
				break;
			case 10:
				if(!ramseteFollower.pathFractionSegment(.82) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.2))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1FORWARDS;
						HatchGrabber.runConstant();
					}	
				}
				else
				{
					autoStep = 11;
				}
				break;
			case 11:
				if(ramseteFollower.pathFractionSegment(.82))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 13;
				}
				break;
			case 13:
				if(autoTimer.get() < 0.3)
				{
					HatchGrabber.releaseHatch();
					Drivetrain.setAutoVelocity(-500, -500);
				}
				else
				{
					HatchGrabber.stopMotor();
					Drivetrain.stop();
					autoStep = 14;
					autoTimer.stop();
					autoTimer.reset();
				}
				break;
			case 14:
				Robot.runAuto = false;
				Drivetrain.stop();
				HatchGrabber.stopMotor();
				break;
			default:
				Drivetrain.stop();
				break;
		}
	}

	public static void mixedCargoShipAuto(String LeftOrRight)
	{
		ramsetePeriodic();
		switch(autoStep)
		{
			case 0:
				if(LeftOrRight.equals("Left"))
				{
					limelightClimber.set(VisionMode.kLeft);
				}
				else
				{
					limelightClimber.set(VisionMode.kRight);
				}
				if(!ramseteFollower.pathFractionSegment(.8) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
				}
				else
				{
					autoStep = 1;
				}
				break;
			case 1:
				if(ramseteFollower.pathFractionSegment(.8))
				{
					SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1FORWARDS;
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoStep = 4;
				}
				break;
			case 4:
				autoInitBWD("Middle" + LeftOrRight + "CargoShipToStation");
				limelightClimber.set(VisionMode.kBlack);
				if(LeftOrRight.equals("Left"))
				{
					limelightClimber.set(VisionMode.kRight);
				}
				else
				{
					limelightClimber.set(VisionMode.kLeft);
				}
				autoStep = 5;
				break;
			case 5:
				if(!ramseteFollower.pathFractionSegment(.6) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.15))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1BACKWARDS;
						HatchGrabber.runConstant();
					}
				}
				else
				{
					autoStep = 6;
				}
				break;
			case 6:
				if(ramseteFollower.pathFractionSegment(.6))
				{
					limelightFourBar.center();
					HatchGrabber.grabHatch();
					Drivetrain.setAutoVelocity(linearVelocity +  (visionVelocityConstant)*limelightFourBar.leftSpeed,  linearVelocity + (visionVelocityConstant)*limelightFourBar.rightSpeed);
				}
				else
				{
					autoInitFWD2("StationTo" + LeftOrRight + "CargoShipBay2");
					limelightFourBar.set(VisionMode.kBlack);
					if(LeftOrRight.equals("Left"))
					{
						limelightClimber.set(VisionMode.kRight);
					}
					else
					{
						limelightClimber.set(VisionMode.kLeft);
					}
					autoStep = 10;
				}
				break;
			case 10:
				if(!ramseteFollower.pathFractionSegment(.82) && !ramseteFollower.isFinished())
				{
					Drivetrain.setAutoVelocity(leftSpeed, rightSpeed);
					if(ramseteFollower.pathFractionSegment(.2))
					{
						SeriesStateMachine.aimedRobotState = ScoringPosition.HATCHL1FORWARDS;
						HatchGrabber.runConstant();
					}	
				}
				else
				{
					autoStep = 11;
				}
				break;
			case 11:
				if(ramseteFollower.pathFractionSegment(.82))
				{
					limelightClimber.center();
					Drivetrain.setAutoVelocity(linearVelocity + (visionVelocityConstant)*limelightClimber.leftSpeed, linearVelocity + (visionVelocityConstant)*limelightClimber.rightSpeed);
				}
				else if(ramseteFollower.isFinished())
				{
					HatchGrabber.releaseHatch();
					autoTimer.stop();
					autoTimer.reset();
					autoStep = 13;
				}
				break;
			case 13:
				if(autoTimer.get() < 0.3)
				{
					HatchGrabber.releaseHatch();
					Drivetrain.setAutoVelocity(-500, -500);
				}
				else
				{
					HatchGrabber.stopMotor();
					Drivetrain.stop();
					autoStep = 14;
					autoTimer.stop();
					autoTimer.reset();
				}
				break;
			case 14:
				Robot.runAuto = false;
				Drivetrain.stop();
				HatchGrabber.stopMotor();
				break;
			default:
				Drivetrain.stop();
				break;
		}
		
	}


}