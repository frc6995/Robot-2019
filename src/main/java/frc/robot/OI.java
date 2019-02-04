package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMechDeployC;
import frc.robot.commands.HatchMechRetractC;
import frc.robot.commands.HatchMechToggleCG;


public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);
    
    public final JoystickButton hatchDeploy = new JoystickButton(stick, RobotMap.BUTTON_HATCH_DEPLOY);
    public final JoystickButton hatchRetract = new JoystickButton(stick, RobotMap.BUTTON_HATCH_RETRACT);
    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchDeploy.whenPressed(new HatchMechDeployC());
        hatchRetract.whenPressed(new HatchMechRetractC());
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    }
}
