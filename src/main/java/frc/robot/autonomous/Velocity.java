package frc.robot.autonomous;

public class Velocity 
{
    private double linear;
    private double angular;

    Velocity(double linear, double angular) 
    {
        this.linear = linear;
        this.angular = angular;
    }

    public double getLinear() 
    {
        return linear;
    }

    public double getAngular() 
    {
        return angular;
    }
}
