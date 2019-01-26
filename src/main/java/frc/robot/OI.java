/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AlignTargetC;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);
    public XboxController xbox = new XboxController(RobotMap.OI_XBOX);
    public final JoystickButton alignCargoRocket = new JoystickButton(stick, 1);
    /*Xbox buttons: 1=A, 2=B, 3=X, 4=Y, LB = 5, RB = 6, Select = 7, Start = 8, LStick In = 9, RStick In = 10
    Axes: LStick X, LStick Y, L Trigger, R Trigger, RStick X, Rstick Y
    Rumble: Big Slow Rumble, Small Fast Rumble.
    */

    public OI(){
        // joystick
        alignCargoRocket.toggleWhenPressed(new AlignTargetC(0));    
    }
}
