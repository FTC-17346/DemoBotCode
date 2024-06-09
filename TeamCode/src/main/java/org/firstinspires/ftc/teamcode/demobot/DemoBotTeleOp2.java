package org.firstinspires.ftc.teamcode.demobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Objects;

@TeleOp(name = "Demo Bot TeleOp2", group = "DemoBot")
public class DemoBotTeleOp2 extends OpMode {
    DemoBotHardware robot;

    @Override
    public void init() {
        robot = new DemoBotHardware(this);
        robot.init();
    }


    boolean xWasPressed = false;
    ElapsedTime shooterTimer = new ElapsedTime();
    // "off" nothing happening
    String shooterState = "off";

    @Override
    // @Override is what prevents this program from showing up on the driver hub - comment it out to use it

    public void loop() {

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        robot.drive((drive/2), (turn/2));

        telemetry.addData("Drive", "Left Stick");
        telemetry.addData("Turn", "Right Stick");

        telemetry.addData("Drive Power", "%.2f", drive);
        telemetry.addData("Turn Power", "%.2f", turn);
        telemetry.addData("Timer", shooterTimer);
        telemetry.addData("Shooter State", shooterState);
        telemetry.addData("Left Motor", robot.getLeftPower());
        telemetry.addData("Right Motor", robot.getRightPower());
        telemetry.update();

        xWasPressed = gamepad1.x;
        robot.pushServo.setPosition(0.8);

        if (gamepad1.right_bumper) {
            shooterState = "charging";
            telemetry.addData(
                    "state",shooterState
            );
/*            telemetry.addData("Shooter State", shooterState);
            if (!xWasPressed) {
                shooterState = "charging";
                shooterTimer.reset();
                }
            if (Objects.equals(shooterState, "charging")) {
                if (shooterTimer.seconds() < 3) {
*/
            robot.shooter.setPower(1);
            shooterTimer.reset();
            telemetry.update();
//            while (shooterTimer.seconds() < 2) {
//                robot.shooter.setPower(1);
//                robot.pushServo.setPosition(0.85);
//            }
            if (xWasPressed) {
//            if (shooterTimer.seconds() > 2) {
                robot.pushServo.setPosition(.9);
                telemetry.addData("Shooter State", shooterState);
            } else {
                shooterState = "charging";
                telemetry.addData("Shooter State", shooterState);
            }
            telemetry.update();
        }
//        xWasPressed = false;

        else {
            shooterState = "off";
            robot.shooter.setPower(0);
            // robot.shooter.
        }

//        telemetry.addData("Shooter State", shooterState);
//        telemetry.addData("Shooter Speed", robot.shooter.getVelocity());
//        telemetry.update();
    }

    public void motorTest() {
        double motorPower = 0.5;

        if (gamepad1.a) {
            robot.frontLeft.setPower(0.5);
        } else {
            robot.frontLeft.setPower(0);
        }

        if (gamepad1.b) {
            robot.frontRight.setPower(0.5);
        } else {
            robot.frontRight.setPower(0);
        }

        if (gamepad1.x) {
            robot.backLeft.setPower(0.5);
        } else {
            robot.backLeft.setPower(0);
        }

        if (gamepad1.y) {
            robot.backRight.setPower(0.5);
        } else {
            robot.backRight.setPower(0);
        }
    }
}