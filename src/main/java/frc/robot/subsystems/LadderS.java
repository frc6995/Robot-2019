
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
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

  private WPI_TalonSRX ladderMotorA = null;
  private WPI_TalonSRX ladderMotorB = null;

  private int currentLadderLevel = 1; // 1 = ground level, 2 = rocket level 2, 3 = rocket level 3.
  private int nextLadderLevel = 1;

  private int setPoint = 0;

  public static final int LADDER_LEVEL_ONE = 0;
  public static final int LADDER_LEVEL_TWO = 100; //change as needed
  public static final int LADDER_LEVEL_THREE = 200; //change as needed  

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

  public LadderS() {

    ladderMotorA = new WPI_TalonSRX(frc.robot.RobotMap.LADDER_MOTOR_A_TALON_CAN_ID);  
    ladderMotorB = new WPI_TalonSRX(RobotMap.LADDER_MOTOR_B_TALON_CAN_ID);    

  }

  public void MoveLadder(double moveSpeed){
         ladderMotorA.set(moveSpeed);
         ladderMotorB.set(moveSpeed); //may need to be negative
  }

  public double GetMotorAEncoderCount() {
  return (ladderMotorA.getSensorCollection().getQuadraturePosition());  
  }

  public double GetMotorBEncoderCount() {
  return (ladderMotorB.getSensorCollection().getQuadraturePosition());
  }

  public double GetLadderEncoderCount() {
    return (GetMotorAEncoderCount() + GetMotorBEncoderCount() / 2); //averages two encoders
  }

  public int GetCurrentLadderLevel(){
    return (currentLadderLevel);
  }

  public int GetError(){

    if (nextLadderLevel == 1) {
      return (GetCurrentLadderLevel() - LADDER_LEVEL_ONE);
    }
    else if (nextLadderLevel == 2) {
      return (GetCurrentLadderLevel() - LADDER_LEVEL_TWO);
    }
    else if (nextLadderLevel == 3) {
      return (GetCurrentLadderLevel() - LADDER_LEVEL_THREE);
    }
    else {
      return 0;
    }
  }

  public void SetLadderLevel(int currentLevel) {
   currentLadderLevel = currentLevel;
  }

  public void SetNextLadderLevel(int nextLevel){
      nextLadderLevel = nextLevel;
  }

  public int GetNextLadderLevel(){
    return nextLadderLevel;
  }

  public void ResetEncoders() {
    ladderMotorA.getSensorCollection().setQuadraturePosition(0, 500);
    ladderMotorB.getSensorCollection().setQuadraturePosition(0, 500);
  }
}
