package com.chucklelib.movement;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

// TODO: Add an option to use the turning controls from this season

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
    
    private Direction moveDirection = Direction.FORWARD;
    private Direction turnDirection = Direction.FORWARD;
    
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
    
    
    /**
     * Move the robot relative to itself.
     * @param gamepad The gamepad to control movement with.
     */
    public void robotCentricMove(Gamepad gamepad) {
        double inputTheta = Math.atan2(-gamepad.left_stick_y, gamepad.left_stick_x);
        double movementPower = -Math.sqrt(gamepad.left_stick_y * gamepad.left_stick_y + gamepad.left_stick_x * gamepad.left_stick_x);
        double turnPower = -gamepad.right_stick_x * 0.7;
         
        if (moveDirection == Direction.REVERSE) {
            movementPower *= -1;
        }   
        if (turnDirection == Direction.REVERSE) { 
            turnPower *= -1;
        }
        
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
     * @param gamepad The gamepad to control movement with.
     */
    public void fieldCentricMove(Gamepad gamepad) {
        double inputTheta = Math.atan2(-gamepad.left_stick_y, gamepad.left_stick_x);
        double inputPower = Math.sqrt(gamepad.left_stick_y * gamepad.left_stick_y + gamepad.left_stick_x * gamepad.left_stick_x);
        double turnPower = -gamepad.right_stick_x * 0.7;
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        
        if (moveDirection == Direction.REVERSE) {
            inputPower *= -1;
        }   
        if (turnDirection == Direction.REVERSE) { 
            turnPower *= -1;
        }
        
        double sin = Math.sin(inputTheta - (Math.PI * 0.25) - botHeading);
        double cos = Math.cos(inputTheta - (Math.PI * 0.25) - botHeading);

        double maxed = Math.max(Math.abs(cos), Math.abs(sin));

        double frontLeftPower = (inputPower * cos / maxed) - turnPower;
        double frontRightPower = (inputPower * sin / maxed) + turnPower;
        double backLeftPower = (inputPower * sin / maxed) - turnPower;
        double backRightPower = (inputPower * cos / maxed) + turnPower;

        double max1 = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        double max2 = Math.max(Math.abs(backLeftPower), Math.abs(backRightPower));
        double max = Math.max(1, Math.max(max1, max2));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }
    
    
    public void setMoveDirection(Direction direction) { moveDirection = direction; }
    public void setTurnDirection(Direction direction) { turnDirection = direction; }
    
    public DcMotorEx getFrontLeft() { return frontLeft; }
    public DcMotorEx getFrontRight() { return frontRight; }
    public DcMotorEx getBackLeft() { return backLeft; }
    public DcMotorEx getBackRight() { return backRight; }
    public IMU getImu() { return imu; }
    public Direction getMoveDirection() { return moveDirection; }
    public Direction getTurnDirection() { return turnDirection; }
}