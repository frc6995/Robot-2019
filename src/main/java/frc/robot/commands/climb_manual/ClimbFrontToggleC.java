package frc.robot.commands.climb_manual;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


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
