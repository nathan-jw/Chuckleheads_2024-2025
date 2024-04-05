package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

import com.chucklelib.MecanumChassis;

@TeleOp(name="MecanumMovementSample", group="SampleOpMode")
public class MecanumMovementSample extends LinearOpMode {
    private RobotHardware hardware;
    private MecanumChassis robot;
    
    @Override
    public void runOpMode() {
        hardware = new RobotHardware(hardwareMap);
        robot = new MecanumChassis(hardware);

        waitForStart();

        while (opModeIsActive()) {
            double botHeading = robot.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            
            double inputTheta = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
            double inputPower = Math.sqrt(gamepad1.left_stick_y * gamepad1.left_stick_y + gamepad1.left_stick_x * gamepad1.left_stick_x);
            double inputTurn = -gamepad1.right_stick_x * 0.7;
            
            // robot.robotCentricMove(inputTheta, inputPower, inputTurn);
            robot.fieldCentricMove(inputTheta, inputPower, inputTurn, botHeading);
        }
    }
}
