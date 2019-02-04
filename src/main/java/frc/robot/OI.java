/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbFrontToggleC;
import frc.robot.commands.ClimbPlatformCG;
import frc.robot.commands.ClimbRearToggleC;
//import frc.robot.commands.HatchMechDeployC;
//import frc.robot.commands.HatchMechRectractC;
//import frc.robot.commands.HatchMechToggleCG;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */


public class OI {
    public final Joystick stick = new Joystick(RobotMap.OI_JOYSTICK);

    public final JoystickButton climbFront = new JoystickButton(stick, RobotMap.BUTTON_ID_FRONT_TOGGLE); //bad naming...
    public final JoystickButton climbRear = new JoystickButton(stick, RobotMap.BUTTON_ID_REAR_TOGGLE); //bad naming...
    public final JoystickButton climbBox = new JoystickButton(stick, RobotMap.BUTTON_ID_CLIMB_BOX);

    //public final JoystickButton hatchDeploy = new JoystickButton(stick, RobotMap.HATCH_DEPLOY); //change to button nums
    //public final JoystickButton hatchRetract = new JoystickButton(stick, RobotMap.HATCH_RETRACT);
    //public final JoystickButton hatchToggle = new JoystickButton(stick, RobotMap.HATCH_TOGGLE);
    
    public final XboxController xbox = new XboxController(RobotMap.OI_XBOX);
    
        public OI() {
            //hatchDeploy.whenPressed(new HatchMechDeployC()); //change to Climber
            //hatchRetract.whenPressed(new HatchMechRectractC());
            //hatchToggle.whenPressed(new HatchMechToggleCG());

            climbFront.toggleWhenPressed(new ClimbFrontToggleC());
            climbRear.toggleWhenPressed(new ClimbRearToggleC());
            climbBox.whenPressed(new ClimbPlatformCG());
            
    }
}