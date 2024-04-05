package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

/**
 * Initializes hardware variables. Makes it so that one function call is
 * required in our teleop and autonomous files to get hardware variables.
 */
public class RobotHardware {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    
    private IMU imu;

    /**
     * Initialize the hardware variables
     * @param hardwareMap HardwareMap from the Opmode
     */
    public RobotHardware(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotorEx.class, "fl");
        frontRight = hardwareMap.get(DcMotorEx.class, "fr");
        backLeft = hardwareMap.get(DcMotorEx.class, "bl");
        backRight = hardwareMap.get(DcMotorEx.class, "br");
        imu = hardwareMap.get(IMU.class, "imu");
        
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);
    }
    
    public DcMotorEx getFrontLeft() { return frontLeft; }
    public DcMotorEx getFrontRight() { return frontRight; }
    public DcMotorEx getBackLeft() { return backLeft; }
    public DcMotorEx getBackRight() { return backRight; }
    public IMU getImu() { return imu; }
}