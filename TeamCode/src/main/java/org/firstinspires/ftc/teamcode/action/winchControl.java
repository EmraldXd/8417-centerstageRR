package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class winchControl {

    public ElapsedTime discoBugSquasher = new ElapsedTime(); //We have no clue if the disco bug will remain when this is added, but we cant take any chances
    static final DecimalFormat df = new DecimalFormat("0.00");
    Servo lock;
    DcMotor winch;
    double lockPos = 0.81;
    double unlockPos = 0.64;
    double delay = 0.50;
    double winchPower = 1.00;
    boolean unlocked;
    Telemetry telemetry;
    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        lock = hardwareMap.get(Servo.class, "lock");
        winch = hardwareMap.get(DcMotor.class, "winch");
        telemetry = opMode.telemetry;
        lock.setPosition(lockPos);
        unlocked = false;
        winch.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runWithEncoder() {
        winch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoder() {
        winch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void lockSet (boolean isPressed) {
        if(isPressed && !unlocked && discoBugSquasher.time() >= delay) {
            lock.setPosition(unlockPos);
            unlocked = true;
            discoBugSquasher.reset();
        } else if (isPressed && unlocked && discoBugSquasher.time() >= delay) {
            lock.setPosition(lockPos);
            unlocked = false;
            discoBugSquasher.reset();
        }
    }

    public void windWinch(boolean isPressed) {
        if(unlocked && isPressed) {
            winch.setPower(winchPower);
        } else if(!(unlocked && isPressed)){
            winch.setPower(0.00);
        }
    }

    public void telemetry() {
        telemetry.addData("Unlocked?", unlocked);
        telemetry.addData("Winch power: ", df.format(winchPower));
        telemetry.addData("Winch position; ", df.format(winch.getCurrentPosition()));
    }
}
