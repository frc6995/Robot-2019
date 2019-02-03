/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ClimbFrontToggleC extends Command {
    public boolean extended = false;

    public ClimbFrontToggleC () {
        requires (Robot.m_ClimbFrontS);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.m_ClimbFrontS.deployFront();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.m_ClimbFrontS.retractFront();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
