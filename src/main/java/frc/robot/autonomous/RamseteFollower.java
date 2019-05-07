package frc.robot.autonomous;

import frc.robot.Robot;
import frc.robot.autonomous.Constants;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;


/**
 * Ramsete implementation by Brian for Team 321 based on Aaron's implementation
 * with help from Prateek and all on the FIRST programming discord server
 * yeeted by Kunal for Team 3647 (with permission of course)
 */
public class RamseteFollower 
{

    // Should be greater than zero and this increases correction
    private double kBeta = Constants.kBeta; //1.5;

    // Should be between zero and one and this increases dampening
    private double kZeta = Constants.kZeta; //0.7;

    // Holds what segment we are on
    private int segmentIndex;
    private Segment current;

    // The trajectory to follow
    private Trajectory trajectory;

    // The robot's x and y position and angle
    private Odometry odometry;

    // Variable used to calculate linear and angular velocity
    private double lastTheta, nextTheta;
    private double k, thetaError, sinThetaErrorOverThetaError;
    public double desiredAngularVelocity, linearVelocity, angularVelocity;
    private double odometryError;

    // Constants
    private static final double EPSILON = 0.00000001;
    private static final double TWO_PI = 2 * Math.PI;

    // Variable for holding velocity for robot to drive on
    private Velocity velocity;
    private DriveSignal driveSignal;
    private double left, right;


    public RamseteFollower(Trajectory trajectory, MotionProfileDirection direction) 
    {
        //ternary operator, if direction is forward return trajectory, else return the reversed path
        this.trajectory = direction == MotionProfileDirection.FORWARD ? trajectory : TrajectoryUtil.reversePath(trajectory);
        // this.trajectory = TrajectoryUtil.correctPath(trajectory);

        segmentIndex = 0;
        odometry = Odometry.getInstance();

        driveSignal = new DriveSignal();
    }

    public RamseteFollower(Trajectory trajectory, double b, double zeta, MotionProfileDirection direction) 
    {
        this(trajectory, direction);

        this.kBeta = b;
        this.kZeta = zeta;
    }

    public Velocity getVelocity() 
    {
        if (isFinished()) 
        {
            return new Velocity(0, 0);
        }

        current = trajectory.get(segmentIndex);

        desiredAngularVelocity = calculateDesiredAngular();

        linearVelocity = calculateLinearVelocity(current.x, current.y, current.heading, current.velocity, desiredAngularVelocity);

        angularVelocity = calculateAngularVelocity(current.x, current.y, current.heading, current.velocity, desiredAngularVelocity);

        return new Velocity(linearVelocity, angularVelocity);
    }

    public DriveSignal getNextDriveSignal() 
    {
        velocity = getVelocity();

        left = (-(velocity.getAngular() * Constants.kWheelBase) + (2 * velocity.getLinear())) / 2;
        right = ((velocity.getAngular() * Constants.kWheelBase) + (2 * velocity.getLinear())) / 2;

        driveSignal.setLeft(left);
        driveSignal.setRight(right);

        segmentIndex++;

        return driveSignal;
    }

    private double calculateDesiredAngular() 
    {
        if (segmentIndex < trajectory.length() - 1) 
        {
            lastTheta = trajectory.get(segmentIndex).heading;
            nextTheta = trajectory.get(segmentIndex + 1).heading;
            return boundHalfRadians(nextTheta - lastTheta) / current.dt;
        } 
        else 
        {
            return 0;
        }
    }

    private double calculateLinearVelocity(double desiredX, double desiredY, double desiredTheta,
            double desiredLinearVelocity, double desiredAngularVelocity) 
    {
        // System.out.println("DesiredLinearVelocity: " + desiredLinearVelocity);
        // System.out.println("Desired X: " + desiredX);
        // System.out.println("Actual X : " + odometry.getX());
        // System.out.println("thetaError: " + thetaError);
        k = calculateK(desiredLinearVelocity, desiredAngularVelocity);

        thetaError = boundHalfRadians(desiredTheta - odometry.getTheta());

        odometryError = (Math.cos(odometry.getTheta()) * (desiredX - odometry.getX()))
                + (Math.sin(odometry.getTheta()) * (desiredY - odometry.getY()));

        // System.out.println("odometryError: " + (odometryError));

        return (desiredLinearVelocity * Math.cos(thetaError)) + (k * odometryError);
    }

    private double calculateAngularVelocity(double desiredX, double desiredY, double desiredTheta,
            double desiredLinearVelocity, double desiredAngularVelocity) 
    {
        k = calculateK(desiredLinearVelocity, desiredAngularVelocity);

        thetaError = boundHalfRadians(desiredTheta - odometry.getTheta());

        if (Math.abs(thetaError) < EPSILON) 
        {
            // This is for the limit as sin(x)/x approaches zero
            sinThetaErrorOverThetaError = 1;
        } 
        else 
        {
            sinThetaErrorOverThetaError = Math.sin(thetaError) / thetaError;
        }

        odometryError = (Math.cos(odometry.getTheta()) * (desiredY - odometry.getY()))
                - (Math.sin(odometry.getTheta()) * (desiredX - odometry.getX()));

        return desiredAngularVelocity + (kBeta * desiredLinearVelocity * sinThetaErrorOverThetaError * odometryError)
                + (k * thetaError);
    }

    private double calculateK(double desiredLinearVelocity, double desiredAngularVelocity) 
    {
        return 2 * kZeta * Math.sqrt(Math.pow(desiredAngularVelocity, 2) + (kBeta * Math.pow(desiredLinearVelocity, 2)));
    }

    private double boundHalfRadians(double radians) 
    {
        while (radians >= Math.PI)
            radians -= TWO_PI;
        while (radians < -Math.PI)
            radians += TWO_PI;
        return radians;
    }

    public Segment currentSegment() 
    {
        return current;
    }

    public boolean isFinished() 
    {
        return segmentIndex >= trajectory.length();
    }

    public void printOdometry()
    {
        System.out.println("Segment index: " + segmentIndex);
        // // System.out.println("Trajectory.length: " + trajectory.length());
        // if(odometry.getX() < 3)
        //     System.out.println(odometry.getX());
        // else
        //     System.out.println("PASSED 3");
    }

    public int getSegmentIndex()
    {
        return this.segmentIndex;
    }

    public boolean pathFractionSegment(double fraction)
    {
        return (this.segmentIndex > trajectory.length()*fraction) && (this.segmentIndex < trajectory.length());
    }

    public void printDeltaDist()
    {
        System.out.println("Delta position in meters: " + odometry.getDeltaPosition());
    }

    public void printCurrentEncoders()
    {
        System.out.println("Odometry encoder: " + odometry.getCurrentEncoderPosition());
        System.out.println("Actual encoder left: " + Robot.m_drivebaseS.getDrivebaseRightEncoderCount());
        System.out.println("Actual encoder right: " + Robot.m_drivebaseS.getDrivebaseRightEncoderCount());
    }
}