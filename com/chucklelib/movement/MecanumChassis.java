package com.chucklelib.movement;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

/**
 * Represents a robot chassis with four mecanum wheels, an IMU, and two
 * odometry pods.
 */
public class MecanumChassis {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    
    private IMU imu;
    
    /**
     * @param robotHardware A RobotHardware instance from which to get the
     * motors, IMU, and odometry pods.
     */
    public MecanumChassis(RobotHardware robotHardware) {
        frontLeft = robotHardware.getFrontLeft();
        frontRight = robotHardware.getFrontRight();
        backLeft = robotHardware.getBackLeft();
        backRight = robotHardware.getBackRight();
        
        imu = robotHardware.getImu();
    }
    
    
    public void resetYaw() { imu.resetYaw(); } 
    
    public DcMotorEx getFrontLeft() { return frontLeft; }
    public DcMotorEx getFrontRight() { return frontRight; }
    public DcMotorEx getBackLeft() { return backLeft; }
    public DcMotorEx getBackRight() { return backRight; }
    public IMU getImu() { return imu; }
}