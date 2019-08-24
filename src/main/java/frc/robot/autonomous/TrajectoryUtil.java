package frc.robot.autonomous;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

import frc.robot.RobotMap;
import frc.robot.Robot;

public class TrajectoryUtil 
{

    public static Trajectory reversePath(Trajectory originalTrajectory)
    {
        ArrayList<Segment> segments = new ArrayList<>(Arrays.asList(originalTrajectory.segments));
        Collections.reverse(segments);

        double distance = segments.get(0).position;

        return new Trajectory(segments.stream()
                .map(segment -> new Segment(segment.dt, segment.x, segment.y, distance - segment.position, -segment.velocity, -segment.acceleration, -segment.jerk, segment.heading))
                .toArray(Segment[]::new));
    }

    public static Trajectory correctPath(Trajectory originalTrajectory)
    {
        ArrayList<Segment> segments = new ArrayList<>(Arrays.asList(originalTrajectory.segments));

        return new Trajectory(segments.stream()
                .map(segment -> new Segment(segment.dt, segment.x, RobotMap.FIELD_WIDTH - segment.y, segment.position, segment.velocity, segment.acceleration, segment.jerk, -segment.heading))
                .toArray(Segment[]::new));
    }

    public static Trajectory getTrajectoryFromName(String trajectoryName)
    {
        File trajectoryFile = new File("/home/lvuser/paths/" + trajectoryName + ".pf1.csv");

        Trajectory trajectory; // = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile) : null;
        try{
            trajectory = Pathfinder.readFromCSV(trajectoryFile);
        } catch (IOException e) {
            trajectory = null;
        } 
        if(trajectory == null)
        {
            System.out.println("FILE DOES NOT EXIST");
            Robot.runAuto = false;
            // trajectoryFile = new File("C:\\Users\\brian\\OneDrive\\Projects\\FRC_2018_Offseason\\PathPlanner\\Trajectories\\" + trajectoryName + "\\" + trajectoryName + "_source_detailed.traj");
            // trajectory = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile): null;
        }
        else
            System.out.println("CSV READ SUCCESSFUL");

        return correctPath(trajectory);
    }

    public static Trajectory getTrajectoryFromNameHigherAccel(String trajectoryName)
    {
        File trajectoryFile = new File("/home/lvuser/paths/" + trajectoryName + ".pf1.csv");

        Trajectory trajectory; // = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile) : null;
        try{
            trajectory = Pathfinder.readFromCSV(trajectoryFile);
        } catch (IOException e) {
            trajectory = null;
        } 
        if(trajectory == null)
        {
            System.out.println("FILE DOES NOT EXIST");
            // trajectoryFile = new File("C:\\Users\\brian\\OneDrive\\Projects\\FRC_2018_Offseason\\PathPlanner\\Trajectories\\" + trajectoryName + "\\" + trajectoryName + "_source_detailed.traj");
            // trajectory = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile): null;
        }
        else
            System.out.println("CSV READ SUCCESSFUL");

        return correctPath(trajectory);
    }




    public static Trajectory getTrajectoryFromNameJaci(String trajectoryName)
    {
        File trajectoryFile = new File("/home/lvuser/paths/" + trajectoryName + ".csv");

        Trajectory trajectory; // = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile) : null;
        try{
            trajectory = Pathfinder.readFromCSV(trajectoryFile);
        } catch (IOException e) {
            trajectory = null;
        } 
        
        if(trajectory == null)
        {
            System.out.println("FILE DOES NOT EXIST");
            // trajectoryFile = new File("C:\\Users\\brian\\OneDrive\\Projects\\FRC_2018_Offseason\\PathPlanner\\Trajectories\\" + trajectoryName + "\\" + trajectoryName + "_source_detailed.traj");
            // trajectory = trajectoryFile.exists() ? Pathfinder.readFromFile(trajectoryFile): null;
        }
        else
            System.out.println("CSV READ SUCCESSFUL");

        return trajectory;
    }
}
