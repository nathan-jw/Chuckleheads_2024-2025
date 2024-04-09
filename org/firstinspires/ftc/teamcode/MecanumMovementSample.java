package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

import com.chucklelib.direction.Direction;
import com.chucklelib.movement.MecanumChassis;
import com.chucklelib.movement.teleop.TeleopController;

@TeleOp(name="MecanumMovementSample", group="TeleopSample")
public class MecanumMovementSample extends LinearOpMode {
    private RobotHardware hardware;
    private MecanumChassis chassis;
    private TeleopController controller;
    
    @Override
    public void runOpMode() {
        hardware = new RobotHardware(hardwareMap);
        chassis = new MecanumChassis(hardware);
        controller = new TeleopController(chassis);
        // if you're feeling dangerous:
        // controller = new TeleopController(new MecanumChassis(new RobotHardware(hardwareMap)));
        
        // Movement and turning run forward by default
        // controller.setMoveDirection(Direction.FORWARD);
        // controller.setTurnDirection(Direction.FORWARD);
        
        controller.setMoveDirection(Direction.REVERSE);
        // controller.setTurnDirection(Direction.REVERSE);
        
        // power/speed tuning
        // controller.setMovePower(2.0);
        // controller.setTurnPower(2.0);

        waitForStart();

        while (opModeIsActive()) {
            // controller.robotCentricMove(gamepad1);
            controller.fieldCentricMove(gamepad1);
        }
    }
}
