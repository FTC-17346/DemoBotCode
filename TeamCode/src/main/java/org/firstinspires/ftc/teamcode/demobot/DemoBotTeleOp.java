package org.firstinspires.ftc.teamcode.demobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Demo Bot TeleOp", group="DemoBot")
public class DemoBotTeleOp extends OpMode {
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
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        robot.drive(drive, turn);

        telemetry.addData("Drive", "Left Stick");
        telemetry.addData("Turn", "Right Stick");

        telemetry.addData("Drive Power", "%.2f", drive);
        telemetry.addData("Turn Power",  "%.2f", turn);
        telemetry.update();

        if (gamepad1.x) {
            if (!xWasPressed) {
                shooterState = "charging";
                shooterTimer.reset();
            }
            xWasPressed = gamepad1.x;
            if (shooterState == "charging") {
                if (shooterTimer.seconds() < 3) {
                    robot.shooter.setPower(1);
                } else {
                    shooterState = "shootup";
                    shooterTimer.reset();
                }
            }
            if (shooterState == "shootup" ) {
                if (shooterTimer.seconds() < 0.2) {
                    robot.pushServo.setPosition(1);
                } else {
                    shooterState = "shootdown";
                    shooterTimer.reset();
                }
            }
            if (shooterState == "shootdown" ) {
                if (shooterTimer.seconds() < 0.2) {
                    robot.pushServo.setPosition(0);
                } else {
                    shooterState = "shootup";
                    shooterTimer.reset();
                }
            }

        } else {
            shooterState = "off";
            robot.shooter.setPower(0);
            robot.pushServo.setPosition(0);
        }
        telemetry.addData("Shooter State", shooterState);
        telemetry.addData("Shooter Speed", robot.shooter.getVelocity());

    }

    public void motorTest() {
        if (gamepad1.a) {
            robot.frontLeft.setPower(1);
        } else {
            robot.frontLeft.setPower(0);
        }

        if (gamepad1.b) {
            robot.frontRight.setPower(1);
        } else {
            robot.frontRight.setPower(0);
        }

        if (gamepad1.x) {
            robot.backLeft.setPower(1);
        } else {
            robot.backLeft.setPower(0);
        }

        if (gamepad1.y) {
            robot.backRight.setPower(1);
        } else {
            robot.backRight.setPower(0);
        }
    }
}