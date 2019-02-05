package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);

    public final JoystickButton hatchToggleButton = new JoystickButton(xbox, RobotMap.BUTTON_HATCH_TOGGLE);
    public final JoystickButton shootCargoButton = new JoystickButton(xbox, RobotMap.BUTTON_CARGO_SHOOT);

    public OI() {
        hatchToggleButton.toggleWhenPressed(Robot.m_hatchMechToggleCG);
        shootCargoButton.whileHeld(Robot.m_cargoShootC);

    }
}
