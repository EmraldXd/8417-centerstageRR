package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.action.mecanumDrive;

@Autonomous(name = "Wheel Test", group = "Main")
public class wheelTest extends OpMode {

    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();

    DcMotor fR;
    DcMotor fL;
    DcMotor bR;
    DcMotor bL;

    @Override
    public void init() {

        mecanumDrive.init(this);

    }

    @Override
    public void loop() {

        /**
         * This code is used to test each wheel individually, and as a single unit. This should
         * help determine if the wheels are not spinning correctly. If they are, then they should
         * move clockwise or counterclockwise, allowing us to see which wheels need to be reversed.
         */

        if(gamepad1.a) {
            if(gamepad1.dpad_up) {
                fR.setPower(1.0);
            }else if(gamepad1.dpad_down) {
                fR.setPower(-1.0);
            }
        }
        if(gamepad1.b) {
            if(gamepad1.dpad_up) {
                fL.setPower(1.0);
            }else if(gamepad1.dpad_down) {
                fL.setPower(-1.0);
            }
        }
        if(gamepad1.y) {
            if(gamepad1.dpad_up) {
                bR.setPower(1.0);
            }else if(gamepad1.dpad_down) {
                bR.setPower(-1.0);
            }
        }
        if(gamepad1.x) {
            if(gamepad1.dpad_up) {
                bL.setPower(1.0);
            }else if(gamepad1.dpad_down) {
                bL.setPower(-1.0);
            }
        }


        /**
         * This code is used to test the wheels strafing ability. Assuming that the above works
         * well, this will allow us to see whether or not the wheels are working correctly. If the
         * wheels work to this point, but DO NOT work in these if statements, then that means
         * something is really off, and we may just go for a tank drive until the issue is resolved.
         */
        if(gamepad1.left_bumper) {
            if(gamepad1.dpad_left) {
                fR.setPower(-1.0);
                bR.setPower(1.0);
            }else if(gamepad1.dpad_right) {
                fR.setPower(1.0);
                bR.setPower(-1.0);
            }
        }
        if(gamepad1.right_bumper) {
            if(gamepad1.dpad_left) {
                fL.setPower(1.0);
                bL.setPower(-1.0);
            }else if(gamepad1.dpad_right) {
                fL.setPower(-1.0);
                bL.setPower(1.0);
            }
        }

        /**
         * This code is used to test the wheels ability to work together. If everything works until
         * this point, we have proven the issue that the issue is not the wheels, but it is the
         * mecanum drive code, and we are now back to square one.
         */
        if(gamepad1.left_trigger > 0) {
            if(gamepad1.dpad_right) {
                mecanumDrive.setPower(1.0, 0.0 ,0.0);
            }else if(gamepad1.dpad_left) {
                mecanumDrive.setPower(-1.0, 0.0, 0.0);
            }else if(gamepad1.dpad_up) {
                mecanumDrive.setPower(0.0, 1.0, 0.0);
            }else if(gamepad1.dpad_down) {
                mecanumDrive.setPower(0.0, -1.0, 0.0);
            }
        }
        if(gamepad1.right_trigger > 0) {
            if(gamepad1.dpad_right) {
                mecanumDrive.setPower(0.0, 0.0, 1.0);
            }else if(gamepad1.dpad_left) {
                mecanumDrive.setPower(0.0, 0.0, -1.0);
            }
        }
    }
}
