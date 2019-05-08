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

	public static final int velocitySlotIdx = 0; //probably 0, but possibly 1 (original)

	// Left and Right Drivetrain Velocity PIDF Values (in that order)
	public static final double[] leftVelocityPIDF = {  .341* 1.05, 0, 3.4, .281417 }; // .281417
	public static final double[] rightVelocityPIDF = { .341, 0, 3.4, .281417 };

	public static final double velocityConstant = 3600; //3600enc /100ms

	public static final int kTimeoutMs = 10; // Universal Constant
}