package frc.robot;

import frc.robot.RobotMap;

public interface Units
{
    public static double ticksToMeters(double ticks)
    {
        return (ticks / RobotMap.ENCODER_TICKS) * (RobotMap.WHEEL_DIAMETER * Math.PI);
    }

    public static double inchesToMeters(double in)
    {
        return in * 0.0254;
    }

    public static double metersToInches(double m)
    {
        return m/0.0254;
    }

    public static double metersToEncoderTicks(double meters)
    {
        return (meters * RobotMap.ENCODER_TICKS) / (Math.PI * RobotMap.WHEEL_DIAMETER );
    }

    public static double feetToEncoderTicks(double feet)
    {
        return (feet * RobotMap.ENCODER_TICKS) / (Math.PI * .5);
    }

    public static double feetToMeters(double ft)
    {
        return inchesToMeters(ft*12);
    }

    public static double metersToFeet(double m)
    {
        return metersToInches(m) / 12;
    }

    public static double degreesToRadian(double d)
    {
        return Math.toRadians(d);
    }

    public static double radiansToDegress(double r)
    {
        return Math.toDegrees(r);
    }
}
