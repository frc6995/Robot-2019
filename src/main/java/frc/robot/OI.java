package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMechDeployC;
import frc.robot.commands.HatchMechRectractC;
import frc.robot.commands.HatchMechToggleCG;


public class OI {
public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);
public final JoystickButton hatchDeploy = new JoystickButton(stick, RobotMap.HATCH_DEPLOY);
public final JoystickButton hatchRetract = new JoystickButton(stick, RobotMap.HATCH_RETRACT);
public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.HATCH_TOGGLE);

public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);

    public OI() {
        hatchDeploy.whenPressed(new HatchMechDeployC());
        hatchRetract.whenPressed(new HatchMechRectractC());
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    }
}
