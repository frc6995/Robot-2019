package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.HatchScoreCG;
import frc.robot.commands.cargo.CargoShooterC;
import frc.robot.commands.ladder.LadderDisplayStatusC;
import frc.robot.commands.ladder.LadderHoldPIDC;
import frc.robot.commands.ladder.LadderHomeC;
import frc.robot.commands.ladder.LadderMoveDownPIDC;
import frc.robot.commands.ladder.LadderMoveUpPIDC;
import frc.robot.commands.limelight.setCameraMode;
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
  public static DrivebaseS m_drivebaseS;
  public static LadderS m_ladderS;
  public static CargoShooterS m_CargoShooterS;
  public static HatchMechDrawerS m_hatchMechDrawerS;
  public static HatchMechWheelsS m_hatchMechWheelsS;

  public static OI m_oi;
  public Command m_ladderHomeC;
  public Command m_ladderManualMoveC;
  public Command m_ladderDisplayStatusC;

  public static Command m_ladderMoveUpPIDC;
  public static Command m_ladderMoveDownPIDC;
  public static Command m_ladderHoldPIDC;
  public static Command m_hatchScoreCG;
  public static Command m_cargoShooterC;

  public Command m_setCameraModeC;


  @Override
  public void robotInit() {
    // Instantiate Subsystems Here
    m_drivebaseS = new DrivebaseS();
    m_ladderS = new LadderS();
    m_CargoShooterS = new CargoShooterS();
    m_hatchMechDrawerS = new HatchMechDrawerS();
    m_hatchMechWheelsS = new HatchMechWheelsS();

    m_oi = new OI();

    m_ladderHomeC = new LadderHomeC();

    m_ladderMoveUpPIDC = new LadderMoveUpPIDC();
    m_ladderMoveDownPIDC = new LadderMoveDownPIDC();
    m_ladderHoldPIDC = new LadderHoldPIDC();
    m_hatchScoreCG = new HatchScoreCG();
    m_cargoShooterC = new CargoShooterC();
    m_ladderDisplayStatusC = new LadderDisplayStatusC();
    m_setCameraModeC = new setCameraMode();
  }

  public void robotPeriodic() {
    m_ladderDisplayStatusC.start();
    SmartDashboard.putBoolean("Has Cargo", Robot.m_CargoShooterS.getCargoLimit());
    SmartDashboard.putBoolean("Has Hatch", Robot.m_hatchMechWheelsS.getHatchLimit());
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
    m_setCameraModeC.start();
    m_ladderHomeC.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    m_ladderHomeC.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void testPeriodic() {
  }
}
