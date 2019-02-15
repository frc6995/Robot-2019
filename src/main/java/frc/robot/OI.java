package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.VisionScoreCG;

public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);
    public XboxController xbox = new XboxController(RobotMap.OI_XBOX);
    public final JoystickButton alignCargoRocket = new JoystickButton(stick, 1);


    public OI(){
        // joystick
        //When the button is pressed, start the VisionScoreCG. 
        //If you press the button again, the command group will cancel.
        alignCargoRocket.toggleWhenPressed(new VisionScoreCG());    
    }
}
