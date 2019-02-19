
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.commands.ladder.LadderHomeC;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LadderS extends Subsystem {
  //Enumerate various ladder levels.
  public enum LadderLevel {
    LEVEL_ONE, LEVEL_VISION, LEVEL_TWO, LEVEL_THREE;
  }
  // TalonA is left and has the encoder plugged into it, TalonB is right
  private WPI_TalonSRX ladderTalonA = null;
  private WPI_TalonSRX ladderTalonB = null;

  private DigitalInput ladderBottomLimitSwitch;
  private DigitalInput ladderTopLimitSwitch;

  //1 = rocket level 1 / home, 2 = rocket level 2, 3 = rocket level 3.
  private LadderLevel currentLadderLevel = LadderLevel.LEVEL_ONE;
  private LadderLevel nextLadderLevel = LadderLevel.LEVEL_ONE;

  // The range in encoder counts where we will consider ourselves "at" the set
  // point
  private int setPointRange = 200;

  // Counts how many loops we have been within the ladder set point
  private int countWithinSetPoint = 0;

  // PID "constants"
  private boolean ladderPIDActive = true;
  // Proportional constant
  private double ladderKp = 0.5;
  // Integral constant
  private double ladderKi = 0.001;
  // Derivative constant
  private double ladderKd = 10.0;
  // Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0;

  // The talon PID slot we are using, this should not change as we
  public static final int LADDER_PID_SLOT = 0;

  @Override
  public void initDefaultCommand() {
    // Commented out so we can test other things first
    // setDefaultCommand(new LadderHomeC());
  }

  public LadderS() {
    ladderTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_A);
    ladderTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_B);

    ladderTalonA.setNeutralMode(NeutralMode.Brake);
    ladderTalonB.setNeutralMode(NeutralMode.Brake);

    // Sensor reverse
    ladderTalonA.setSensorPhase(false);

    //Motor reverse
    //ladderTalonA.setInverted(true);
    //ladderTalonB.setInverted(true);

    // B follows A
    ladderTalonB.follow(ladderTalonA);

    // Selects the Quad encoder as the feedback sensor
    ladderTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    // Makes sure we are not multiplying the counts by anything
    ladderTalonA.configSelectedFeedbackCoefficient(1.0);

    // Doesn't apply to voltage control
    ladderTalonA.configForwardSoftLimitThreshold(8000);
    ladderTalonA.configForwardSoftLimitEnable(true);

    // Configs P, I, D and F using the constants
    ladderTalonA.config_kP(LADDER_PID_SLOT, ladderKp);
    ladderTalonA.config_kI(LADDER_PID_SLOT, ladderKi);
    ladderTalonA.config_kD(LADDER_PID_SLOT, ladderKd);
    ladderTalonA.config_kF(LADDER_PID_SLOT, ladderKf);

    // The zone where the integral turns on
    ladderTalonA.config_IntegralZone(LADDER_PID_SLOT, 1000);

    // Makes it so we don't start pushing the ladder at full power immediately,
    // takes 0.5 seconds to ramp to full
    ladderTalonA.configClosedloopRamp(0.5);

    // Sets the max power that the PID can apply
    ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, 0.4);

    // Selects the PID profile slot
    ladderTalonA.selectProfileSlot(LADDER_PID_SLOT, 0);

    ladderBottomLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_BOTTOM);
    ladderTopLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_TOP);

    resetEncoder();

    // Overides any other commands and makes sure the ladder inits not moving
    setLadderPower(0);
    //commented out so I can test LadderHomeC   */ 
    ladderTalonA.neutralOutput(); //equivalent of setLadderPower(0) but w/ break
  }

  public void setLadderPower(double power) {
    // Positive is up, negative is down -VERIFY
    ladderTalonA.set(ControlMode.PercentOutput, power);
  }

  public void setMaxPIDPower(double power){
    ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, power);
  }

  public double getLadderEncoderCount() {
    // Positive us up, negative is down -VERIFY
    return (ladderTalonA.getSensorCollection().getQuadraturePosition());
  }

  public int getError() {
    return ladderTalonA.getClosedLoopError();
  }

  public void enablePID() {
    ladderPIDActive = true;
    // ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());
  }

  public void disablePID() {
    ladderPIDActive = false;
    // Running a "set power" command will ("should") stop any active position
    // control
    setLadderPower(0);
  }

  public void runPID() {
    // Tuning/testing outputs
    SmartDashboard.putNumber("Encoder pos", ladderTalonA.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("Error", getError());
    SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
    SmartDashboard.putNumber("Power", ladderTalonA.getMotorOutputPercent());
    SmartDashboard.putNumber("Talon Set point", ladderTalonA.getClosedLoopTarget());
    SmartDashboard.putNumber("Code Set point", getLadderSetPointEncoderCount());
    SmartDashboard.putString("Next ladder level", LadderLevelToString(nextLadderLevel));
    SmartDashboard.putNumber("Integral sum", ladderTalonA.getIntegralAccumulator());
    SmartDashboard.putNumber("Derivative", ladderTalonA.getErrorDerivative());
    SmartDashboard.putString("Ladder level",LadderLevelToString(getNextLadderLevel()));

    // !Safety code for testing!
    SmartDashboard.putBoolean("Enabled", Robot.m_oi.xbox.a());
    if (Robot.m_oi.xbox.a()) {
      ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());
    } else {

      // Tuning code

      if (SmartDashboard.getNumber("kpL", Integer.MAX_VALUE) == Integer.MAX_VALUE) { // make sure kpL is on
                                                                                     // smartdashboard
        SmartDashboard.putNumber("kpL", ladderKp);
      } else {
        ladderKp = SmartDashboard.getNumber("kpL", ladderKp);
        ladderTalonA.config_kP(LADDER_PID_SLOT, ladderKp);
      }

      if (SmartDashboard.getNumber("kiL", Integer.MAX_VALUE) == Integer.MAX_VALUE) { // make sure kpAim is on
                                                                                     // smartdashboard
        SmartDashboard.putNumber("kiL", ladderKi);
      } else {
        ladderKi = SmartDashboard.getNumber("kiL", ladderKi);
        ladderTalonA.config_kI(LADDER_PID_SLOT, ladderKi);
      }

      if (SmartDashboard.getNumber("kdL", Integer.MAX_VALUE) == Integer.MAX_VALUE) { // make sure kpAim is on
                                                                                     // smartdashboard
        SmartDashboard.putNumber("kdL", ladderKd);
      } else {
        ladderKd = SmartDashboard.getNumber("kdL", ladderKd);
        ladderTalonA.config_kD(LADDER_PID_SLOT, ladderKd);
      }

      if (SmartDashboard.getNumber("kfL", Integer.MAX_VALUE) == Integer.MAX_VALUE) { // make sure kpAim is on
                                                                                     // smartdashboard
        SmartDashboard.putNumber("kfL", ladderKf);
      } else {
        ladderKf = SmartDashboard.getNumber("kfL", ladderKf);
        ladderTalonA.config_kF(LADDER_PID_SLOT, ladderKf);
      }

      ladderTalonA.set(ControlMode.PercentOutput, 0);
    }

    // If we are within the set point range add 1 to countWithinSetPoint, else set
    // it to 0
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

    SmartDashboard.putString("Ladder status", ladderStatus);
  }

  public boolean isAtSetPoint() {
    // If we have been within our range for at least 50 cycles (1 second), return
    // true
    if (countWithinSetPoint > 50) {
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
    if(getNextLadderLevel() == LadderLevel.LEVEL_ONE){
      return RobotMap.LADDER_LEVEL_VISION; //cushion level
    }else if (getNextLadderLevel() == LadderLevel.LEVEL_VISION) {
      return RobotMap.LADDER_LEVEL_VISION;
    }
    else if (getNextLadderLevel() == LadderLevel.LEVEL_TWO) {
      return RobotMap.LADDER_LEVEL_TWO;
    }
    else if (getNextLadderLevel() == LadderLevel.LEVEL_THREE) {
      return RobotMap.LADDER_LEVEL_THREE;
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

  public boolean upperLimitSwitchPressed() {
    return ladderTopLimitSwitch.get();
  }

  public void resetEncoder() {
    ladderTalonA.getSensorCollection().setQuadraturePosition(3, 500);
    ladderTalonA.setSelectedSensorPosition(0);
  }

  public String LadderLevelToString(LadderLevel level) {
    switch(level) {
      case LEVEL_ONE:
        return "1";
      case LEVEL_VISION:
        return "Vision";
      case LEVEL_TWO:
        return "2";
      case LEVEL_THREE:
        return "3";
      default:
        return "Unknown. Illegal LadderLevel type.";
    }
  }
}
