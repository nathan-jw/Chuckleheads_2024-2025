package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

import com.chucklelib.movement.MecanumChassis;
import com.chucklelib.movement.Direction;

@TeleOp(name="MecanumMovementSample", group="SampleOpMode")
public class MecanumMovementSample extends LinearOpMode {
    private RobotHardware hardware;
    private MecanumChassis chassis;
    
    @Override
    public void runOpMode() {
        hardware = new RobotHardware(hardwareMap);
        chassis = new MecanumChassis(hardware);
        
        // Movement and turning run forward by default
        // chassis.setMoveDirection(Direction.FORWARD);
        // chassis.setTurnDirection(Direction.FORWARD);
        
        // In our strafer's case, the move direction will remain unchanged,
        // but we need to reverse the turn direction for it to work the way we
        // expect.
        // chassis.setMoveDirection(Direction.REVERSE);
        chassis.setTurnDirection(Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            // chassis.robotCentricMove(gamepad1);
            chassis.fieldCentricMove(gamepad1);
        }
    }
}
