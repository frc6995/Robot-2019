
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ladder.LadderHomeC;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LadderS extends Subsystem {

  //TalonA is left and has the encoder plugged into it, TalonB is right
  private WPI_TalonSRX ladderTalonA = null;
  private WPI_TalonSRX ladderTalonB = null;

  private DigitalInput ladderBottomLimitSwitch;
  private DigitalInput ladderTopLimitSwitch;

  //0 = home level, 1 = rocket level 1, 2 = rocket level 2, 3 = rocket level 3.
  private int currentLadderLevel = 0; 
  private int nextLadderLevel = 0;

  //The range in encoder counts where we will consider ourselves "at" the set point
  private int setPointRange = 10;
  //Counts how many loops we have been within the ladder set point
  private int countWithinSetPoint = 0;
  
  //PID "constants"
  private boolean ladderPIDActive = true;
  //Preportoinal constant
  private double ladderKp = 10;
  //Integral constant
  private double ladderKi = 0.0;
  //Derivative constant
  private double ladderKd = 0.0;
  //Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0;

  //The talon PID slot we are using, this should not change as we 
  public static final int LADDER_PID_SLOT = 0;

  @Override
  public void initDefaultCommand() {
    //Commented out so we can test other things first
    //setDefaultCommand(new LadderHomeC());
  }

  public LadderS() {
    ladderTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_A);  
    ladderTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_B);    

    //      !Commented for testing!
    //ladderTalonA.setNeutralMode(NeutralMode.Brake);
    //ladderTalonB.setNeutralMode(NeutralMode.Brake);

    ladderTalonB.follow(ladderTalonA);
    
    ladderTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    //Configs P, I, D and F using the constants
    ladderTalonA.config_kP(LADDER_PID_SLOT, ladderKp);
    ladderTalonA.config_kI(LADDER_PID_SLOT, ladderKi);
    ladderTalonA.config_kD(LADDER_PID_SLOT, ladderKd);
    ladderTalonA.config_kF(LADDER_PID_SLOT, ladderKf);
    
    //    !Saftey code for testing!
    ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, 0.2);

    ladderTalonA.selectProfileSlot(LADDER_PID_SLOT, 0);

    ladderBottomLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_BOTTOM);
    ladderTopLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_TOP);
  }

  public void setLadderPower(double power){
    //Positive is up, negative is down  -VERIFY
    ladderTalonA.set(power);
  }

  public double getLadderEncoderCount() {
    //Positive us up, negative is down  -VERIFY
    return (ladderTalonA.getSensorCollection().getQuadraturePosition());  
  }

  public int getError(){
    return ladderTalonA.getClosedLoopError();
  }

  public void enablePID(){
    ladderPIDActive = true;
    ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());
  }

  public void disablePID(){
    ladderPIDActive = false;
    //Running a "set power" command will ("should") stop any active position control
    setLadderPower(0);
  }

  public void runPID(){
    //Tuning/testing outputs
    SmartDashboard.putNumber("Encoder pos", ladderTalonA.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("Error", getError());
    SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
    SmartDashboard.putNumber("Power", ladderTalonA.getMotorOutputPercent());
    SmartDashboard.putNumber("Set point", ladderTalonA.getClosedLoopTarget());

    //   !Saftey code for testing!
    if(Robot.m_oi.xbox.left_bumper()){
      ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());
    }else{
      ladderTalonA.set(0);
    }
    

    //If we are within the set point range add 1 to countWithinSetPoint, else set it to 0
    if(Math.abs(getError())<setPointRange){
      countWithinSetPoint++;
    }else{
     countWithinSetPoint = 0;
    }
  }
  
  public void displayStatus(){
    String ladderStatus = "null";

    if(isAtSetPoint() && ladderPIDActive){
      //Example: "Holding at level 3."
      ladderStatus = "Holding at level: " + getCurrentLadderLevel() + ".";
    }else if(!isAtSetPoint() && ladderPIDActive){
      //Example: "Moving down to level 0."
      ladderStatus = "Moving ";
      if(ladderTalonA.getMotorOutputPercent() > 0){
        ladderStatus += "up ";
      }else{
        ladderStatus += "down ";
      }
      ladderStatus += "to level " + getNextLadderLevel() + ".";
    }else if(!ladderPIDActive){
      ladderStatus = "Idle";
    }

    SmartDashboard.putString("Ladder status", ladderStatus);
  }

  public boolean isAtSetPoint(){
    //If we have been within our range for at least 50 cycles (1 second), return true
    if(countWithinSetPoint > 50){
      currentLadderLevel = nextLadderLevel;
      return true;
    }else{
      return false;
    }
  }
  
  public void setCurrentLadderLevel(int currentLevel) {
   currentLadderLevel = currentLevel;
  }
  
  public int getCurrentLadderLevel(){
    return currentLadderLevel;
  }

  public int getLadderSetPointEncoderCount(){
    
    if(getNextLadderLevel() == 0){
      return RobotMap.LADDER_LEVEL_ZERO;
    }else if (getNextLadderLevel() == 1) {
      return RobotMap.LADDER_LEVEL_ONE;
    }
    else if (getNextLadderLevel() == 2) {
      return RobotMap.LADDER_LEVEL_TWO;
    }
    else if (getNextLadderLevel() == 3) {
      return RobotMap.LADDER_LEVEL_THREE;
    }
    else {
      return 0;
    }
  }

  public void setNextLadderLevel(int nextLevel){
    nextLadderLevel = nextLevel;
  }

  public int getNextLadderLevel(){
    return nextLadderLevel;
  }

  public boolean lowerLimitSwitchPressed(){
    //Returns true if the sensor is pressed, false if it is not
    return ladderBottomLimitSwitch.get();
  }
  
  public boolean upperKimitSwitchPressed(){
    return ladderTopLimitSwitch.get();
  }

  public void resetEncoder() {
    ladderTalonA.getSensorCollection().setQuadraturePosition(0, 500);
  }
}
