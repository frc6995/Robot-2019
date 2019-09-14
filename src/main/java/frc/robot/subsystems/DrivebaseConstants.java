/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Created with the approval of Elijah Sauder and the uneducated intellect of Joey Fabel
 */
public class DrivebaseConstants {

    public DrivebaseConstants drivebaseConstants = null;
    public float maxVelocity = 0.0f;
    public float maxAcceleration = 0.0f;
    public float maxJerk = 0.0f;

    private float prevDistance;
    private float prevVelocity;
    private float prevAcceleration;

    private float distance;
    private long startTime;

    public DrivebaseConstants() {
        Awake();
        
        prevDistance = calculateDistance();
        prevVelocity = getVelocity();
        prevAcceleration = getAcceleration();   
    }

    void Awake() {
        startTime = System.currentTimeMillis();
        if(drivebaseConstants == null) {
            drivebaseConstants = this;
        }
    }

    private float calculateDistance() {
        distance =  Robot.m_drivebaseS.getRightEncoder() / RobotMap.ENCODER_TICKS * (float)RobotMap.WHEEL_DIAMETER * (float)Math.PI;
        return distance;
    }
    private float getVelocity() {     
        float velocity;
        velocity = (calculateDistance() - prevDistance) / (System.currentTimeMillis() - startTime);
        return velocity;        
    }
    
    private float getAcceleration () {
        float acceleration;
        acceleration = (getVelocity() - prevVelocity) / (System.currentTimeMillis() - startTime);
        return acceleration;
    }

    private float getJerk () {
        float jerk;
        jerk = (getAcceleration() - prevAcceleration) / (System.currentTimeMillis() - startTime);
        return jerk;
    }

   public void update() {     

        if (getVelocity() > maxVelocity) {
            maxVelocity = getVelocity();
        }
        if (getAcceleration() > maxAcceleration){            
            maxAcceleration = getAcceleration();
        }
        if (getJerk() > maxJerk) {            
            maxJerk = getJerk();        
        }
        SmartDashboard.putNumber("max velocity: ", maxVelocity);
        SmartDashboard.putNumber("max acceleration: ", maxAcceleration);
        SmartDashboard.putNumber("max jerk: ", maxJerk);
       
        prevDistance = calculateDistance();
        prevVelocity = getVelocity();
        prevAcceleration = getAcceleration();   
    }
}
