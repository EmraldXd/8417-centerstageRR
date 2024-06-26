package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class mecanumDrive {

    static final DecimalFormat df = new DecimalFormat("0.00");
    //DECLARE NULL
    DcMotor fR;
    DcMotor fL;
    DcMotor bR;
    DcMotor bL;
    DcMotor lE;
    double fRPwr;
    double fLPwr;
    double bRPwr;
    double bLPwr;
    Telemetry telemetry;
    //DECLARE CUSTOM
    static double totalSpeed = 0.75;
    double slowSpeed = 0.50;

    //METHODS
    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        fR = hardwareMap.get(DcMotor.class, "Front Right");
        fL = hardwareMap.get(DcMotor.class, "Front Left");
        bR = hardwareMap.get(DcMotor.class, "Back Right");
        bL = hardwareMap.get(DcMotor.class, "Back Left");
        lE = hardwareMap.get(DcMotor.class, "leftEncoder");

        bR.setDirection(DcMotorSimple.Direction.REVERSE);
        fL.setDirection(DcMotorSimple.Direction.REVERSE);

        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Sets the mode of the motors to run WITH encoders
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Speeds up and slows down the robot during driver-controlled.
     * @param enable player 1's "turbo" button (LB)
     */
    public void slowMode(Boolean enable) {
        slowSpeed = enable ? 1.00 : 0.50;
    }

    public void runUsingEncoder() {
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoder() {
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runToPosition() {
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Sets the power of the motors during driver-controlled, or our drive-by-time autonomous.
     * @param x is the X position of player 1's left stick, used for left and right movement.
     *          This is also used for the strafe value in our drive-by-time auto.
     * @param y is the Y position of player 1's left stick, used for forward and backward movement.
     *          This is also used for the drive value in our drive-by-time auto.
     * @param rot is the X position of player 1's right stick, used for clockwise and
     *            counter-clockwise rotation. This is also used for the rotation value in our
     *            drive-by-time auto.
     */
    public void setPower(double x, double y, double rot) {
        x = x * 1.1;
        y = -y;
        rot = -rot;
        x = -x;

        //motor power math
        double ratio = Math.max((Math.abs(x) + Math.abs(y) + Math.abs(rot)), 1);
        fRPwr = (y - x - rot) / ratio;
        fLPwr = (y + x + rot) / ratio;
        bRPwr = (y - x + rot) / ratio;
        bLPwr = (y + x - rot) / ratio;

        fR.setPower(fRPwr * totalSpeed * slowSpeed);
        fL.setPower(fLPwr * totalSpeed * slowSpeed);
        bR.setPower(bRPwr * totalSpeed * slowSpeed);
        bL.setPower(bLPwr * totalSpeed * slowSpeed);
    }

    public void telemetryOutput() {
        telemetry.addData("fRMotorPwr", df.format(fRPwr));
        telemetry.addData("bRMotorPwr", df.format(bRPwr));
        telemetry.addData("bLMotorPwr", df.format(bLPwr));
        telemetry.addData("fLMotorPwr", df.format(bLPwr));
        // Ticks
        telemetry.addData("Front Left Ticks", fL.getCurrentPosition());
        telemetry.addData("Front Right Ticks", fR.getCurrentPosition());
        telemetry.addData("Back Left Ticks", bL.getCurrentPosition());
        telemetry.addData("Back Right Ticks", bR.getCurrentPosition());
        telemetry.addData("encoder pos:", lE.getCurrentPosition());
    }
}
