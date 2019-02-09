package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMechToggleCG;

public class OI {
// joystick
    public Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);
    //public XboxController xbox = new XboxController(RobotMap.OI_XBOX);

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    }
}