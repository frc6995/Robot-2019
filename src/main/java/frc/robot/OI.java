package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbFrontToggleC;
import frc.robot.commands.ClimbPlatformCG;
import frc.robot.commands.ClimbRearToggleC;
import frc.robot.commands.ClimbMotorsToggleC;
import frc.robot.commands.HatchMechToggleCG;


public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);
    
    public final JoystickButton climbFront = new JoystickButton(stick, RobotMap.BUTTON_FRONT_TOGGLE); //bad naming...
    public final JoystickButton climbRear = new JoystickButton(stick, RobotMap.BUTTON_REAR_TOGGLE); //bad naming...
    public final JoystickButton climbBox = new JoystickButton(stick, RobotMap.BUTTON_CLIMB_BOX);
    public final JoystickButton climbMotorsToggle = new JoystickButton(stick, RobotMap.BUTTON_CLIMB_MOTORS_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    
        climbFront.toggleWhenPressed(new ClimbFrontToggleC());
        climbRear.toggleWhenPressed(new ClimbRearToggleC());
        climbMotorsToggle.toggleWhenPressed(new ClimbMotorsToggleC());
        climbBox.whenPressed(new ClimbPlatformCG());
    }
}
