package frc.robot.commands.climb_test;

//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// This is for testing/backup, it either deploys or retracts the cylinders.
// On a toggle command like this the first press creates and execute the command but it does not finish.
// The second press interrupts the command.

public class ClimbFrontToggleNC extends Command {

    public ClimbFrontToggleNC () {
        requires (Robot.m_ClimbFrontS);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (Robot.m_ClimbFrontS.getExtended() == true) { //(Robot.m_ClimbFrontS.getExtended() == Value.kForward)
            Robot.m_ClimbFrontS.retractFront();
        }
        /* else if (Robot.m_ClimbFrontS.getExtended() == Value.kOff) {
            Robot.m_ClimbFrontS.retractFront();
        } */
        else { //in DS mode, it would be reverse, so deploy still works
            Robot.m_ClimbFrontS.deployFront();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
