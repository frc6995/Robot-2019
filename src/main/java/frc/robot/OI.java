package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ladder.LadderChangeLevelC;
import frc.robot.commands.ladder.LadderMovePIDC;
import frc.robot.commands.ladder.LadderHoldPIDC;

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);
    
    public OI() {
        //hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
        xbox.y_toggleOnPress(new LadderHoldPIDC());
        //xbox.x_runOnPressed(new LadderChangeLevelC(0));
        //xbox.a_runOnPressed(new LadderChangeLevelC(1));
        //xbox.b_runOnPressed(new LadderChangeLevelC(2));
    }
}