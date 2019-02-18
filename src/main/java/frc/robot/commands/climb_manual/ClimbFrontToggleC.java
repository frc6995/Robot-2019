package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// This is for testing/backup, it either deploys or retracts the cylinders.
// On a toggle command like this the first press creates and execute the command but it does not finish.
// The second press interrupts the command.

public class ClimbFrontToggleC extends Command {

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
