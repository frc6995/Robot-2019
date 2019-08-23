package frc.robot.autonomous;

import frc.robot.Robot;

import frc.robot.subsystems.DrivebaseS;
import frc.robot.Units;
import jaci.pathfinder.Trajectory;

public class Odometry 
{
    private volatile double x, y, theta;
    private volatile double currentEncoderPosition, lastPosition, deltaPosition;

    private static Odometry instance;

    private Odometry()
    {
        this.x = 0;
        this.y = 0;
        this.theta = 0;

        this.currentEncoderPosition = 0;
        this.lastPosition = 0;
        this.deltaPosition = 0;
    }

    public synchronized static Odometry getInstance()
    {
        if(instance == null)
        {
            instance = new Odometry();
        }

        return instance;
    }

    public void odometryInit()
    {
        lastPosition = 0;
        // odoThread = new Notifier(() ->                                     //create a notifier event
        // {
        //     currentEncoderPosition = (DrivebaseS.leftSRX.getSelectedSensorPosition(0) + DrivebaseS.rightSRX.getSelectedSensorPosition(0)) / 2.0;
        //     deltaPosition = Units.ticksToMeters(currentEncoderPosition - lastPosition); // delta position calculated by
        //     // difference in encoder ticks
        //     theta = Math.toRadians(Robot.gyro.getYaw()); // Gyro angle in Radians
        //     x += Math.cos(theta) * deltaPosition; // Getting x position from cosine of the change in position
        //     y += Math.sin(theta) * deltaPosition; //Getting y position from sine of the change in position
            
        //     lastPosition = currentEncoderPosition;
        // });
        // odoThread.startPeriodic(0.01);                                              //run the notifier event periodically requeued every .01 seconds
    }

    public void runOdometry()
    {
        currentEncoderPosition = (frc.robot.subsystems.DrivebaseS.leftEncoder + DrivebaseS.rightEncoder) / 2.0;
        deltaPosition = Units.ticksToMeters(currentEncoderPosition - lastPosition); // delta position calculated by
        // difference in encoder ticks
        theta = Math.toRadians(DrivebaseS.navX.getYaw());// Gyro angle in Radians
        x += Math.cos(theta) * deltaPosition; // Getting x position from cosine of the change in position
        y += Math.sin(theta) * deltaPosition; //Getting y position from sine of the change in position
        
        lastPosition = currentEncoderPosition;
    }

    public double getX() 
    {
        return x;
    }

    public double getY() 
    {
        return y;
    }

    public double getTheta() 
    {
        return theta % (2 * Math.PI);
    }

    public synchronized void setX(double x)
    {
        this.x = x;
    }

    public synchronized void setY(double y)
    {
        this.y = y;
    }

    public synchronized void addX(double x)
    {
        this.x += x;
    }

    public synchronized void addY(double y)
    {
        this.y += y;
    }

    public synchronized void setTheta(double theta)
    {
        this.theta = theta;
    }

    public synchronized void addTheta(double theta)
    {
        this.theta += theta;
    }

    public double getCurrentEncoderPosition() 
    {
        return currentEncoderPosition;
    }

    public double getLastPosition() 
    {
        return lastPosition;
    }

    public double getDeltaPosition()
    {
        return deltaPosition;
    }

    public synchronized void setCurrentEncoderPosition(double currentEncoderPosition) 
    {
        this.currentEncoderPosition = currentEncoderPosition;
    }

    public synchronized void setLastPosition(double lastPosition) 
    {
        this.lastPosition = lastPosition;
    }

    public synchronized void setDeltaPosition(double deltaPosition) 
    {
        this.deltaPosition = deltaPosition;
    }

    public void setInitialOdometry(Trajectory trajectory)
    {
        setX(trajectory.get(0).x);
        setY(trajectory.get(0).y);
        DrivebaseS.navX.setAngleAdjustment(Math.toDegrees(trajectory.get(0).heading));
        setTheta(trajectory.get(0).heading);
    }

    public String toString()
    {
        return "X Position: " + x + " Y Position: " + y + " Heading: " + theta;
    }
}
