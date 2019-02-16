package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllermap.Xbox;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.climb_manual.ClimbFrontToggleC;
import frc.robot.commands.climb_manual.ClimbMotorsForwardC;
import frc.robot.commands.climb_manual.ClimbMotorsReverseC;
import frc.robot.commands.climb_manual.ClimbMotorsStopC;
import frc.robot.commands.climb_manual.ClimbMotorsReverseToggleC;
import frc.robot.commands.climb_manual.ClimbRearToggleC;

import frc.robot.commands.climb.ClimbPlatformCG;

import frc.robot.commands.hatch.HatchMechToggleCG;

public class OI {

    public Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.BUTTON_HATCH_TOGGLE);

    public OI() {
        hatchToggle.toggleWhenPressed(new HatchMechToggleCG());

        /* xbox.b_toggleOnPress(new ClimbPlatformCG());
        
        //backups
        xbox.x_toggleOnPress(new ClimbFrontToggleC());
        xbox.y_toggleOnPress(new ClimbRearToggleC());
        xbox.a_toggleOnPress(new ClimbMotorsReverseToggleC());

        xbox.dpad_up_runOnPressed(new ClimbMotorsForwardC());
        xbox.dpad_up_runOnRelease(new ClimbMotorsStopC());
        xbox.dpad_down_runOnPressed(new ClimbMotorsReverseC());
        xbox.dpad_down_runOnRelease(new ClimbMotorsStopC()); */
    }
}