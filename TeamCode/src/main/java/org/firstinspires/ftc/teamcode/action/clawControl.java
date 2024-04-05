package org.firstinspires.ftc.teamcode.action;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.DecimalFormat;

public class clawControl {

    /*
        These doubles are only initialized. When the variables for each servo location is figured
        out, add an = and the position. (Delete this comment afterwards)
     */
    public ElapsedTime delay1 = new ElapsedTime(); //This will delay claw inputs
    public ElapsedTime delay2 = new ElapsedTime(); //This will too!
    public ElapsedTime discoBugSquasher = new ElapsedTime(); //we had a bug that turned the robot into a DJ
    static final DecimalFormat df = new DecimalFormat("0.00");
    //Outside Claw is ACTUALLY the inside claw, and vice versa
    double openClawIn = 0.01;
    double closedClawIn = 0.175;
    double closedClawOut = 0.85;
    double openClawOut = 0.675;
    double lowestPosition = 1.00;
    double highestPosition = 0.75;
    Servo las;
    Servo inClaw;
    Servo outClaw;
    boolean inClawClosed = true;
    boolean outClawClosed = true;
    double delayLength = 0.75;
    boolean autoOpenIn = false;
    boolean autoOpenOut = false;
    boolean isUp = false;

    //Makes sure the claw is closed, and the arm is at the lowest position.
    public void init(@NonNull OpMode opMode)
    {
        hardwareMap = opMode.hardwareMap;
        try {
            // "Try"es to run the code. If it throws an error, it catches
            // and ignores the error.
            inClaw = hardwareMap.get(Servo.class, "InClaw");
        } catch (Exception ignored) {}
        try {
            outClaw = hardwareMap.get(Servo.class, "OutClaw");
        } catch (Exception ignored) {}
        las = hardwareMap.get(Servo.class, "las");
        las.setPosition(lowestPosition);
        try {
            inClaw.setPosition(closedClawIn);
        } catch (Exception ignored) {}
        try {
            outClaw.setPosition(closedClawOut); //Turn this to closed
        } catch (Exception ignored) {}
        telemetry = opMode.telemetry;
    }

    /**
     * This opens the inside and outside claws. Note that the inside claw WILL NOT open if the
     * outside claw is currently closed, nor will the outside claw close if the inside claw is
     * open. This will prevent the two claws from killing each other. This has happened before.
     * @param rightBumperPressed opens and closes the small claw.
     * @param leftBumperPressed opens and closes the large claw.
     */
    public void openClaw(boolean rightBumperPressed, boolean leftBumperPressed)
    {
        if (delay1.time() > delayLength && !outClawClosed) {
            if (rightBumperPressed && inClawClosed) {
                try {
                    inClaw.setPosition(openClawIn);
                } catch (Exception ignored) {}
                inClawClosed = false;
                delay1.reset();
            }
            else if (rightBumperPressed)
            {
                try {
                    inClaw.setPosition(closedClawIn);
                } catch (Exception ignored) {}
                inClawClosed = true;
                delay1.reset();
            }
        }
        if(delay2.time() > delayLength)
        {
            if (leftBumperPressed && outClawClosed) {
                delay2.reset();
                try {
                    outClaw.setPosition(openClawOut);
                } catch (Exception ignored) {}
                outClawClosed = false;
            }
            else if(leftBumperPressed && inClawClosed)
            {
                delay2.reset();
                try {
                    outClaw.setPosition(closedClawOut);
                } catch (Exception ignored) {}
                outClawClosed = true;
            }
        }
    }

    /**
     * This moves the arm up and down to line it up with the backdrop via user control
     * @param upPressed lets the robot know that the up button has be pressed
     * @param downPressed lets the robot know that the down button has been pressed
     */
    public void moveArm(boolean upPressed, boolean downPressed)
    {
        if(discoBugSquasher.time() > delayLength)
        {
            if (upPressed)
            {
                las.setPosition(highestPosition);
                discoBugSquasher.reset();
            }
            else if (downPressed)
            {
                las.setPosition(lowestPosition);
                discoBugSquasher.reset();
            }
        }
    }

    /**
     * This just opens the inside claw in our autonomous code.
     */
    public void openInsideClaw() {
        if(autoOpenIn) {
            try {
                inClaw.setPosition(closedClawIn);
            } catch (Exception ignored) {}
            autoOpenIn = false;
        } else if (!autoOpenIn) {
            try {
                inClaw.setPosition(openClawIn);
            } catch (Exception ignored) {}
            autoOpenIn = true;
        }
    }

    /**
     * This is the code above's littlest brother.
     */
    public void openOutsideClaw() {
        if(autoOpenOut) {
            try {
                outClaw.setPosition(closedClawOut);
            } catch (Exception ignored) {}
            autoOpenOut = false;
        } else if (!autoOpenOut) {
            try {
                outClaw.setPosition(openClawOut);
            } catch (Exception ignored) {}
            autoOpenOut = true;
        }
    }

    /**
     * This is the middle child. Theres not much more that is needed to be said.
     */
    public void toggleArm() {
        if(isUp) {
            las.setPosition(lowestPosition);
            isUp = false;
        } else if (!isUp) {
            las.setPosition(highestPosition);
            isUp = true;
        }
    }

    public void telemetry()
    {
        telemetry.addData("Arm position : ", df.format(las.getPosition()));
        telemetry.addData("Inside claw closed? ", inClawClosed);
        telemetry.addData("Outside claw closed? ", outClawClosed);
        try {
            telemetry.addData("InsideClawPosition", inClaw.getPosition());
        } catch (Exception ignored) {
            telemetry.addData("This Claw", " does not exist");
        }
        try {
            telemetry.addData("OutsideClawPosition", outClaw.getPosition());
        } catch (Exception ignored) {
            telemetry.addData("The Claw ", "does not exist");
        }
        telemetry.addData("Delay timer: ", delay1.time());
    }
}
