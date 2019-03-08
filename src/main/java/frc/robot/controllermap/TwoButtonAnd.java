/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllermap;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Add your docs here.
 */
public class TwoButtonAnd extends Button {
    public TwoButtonAnd(Button button1, Button button2) {
        Button firstButton = button1;
        Button secondButton = button2;
    }
    @Override
    public boolean get() {
        return true;
    }


}
