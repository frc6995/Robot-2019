package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchMechDrawerS extends Subsystem {
  private static Solenoid drawerMech;

  public HatchMechDrawerS () {
    drawerMech = new Solenoid(RobotMap.PCM_ID, RobotMap.PCM_ID_SOLENOID_HATCHDRAWER);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void retract() {
    drawerMech.set(false);
  }

  public void deploy() {
    drawerMech.set(true);
  }

  public boolean get_Value() {
    return drawerMech.get();
  }
  public void set_value(boolean val) {
    drawerMech.set(val);
  }
}