package org.firstinspires.ftc.teamcode.test;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.other.apriltags;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Autonomous
public class AprilTagTestAuto extends OpMode{

    double INCHTOTIME = 1 / 6.5; //At max power, the robot moves around 6.5 inches a second
    org.firstinspires.ftc.teamcode.action.clawControl clawControl = new clawControl();
    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();
    org.firstinspires.ftc.teamcode.other.apriltags apriltags = new apriltags();
    org.firstinspires.ftc.teamcode.other.tfSetup tfSetup = new tfSetup();
    ElapsedTime trackKeeper = new ElapsedTime();
    int aprilTagID;
    boolean aprilTagFound = false;
    boolean linedUp = false;
    double distance;
    int robotSide;
    int actions;
    ElapsedTime moveTime = new ElapsedTime();
    int multiplier;
    double tagXPos = 0;
    double tagYPos = 0;
    int timesReset = 0;
    double powerX;
    double powerY;
    double rotPower;

    @Override
    public void init() {
        mecanumDrive.init(this);
        clawControl.init(this);
        linearSlide.init(this);
        tfSetup.init(this);
        mecanumDrive.runWithoutEncoder();
    }

    public void start() {
        tfSetup.quit();
        apriltags.init(this);
        trackKeeper.reset();
    }

    public void init_loop() {
        if(gamepad1.b) {
            if(gamepad1.dpad_left) {
                aprilTagID = 4;
            } else if (gamepad1.dpad_up) {
                aprilTagID = 5;
            } else if (gamepad1.dpad_right) {
                aprilTagID = 6;
            }
        } else if (gamepad1.x) {
            if(gamepad1.dpad_left) {
                aprilTagID = 1;
            } else if (gamepad1.dpad_up) {
                aprilTagID = 2;
            } else if (gamepad1.dpad_right) {
                aprilTagID = 3;
            }
        }

        if (gamepad1.left_bumper) {
            robotSide = 0;
            multiplier = 1;
        } else if (gamepad1.right_bumper) {
            robotSide = 1;
            multiplier = -1;
        }

        if(trackKeeper.time() <= 0.5) {
            linearSlide.setPower(-0.5);
        } else if (trackKeeper.time() >= 0.5) {
            linearSlide.setPower(-0.06);
        }
    }

    @Override
    public void loop() {
        if(!aprilTagFound) {
            scanForAprilTag(apriltags.runTag(aprilTagID));
        } else if (aprilTagFound) {
                tagXPos = findTagX(apriltags.getxLocation(aprilTagID), apriltags.getYaw(aprilTagID)) - 5;   //Find X position
                tagYPos = findTagY(apriltags.getyLocation(aprilTagID), apriltags.getzLocation(aprilTagID), apriltags.getPitch(aprilTagID)) - 5.4;  //Find Y pos
            if(tagYPos > 0) {
                lineUpToTag(tagXPos, tagYPos);
            }
        }

        mecanumDrive.telemetryOutput();
        telemetry.addData("Tag Found? ", aprilTagFound);
        telemetry.addData("Tag X: ", tagXPos);
        telemetry.addData("Tag Y: ", tagYPos);
        telemetry.addData("Returned X: ", apriltags.getxLocation(aprilTagID));
        telemetry.addData("Returned Y: ", apriltags.getyLocation(aprilTagID));
    }

    public double findTagX(double x, double yaw) {
        //return ((x * Math.cos(Math.abs(yaw))) - 5);                                               //Formula that was made on 2/14/2024
        return (x * Math.cos(Math.toRadians(yaw)));
    }

    public double findTagY(double y, double z, double pitch) {
        //return ((3 * (y * Math.cos(Math.toDegrees(Math.asin(z / y))) + Math.abs(pitch))) / 4);    This was pretty off, but I typed it wrong
        return ((3 * (y * Math.cos(Math.asin(z / y) + Math.toRadians(pitch))) / 4) - 2);
    }

    public void lineUpToTag(double tagX, double tagY) {
        if(timesReset == 0){
            distance = Math.sqrt(Math.pow(tagX, 2) + Math.pow(tagY, 2));
            moveTime.reset();
            timesReset++;
        }
        time = distance * INCHTOTIME * 0.5;
        time = time + (time * 0.25);
        telemetry.addData("Time to move: ", time);
        telemetry.addData("Distance from board (inch): ", distance);
        if(tagX > tagY) {
            powerX = 1;
            powerY = tagY / tagX;
            rotPower = tagY / tagX;
        } else if (tagY > tagX) {
            powerX = tagX / tagY;
            powerY = 1;
            rotPower = tagX / tagY;
        }
        telemetry.addData("PowerX: ", powerX);
        telemetry.addData("PowerY: ", powerY);
        if(moveTime.time() <= time) {
            mecanumDrive.setPower(.25 * powerX, -.25 * powerY, 0.05 * rotPower);
        } else if (moveTime.time() >= time) {
            mecanumDrive.setPower(0, 0, 0);
        }
    }

    public void scanForAprilTag(boolean foundTag) {
        mecanumDrive.slowMode(true); //Despite what it says, this will make the robot go at max speed
        if(foundTag) {
            aprilTagFound = true;
            mecanumDrive.setPower(0, 0, 0); //stops the motors
        } else {
            if(!(moveTime.time() >= 0.75)) {
                mecanumDrive.setPower(0.5 * multiplier, 0, 0.0375 * multiplier);
            } else if (moveTime.time() >= 3) {
                moveTime.reset();
            } else {
                mecanumDrive.setPower(0, 0, 0);
            }
        }
    }
}
