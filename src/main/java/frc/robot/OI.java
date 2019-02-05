package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMechToggleCG;


public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    }
}
