
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LadderS extends Subsystem {

  private WPI_TalonSRX ladderMotorA = null;
  private WPI_TalonSRX ladderMotorB = null;
  private DifferentialDrive ladderDifferentialDrive = null;

  private int currentLadderLevel = 1; // 1 = ground level, 2 = rocket level 2, 3 = rocket level 3.
  private int nextLadderLevel = 1;

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

    ladderDifferentialDrive = new DifferentialDrive(ladderMotorA, ladderMotorB);

  }

  public void MoveLadder(double moveSpeed){
    ladderDifferentialDrive.arcadeDrive(moveSpeed, 0);     //may need to use set.
  }

  public double GetMotorAEncoderCount() {
  return ladderMotorA.getSensorCollection().getQuadraturePosition();  
  }

  public double GetMotorBEncoderCount() {
  return ladderMotorB.getSensorCollection().getQuadraturePosition();
  }

  public int GetCurrentLadderLevel(){
    return currentLadderLevel;
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
}
