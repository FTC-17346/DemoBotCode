package org.firstinspires.ftc.teamcode.demobot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Demo Bot TeleOp", group="DemoBot")
public class DemoBotTeleOp extends OpMode {
    DemoBotHardware robot = new DemoBotHardware(this);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;

        robot.drive(drive, turn);

        telemetry.addData("Drive", "Left Stick");
        telemetry.addData("Turn", "Right Stick");

        telemetry.addData("Drive Power", "%.2f", drive);
        telemetry.addData("Turn Power",  "%.2f", turn);
        telemetry.update();
    }

    public void motorTest() {
        if (gamepad1.a) {
            robot.frontLeftDrive.setPower(1);
        } else {
            robot.frontLeftDrive.setPower(0);
        }

        if (gamepad1.b) {
            robot.frontRightDrive.setPower(1);
        } else {
            robot.frontRightDrive.setPower(0);
        }

        if (gamepad1.x) {
            robot.backLeftDrive.setPower(1);
        } else {
            robot.backLeftDrive.setPower(0);
        }

        if (gamepad1.y) {
            robot.backRightDrive.setPower(1);
        } else {
            robot.backRightDrive.setPower(0);
        }
    }
}