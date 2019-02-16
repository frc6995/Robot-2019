package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
import frc.robot.commands.VisionAlignTargetC;
import frc.robot.commands.VisionScoreCG;
=======
import frc.robot.commands.hatch.HatchMechToggleCG;
>>>>>>> 26266c3762cd68720365ff545474434ba32d95ae

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
    }
}
