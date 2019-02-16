package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.limelight.*;
import frc.robot.commands.hatch.HatchMechToggleCG;

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());
        xbox.x_toggleOnPress(new VisionScoreCG());

        //For testing purposes
        SmartDashboard.putData("Drive for 3 Secs", new DriveForTimeC(3));
        SmartDashboard.putData("Vision Align", new VisionAlignTargetC());
    }
}
