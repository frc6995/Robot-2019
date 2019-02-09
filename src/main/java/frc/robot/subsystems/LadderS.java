
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HomeLadderC;

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
  private boolean ladderPIDActive = false;
  //Preportoinal constant
  private double ladderKp = 0.01;
  //Integral constant
  private double ladderKi = 0.0;
  //Sum error
  private double sumError = 0;
  //Threshold when we turn the integral on
  private int iThreshold = 50;
  //Feedforward = power needed to hold the ladder in a constant spot
  private double ladderKf = 0.2;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HomeLadderC());
  }

  public LadderS() {
    ladderTalonA = new WPI_TalonSRX(RobotMap.LADDER_MOTOR_A_TALON_CAN_ID);  
    ladderTalonB = new WPI_TalonSRX(RobotMap.LADDER_MOTOR_B_TALON_CAN_ID);    
    
    ladderBottomLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_BOTTOM);
    ladderTopLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_LADDER_TOP);
  }

  public void setLadderPower(double power){
    //Positive is up, negative is down
    ladderTalonA.set(power);
    ladderTalonB.set(power);
  }

  public double getLadderEncoderCount() {
    //Positive us up, negative is down
    return (ladderTalonA.getSensorCollection().getQuadraturePosition());  
  }

  public int getError(){ /*I don't think this will work. 
  getCurrentLadderLevel() returns either 1, 2, or 3, and the ladder levels are in increments of 100.
  We will need to convert the current ladder level into that ladder level's encoder count and then subtract.
  I created a getCurrentLadderLevelEncoderCount() function, and added it in comments below. ~JoeyFabel
  */
    if (nextLadderLevel == 1) {
      return (getCurrentLadderLevel() - RobotMap.LADDER_LEVEL_ONE);
      //return (getCurrentLadderLevelEncoderCount() - RobotMap.LADDER_LEVEL_ONE);
    }
    else if (nextLadderLevel == 2) {
      return (getCurrentLadderLevel() - RobotMap.LADDER_LEVEL_TWO);
      //return (getCurrentLadderLevelEncoderCount() - RobotMap.LADDER_LEVEL_TWO);
    }
    else if (nextLadderLevel == 3) {
      return (getCurrentLadderLevel() - RobotMap.LADDER_LEVEL_THREE);
      //return (getCurrentLadderLevelEncoderCount() - RobotMap.LADDER_LEVEL_THREE);
    }
    else {
      return 0;
    }
  }

  public void enablePID(){
    ladderPIDActive = true;
  }

  public void disablePID(){
    ladderPIDActive = false;
  }

  public void runPID(){
    if(ladderPIDActive){
      //Retrives the error based on the current set point
      int error = getError();
      
      //                P term                  I term        F term
      double power = error * ladderKp + sumError * ladderKi + ladderKf;
      
      //Only "turns on" the integral when we need it
      if(Math.abs(error)<iThreshold){
        //Sums the error
        sumError += error;
      }else{
        //Resets the sumError term when we don't need it
        sumError = 0;
      }
      
      setLadderPower(power);
    }else{
      setLadderPower(0);
    }

    //If we are within the set point range add 1 to countWithinSetPoint, else set it to 0
    if(Math.abs(getError())<setPointRange){
      countWithinSetPoint++;
    }else{
      countWithinSetPoint = 0;
    }
  }
  
  public boolean isAtSetPoint(){
    //If we have been within our range for at least 20 cycles (1 second... I think) return true
    if(countWithinSetPoint > 20){
      //Resets countWithinSetPoint so next time we start at 0, we might have to make this its own command
      countWithinSetPoint = 0;
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
  public int getCurrentLadderLevelEncoderCount(){
    if (getCurrentLadderLevel() == 1) {
      return RobotMap.LADDER_LEVEL_ONE;
    }
    else if (getCurrentLadderLevel() == 2) {
      return RobotMap.LADDER_LEVEL_TWO;
    }
    else if (getCurrentLadderLevel() == 3) {
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
