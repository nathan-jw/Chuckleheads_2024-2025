package com.chucklelib;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;

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
     * motors, IMU, and odometry pods
     */
    public MecanumChassis(RobotHardware robotHardware) {
        frontLeft = robotHardware.getFrontLeft();
        frontRight = robotHardware.getFrontRight();
        backLeft = robotHardware.getBackLeft();
        backRight = robotHardware.getBackRight();
        
        imu = robotHardware.getImu();
    }
    
    
    /**
     * Move the robot relative to itself.
     * @param inputTheta
     * @param inputPower
     * @param turnPower
     */
    public void robotCentricMove(double inputTheta, double movementPower, double turnPower) {
        double sin = Math.sin(inputTheta - Math.PI * 0.25);
        double cos = Math.cos(inputTheta - Math.PI * 0.25);

        double maxed = Math.max(Math.abs(cos), Math.abs(sin));

        double frontLeftPower = (movementPower * cos / maxed) - turnPower;
        double frontRightPower = (movementPower * sin / maxed) + turnPower;
        double backLeftPower = (movementPower * sin / maxed) - turnPower;
        double backRightPower = (movementPower * cos / maxed) + turnPower;

        double max1 = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        double max2 = Math.max(Math.abs(backLeftPower), Math.abs(backRightPower));
        double max = Math.max(1, Math.max(max1, max2));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }

    /**
     * Move the robot relative to its starting orientation.
     * @param inputTheta
     * @param inputPower
     * @param turnPower
     * @param botHeading
     */
    public void fieldCentricMove(double inputTheta, double inputPower, double turnPower, double botHeading) {
        double sin = Math.sin(inputTheta - Math.PI/4 - botHeading);
        double cos = Math.cos(inputTheta - Math.PI/4 - botHeading);

        double maxed = Math.max(Math.abs(cos), Math.abs(sin));

        double frontLeftPower = inputPower * cos/maxed - turnPower;
        double frontRightPower = inputPower * sin/maxed + turnPower;
        double backLeftPower = inputPower * sin/maxed - turnPower;
        double backRightPower = inputPower * cos/maxed + turnPower;

        double max1 = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        double max2 = Math.max(Math.abs(backLeftPower), Math.abs(backRightPower));
        double max = Math.max(1, Math.max(max1, max2));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }
    
    
    public DcMotorEx getFrontLeft() { return frontLeft; }
    public DcMotorEx getFrontRight() { return frontRight; }
    public DcMotorEx getBackLeft() { return backLeft; }
    public DcMotorEx getBackRight() { return backRight; }
    public IMU getImu() { return imu; }
}