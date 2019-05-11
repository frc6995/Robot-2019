package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.LowPowerModeDrivebaseC;
import frc.robot.commands.cargo.CargoIntakeC;
import frc.robot.commands.cargo.CargoScoreC;
import frc.robot.commands.hatch.HatchDrawerDeployC;
import frc.robot.commands.hatch.HatchDrawerRetractC;
import frc.robot.commands.ladder.LadderDisplayStatusC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.limelight.VisionSetDriverCamC;
import frc.robot.subsystems.CargoShooterS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.HatchMechDrawerS;
import frc.robot.subsystems.HatchMechWheelsS;
import frc.robot.subsystems.LadderS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //Subsystems
  public static DrivebaseS m_drivebaseS;
  public static LadderS m_ladderS;
  public static CargoShooterS m_CargoShooterS;
  public static HatchMechDrawerS m_hatchMechDrawerS;
  public static HatchMechWheelsS m_hatchMechWheelsS;

  public static Compressor m_compressor;
  public static PowerDistributionPanel m_PDP;

  public static OI m_oi;

  //Ladder commands
  public static Command m_ladderHomeC;
  public static Command m_ladderDisplayStatusC;
  public static Command m_ladderMoveUpPIDC;
  public static Command m_ladderMoveDownPIDC;
  public static Command m_ladderHoldPIDC;
  //Hatch/Cargo commands
  public static Command m_hatchDrawerDeployC;
  public static Command m_hatchDrawerRetractC;
  public static Command m_hatchScoreCG;
  public static Command m_cargoScoreC;
  public static Command m_cargoIntakeC;
  //Limelight
  public static Command m_visionSetDriverCamC;

  public static Command m_lowPowerDBC;



  @Override
  public void robotInit() {
    // Subsystems
    m_drivebaseS = new DrivebaseS();
    m_ladderS = new LadderS();
    m_CargoShooterS = new CargoShooterS();
    m_hatchMechDrawerS = new HatchMechDrawerS();
    m_hatchMechWheelsS = new HatchMechWheelsS();

    m_oi = new OI();

    //Ladder commands
    m_ladderHomeC = new LadderHomeC();
    m_ladderMoveUpPIDC = new LadderMoveUpPIDC();
    m_ladderMoveDownPIDC = new LadderMoveDownPIDC();
    m_ladderHoldPIDC = new LadderHoldPIDC();
    m_ladderDisplayStatusC = new LadderDisplayStatusC();
    //Cargo/Hatch commands
    m_hatchDrawerDeployC = new HatchDrawerDeployC();
    m_hatchDrawerRetractC = new HatchDrawerRetractC();
    m_cargoIntakeC = new CargoIntakeC();
    m_hatchScoreCG = new HatchScoreCG();
    m_cargoScoreC = new CargoScoreC();
    //Limelight commands
    m_visionSetDriverCamC= new VisionSetDriverCamC();

    m_compressor = new Compressor(RobotMap.PCM_ID);
    m_PDP = new PowerDistributionPanel(0); //use the real can id. 
    SmartDashboard.putData("Compressor", m_compressor);
    SmartDashboard.putData("Diff Drive",m_drivebaseS.differentialDrive);
    SmartDashboard.putData("PDP", m_PDP);
    //SmartDashboard.putData("LP DB", new LowPowerModeDrivebaseC());        //should now be able to pull out like limelight.
    //voltage and fms are both drag widgets also. the dial will be a change to in shuffleboard. it is now sent in
    //drivebase. I think diff drive will also be a drag out thing
  }

  public void robotPeriodic() {
    //Driver feedback
    m_ladderDisplayStatusC.start();
    SmartDashboard.putBoolean("Has Cargo", Robot.m_CargoShooterS.getCargoLimit());
    SmartDashboard.putBoolean("Has Hatch", Robot.m_hatchMechWheelsS.getHatchLimit());
    SmartDashboard.putNumber("PDP Input Voltage", Robot.m_PDP.getVoltage()); // Note: this is input voltage
    
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    //Force the correct camera mode
    m_visionSetDriverCamC.start();
    //Home the ladder
    m_ladderHomeC.start();
    m_compressor.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //Force the correct camera mode
    m_visionSetDriverCamC.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
