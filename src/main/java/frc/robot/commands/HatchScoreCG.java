package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.hatch.HatchRunWheelsForTimeC;

public class HatchScoreCG extends CommandGroup {
  public HatchScoreCG(){
    //  --EXTEND--
    //Start wheels running in so we can "intake" hatch off the velcro
    addParallel(new HatchRunWheelsForTimeC(0.9,0.4));

    //Push the hatchMech out
    addSequential(Robot.m_hatchDrawerDeployC);
    
    //Wait for the hatchMech to push out
    addSequential(new WaitCommand(0.6));


    //  --EJECT--
    //Run wheels backwards to eject hatch
    addParallel(new HatchRunWheelsForTimeC(-1,1.5));
    
    //Wait for the hatch to be pushed off
    addSequential(new WaitCommand(0.5));
    

    //  --RETRACT--
    //Pull the hatchMech in
    addSequential(Robot.m_hatchDrawerRetractC);
  }
}