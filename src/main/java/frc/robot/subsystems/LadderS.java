
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LadderS extends Subsystem {
  //Enumerate various ladder levels.
  public enum LadderLevel {
    LEVEL_ONE, LEVEL_CUSHION, LEVEL_TWO, LEVEL_THREE, LEVEL_CARGO_INTAKE, LEVEL_BOTTOM, LEVEL_CARGO_SHIP;
  }
  // TalonA is left and has the encoder plugged into it, TalonB is right
  public WPI_TalonSRX ladderTalonA = null;
  public WPI_TalonSRX ladderTalonB = null;

  private DigitalInput ladderBottomLimitSwitch;

  private LadderLevel currentLadderLevel = LadderLevel.LEVEL_ONE;
  private LadderLevel nextLadderLevel = LadderLevel.LEVEL_ONE;

  // Range in encoder counts where we consider ourselves "at" the set point
  private int setPointRange = 200;

  // Counts how many loops we have been within the ladder set point
  private int countWithinSetPoint = 0;

  // PID "constants"
  private boolean ladderPIDActive = true;
  // Proportional constant
  private double ladderKp = 0.7; //Up
  private double ladderDownKp = 0.05;  //Down
  // Integral constant
  private double ladderKi = 0.0008;
  // Derivative constant
  private double ladderKd = 10.0;
  // Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0;

  private int currentLimit = 20;

  // The talon PID slot we are using, this should not change
  public static final int LADDER_PID_SLOT = 0;

  @Override
  public void initDefaultCommand() {
  }

  public LadderS() {
    ladderTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_A);
    ladderTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_B);

    ladderTalonA.configFactoryDefault();
    ladderTalonB.configFactoryDefault();

    ladderTalonB.follow(ladderTalonA);

    ladderTalonA.setInverted(false);
    ladderTalonB.setInverted(false);

    ladderTalonA.setNeutralMode(NeutralMode.Brake);
    ladderTalonB.setNeutralMode(NeutralMode.Brake);

    ladderTalonA.configContinuousCurrentLimit(currentLimit);
    ladderTalonA.configPeakCurrentDuration(0);
    ladderTalonA.enableCurrentLimit(false);

    ladderTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    ladderTalonA.configSelectedFeedbackCoefficient(1.0);

    // Sensor reverse
    ladderTalonA.setSensorPhase(false);

    ladderTalonA.selectProfileSlot(LADDER_PID_SLOT, 0);

    ladderTalonA.configAllowableClosedloopError(LADDER_PID_SLOT, 0);

    // Doesn't apply to voltage control
    ladderTalonA.configForwardSoftLimitThreshold(8000);  //Stops the ladder at the top
    ladderTalonA.configForwardSoftLimitEnable(false);

    // Configs P, I, D and F using the constants
    ladderTalonA.config_kP(LADDER_PID_SLOT, ladderKp);
    ladderTalonA.config_kI(LADDER_PID_SLOT, ladderKi);
    ladderTalonA.config_kD(LADDER_PID_SLOT, ladderKd);
    ladderTalonA.config_kF(LADDER_PID_SLOT, ladderKf);

    // The zone where the integral turns on
    ladderTalonA.config_IntegralZone(LADDER_PID_SLOT, 1500);

    // Makes it so we don't start pushing the ladder at full power immediately,
    // takes 0.5 seconds to ramp to full
    ladderTalonA.configClosedloopRamp(0.7);

    // Sets the max power that the PID can apply
    ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, 0.4);

    //Limit switch
    ladderBottomLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_BOTTOM);

    // Overides any other commands and makes sure the ladder is not moving
    setLadderPower(0);
  }

  public void setLadderPower(double power) {
    // Positive is up, negative is down
    ladderTalonA.set(ControlMode.PercentOutput, power);
  }

  //Sets the max power the PID can apply
  public void setMaxPIDPower(double power){
    ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, power);
  }

  //For using a less agressive Kp on the way down
  public void useUpKp(){
    ladderTalonA.config_kP(LADDER_PID_SLOT, ladderKp);
  }
  public void useDownKp(){
    ladderTalonA.config_kP(LADDER_PID_SLOT, ladderDownKp);
  }

  public double getLadderEncoderCount() {
    // Positive is up, negative is down
    return (ladderTalonA.getSensorCollection().getQuadraturePosition());
  }

  public int getError() {
    return ladderTalonA.getClosedLoopError();
  }

  public void enablePID() {
    ladderPIDActive = true;
  }

  public void disablePID() {
    ladderPIDActive = false;
    countWithinSetPoint = 0;
    // Running a "set power" command will stop any active position control
    setLadderPower(0);
    ladderTalonA.neutralOutput();
  }

  public void runPID() {
    ladderPIDActive = true;

    // Tuning/testing outputs
    //SmartDashboard.putNumber("Encoder pos", ladderTalonA.getSensorCollection().getQuadraturePosition());
    //SmartDashboard.putNumber("Error", getError());
    //SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
    //SmartDashboard.putNumber("Power", ladderTalonA.getMotorOutputPercent());
    //SmartDashboard.putNumber("Talon Set point", ladderTalonA.getClosedLoopTarget());
    //SmartDashboard.putNumber("Code Set point", getLadderSetPointEncoderCount());
    //SmartDashboard.putString("Next ladder level", LadderLevelToString(nextLadderLevel));
    //SmartDashboard.putNumber("Integral sum", ladderTalonA.getIntegralAccumulator());
    //SmartDashboard.putNumber("Derivative", ladderTalonA.getErrorDerivative());
    //SmartDashboard.putString("Ladder level",LadderLevelToString(getNextLadderLevel()));

    ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());

    // If we are within the set point range add 1 to countWithinSetPoint, else set to 0
    if (Math.abs(getError()) < setPointRange) {
      countWithinSetPoint++;
    } else {
      countWithinSetPoint = 0;
    }
  }

  public void displayStatus() {
    String ladderStatus = "null";

    if (isAtSetPoint() && ladderPIDActive) {
      // Example: "Holding at level 3."
      ladderStatus = "Holding at level: " + LadderLevelToString(getCurrentLadderLevel()) + ".";
    } else if (!isAtSetPoint() && ladderPIDActive) {
      // Example: "Moving down to level 0."
      ladderStatus = "Moving ";
      if (ladderTalonA.getMotorOutputPercent() > 0) {
        ladderStatus += "up ";
      } else {
        ladderStatus += "down ";
      }
      ladderStatus += "to level " + LadderLevelToString(getNextLadderLevel()) + ".";
    } else if (!ladderPIDActive) {
      ladderStatus = "Idle";
    }
    else {
      ladderStatus = "REKT";
    }
    SmartDashboard.putString("Ladder status", ladderStatus);
  }

  public boolean isAtSetPoint() {
    //If we have been within our set point range for the given time, return true
    if (countWithinSetPoint > 15) {
      currentLadderLevel = nextLadderLevel;
      countWithinSetPoint = 0;
      return true;
    } else {
      return false;
    }
  }

  public void setCurrentLadderLevel(LadderLevel currentLevel) {
    currentLadderLevel = currentLevel;
  }

  public LadderLevel getCurrentLadderLevel() {
    return currentLadderLevel;
  }

  public int getLadderSetPointEncoderCount(){
    //SmartDashboard.putBoolean("Below Cushion", (getLadderEncoderCount() < RobotMap.LADDER_LEVEL_CUSHION));
    if(getNextLadderLevel() == LadderLevel.LEVEL_ONE){
      if (getLadderEncoderCount() < RobotMap.LADDER_LEVEL_CUSHION) {
        return RobotMap.LADDER_LEVEL_ONE;
      }
      else
        return RobotMap.LADDER_LEVEL_CUSHION; 
    }
    else if (getNextLadderLevel() == LadderLevel.LEVEL_CARGO_SHIP) {
      return RobotMap.LEVEL_CARGO_SHIP;
    }
    else if (getNextLadderLevel() == LadderLevel.LEVEL_TWO) {
      return RobotMap.LADDER_LEVEL_TWO;
    }
    else if (getNextLadderLevel() == LadderLevel.LEVEL_THREE) {
      return RobotMap.LADDER_LEVEL_THREE;
    }
    else if(getNextLadderLevel() == LadderLevel.LEVEL_CARGO_INTAKE){
      return RobotMap.LADDER_LEVEL_CARGO_INTAKE;
    }
    else if(getNextLadderLevel() == LadderLevel.LEVEL_BOTTOM) {
      return 0;
    } 
    else {
      return 0;
    }
  }

  public void setNextLadderLevel(LadderLevel nextLevel) {
    nextLadderLevel = nextLevel;
  }

  public LadderLevel getNextLadderLevel() {
    return nextLadderLevel;
  }

  public boolean lowerLimitSwitchPressed() {
    // Returns true if the sensor is pressed, false if it is not
    return ladderBottomLimitSwitch.get();
  }

  public void resetEncoder() {
    ladderTalonA.getSensorCollection().setQuadraturePosition(3, 500);
    ladderTalonA.setSelectedSensorPosition(0);
  }

  public String LadderLevelToString(LadderLevel level) {
    switch(level) {
      case LEVEL_ONE:
        return "1";
      case LEVEL_CUSHION:
        return "Cushion";
      case LEVEL_TWO:
        return "2";
      case LEVEL_THREE:
        return "3";
      case LEVEL_CARGO_INTAKE:
        return "4";
      case LEVEL_BOTTOM:
        return "6";
      case LEVEL_CARGO_SHIP:
        return "Cargo Ship";
      default:
        return "Unknown. Illegal LadderLevel type.";
    }
  }
}
