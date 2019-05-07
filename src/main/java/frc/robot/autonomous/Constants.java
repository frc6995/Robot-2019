package frc.robot.autonomous;
public class Constants {
    
	public static final double kEncoderTicks = 4096;
	public static final double kWheelDiameter = .153; // meters
	public static final double kWheelBase = .7112;
	public static final double kMaxVelocity = 4.22;

	public static final double kBeta = 1.6; // b > 0 Correction
	public static final double kZeta = 0.17; // 0 < z < 1 Dampening //.17 hou apr 18th

	public static final double kFieldWidth = 8.2296;
	public static final double expirationTimeSRX = 2; // seconds	
}