package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClimbFrontToggleC;
import frc.robot.commands.ClimbMotorsToggleC;
import frc.robot.commands.ClimbPlatformCG;
import frc.robot.commands.ClimbRearToggleC;
import frc.robot.commands.hatch.HatchMechToggleCG;

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

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