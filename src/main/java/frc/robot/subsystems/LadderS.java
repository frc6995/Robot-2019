
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ladder.LadderHomeC;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LadderS extends Subsystem {
  /*
  if (Robot.m_oi.xbox.getBumperPressed(Hand.kRight)) {
    Robot.m_ladderS.SetNextLadderLevel(1);
  } // if right bumper is pressed, set the next ladder level to 1.
  else if (Robot.m_oi.xbox.getBumperPressed(Hand.kLeft)) {
    Robot.m_ladderS.SetNextLadderLevel(2);
  } //if left bumper is pressed, set the next ladder level to 2.
  if (Robot.m_oi.xbox.getTriggerAxis(Hand.kLeft) <= 0.6) {
    Robot.m_ladderS.SetNextLadderLevel(3);
  }
  */

  private WPI_TalonSRX ladderTalonA = null;
  private WPI_TalonSRX ladderTalonB = null;

  private DigitalInput ladderBottomLimitSwitch;
  private DigitalInput ladderTopLimitSwitch;

  private int currentLadderLevel = 1; //0 = home level, 1 = rocket level 1, 2 = rocket level 2, 3 = rocket level 3.
  private int nextLadderLevel = 1;

  //The range where we will consider ourselves "at" the set point
  private int setPointRange = 10;
  //Counts how many loops we have been within the ladder set point
  private int countWithinSetPoint = 0;
  
  // -- PID constants --
  private boolean ladderPIDActive = true;
  //Preportoinal constant
  private double ladderKp = 5;
  //Integral constant
  private double ladderKi = 0.0;
  //Sum error
  private double sumError = 0;
  //Threshold when we turn the integral on
  private int iThreshold = 50;
  //Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0;

  public static final int LADDER_PID_SLOT = 0;

  @Override
  public void initDefaultCommand() {
   setDefaultCommand(new LadderHomeC());
  }

  public LadderS() {
    ladderTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_B);  
    ladderTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_LADDER_A);    

    //ladderTalonA.setNeutralMode(NeutralMode.Brake);
    //ladderTalonB.setNeutralMode(NeutralMode.Brake);

    ladderTalonB.follow(ladderTalonA);
    
    ladderTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    ladderTalonA.config_kP(LADDER_PID_SLOT, 10);
    ladderTalonA.config_kI(LADDER_PID_SLOT, ladderKi);
    ladderTalonA.config_kD(LADDER_PID_SLOT, 0);
    ladderTalonA.config_kF(LADDER_PID_SLOT, ladderKf);
    
    //SAFTEY CODE FOR TESTING
    //ladderTalonA.configClosedLoopPeakOutput(LADDER_PID_SLOT, 0.1);

    ladderTalonA.selectProfileSlot(LADDER_PID_SLOT, 0);
    //ladderTalonA.configurePID(pid)

    ladderBottomLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_BOTTOM);
    ladderTopLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_TOP);
  }

  public void setLadderPower(double power){
    //Positive is up, negative is down
    ladderTalonA.set(power);
  }

  public double getLadderEncoderCount() {
    //Positive us up, negative is down
    return (ladderTalonA.getSensorCollection().getQuadraturePosition());  
  }

  public int getError(){
    return ladderTalonA.getClosedLoopError();
  }

  public void enablePID(){
    ladderPIDActive = true;
  }

  public void disablePID(){
    ladderPIDActive = false;
    setLadderPower(0);
  }

  public void runPID(){
    SmartDashboard.putNumber("Encoder pos", ladderTalonA.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("Error", getError());
    SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
    SmartDashboard.putNumber("Power", ladderTalonA.getMotorOutputPercent());
    SmartDashboard.putNumber("Set point", ladderTalonA.getClosedLoopTarget());

    ladderTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());

  //   if(ladderPIDActive){
  //     SmartDashboard.putNumber("Encoder pos", ladderTalonA.getSensorCollection().getQuadraturePosition());
  //     SmartDashboard.putNumber("Error", getError());
  //     SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
  //     SmartDashboard.putNumber("Power", ladderTalonA.getMotorOutputPercent());
  //     SmartDashboard.putNumber("Set point", ladderTalonA.getClosedLoopTarget());
  //     //SmartDashboard.putNumber("test",ladderTalonA.getPID)

  //     ladderTalonA.set(ControlMode.Position, 1000);
  //     //ladderTalonA.set(ControlMode.PercentOutput, 0.1);
  //     //power = 
      
  //   }else{
  //     SmartDashboard.putString("Test", "test");
  //     setLadderPower(0);
  //   }

     //If we are within the set point range add 1 to countWithinSetPoint, else set it to 0
     if(Math.abs(getError())<setPointRange){
       countWithinSetPoint++;
     }else{
       countWithinSetPoint = 0;
     }
  }
  
  public boolean isAtSetPoint(){
    //If we have been within our range for at least 50 cycles (1 second), return true
    if(countWithinSetPoint > 50){
      //Resets countWithinSetPoint so next time we start at 0, we might have to make this its own command
      //countWithinSetPoint = 0;
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
    if (getNextLadderLevel() == 1) {
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
