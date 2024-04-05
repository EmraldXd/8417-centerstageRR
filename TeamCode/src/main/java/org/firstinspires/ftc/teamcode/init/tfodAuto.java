package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.other.apriltags;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Disabled
@Autonomous
public class tfodAuto extends OpMode {
    double INCHTOTIME = 1 / 6.5;
    int fieldSide;
    int robotAction;
    String colorProp;
    String position;
    org.firstinspires.ftc.teamcode.other.tfSetup tfSetup = new tfSetup();
    public ElapsedTime moveTime = new ElapsedTime(); //How long autonomous has been doin' its thing for
    ElapsedTime actionRuntime = new ElapsedTime();
    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    org.firstinspires.ftc.teamcode.action.clawControl clawControl = new clawControl();
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();
    org.firstinspires.ftc.teamcode.other.apriltags apriltags = new apriltags();
    int teamColor;
    boolean aprilTagFound;
    int timesReset = 0;
    double distance;
    double tagXPos = 0;
    double tagYPos = 0;
    double powerX;
    double powerY;
    double rotPower;
    double multiplier = 0.5;
    int reps;


    @Override
    public void init() {
        tfSetup.init(this);
        mecanumDrive.init(this);
        linearSlide.init(this);
        clawControl.init(this);
        mecanumDrive.runWithoutEncoder();
    }

    public void init_loop() {
        colorProp = tfSetup.runTfodLabel("Fix this if we go back to it");
        position = tfSetup.runTfodSide();
        if (gamepad1.dpad_left) {
            fieldSide = 0;
        } else if (gamepad1.dpad_right) {
            fieldSide = 1;
        }
        tfSetup.telemetry();
        if (robotAction == 0) {
            waitThenGoToNextAction(0.50);
        } else if (robotAction == 1) {
            moveSlidesForTime(0.9, 0.75);
        }
        if (gamepad1.b) {
            teamColor = 0;
        } else if (gamepad1.x) {
            teamColor = 1;
        }
    }

    public void start() {
        robotAction = 0;
        tfSetup.quit();
        apriltags.init(this);
    }

    @Override
    public void loop() {
        if (teamColor == 0) {
            //Code for Red on Right side
            if (colorProp != null) {
                if (fieldSide == 1 && colorProp.equals("RedProp")) {
                    if (position.equals("left")) {                                                  //Left spike mark
                        if (robotAction == 0) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 1) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 2) {
                            mecanumDrive(0.2, 1, 0, 0);
                        } else if (robotAction == 3) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 4) {
                            mecanumDrive(.345, 1, 0.25, 0);
                        } else if (robotAction == 5) {
                            waitThenGoToNextAction(0.35);
                        } else if (robotAction == 6) {
                            mecanumDrive(0.55, 0, 0, -1);
                        } else if (robotAction == 7) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 8) {
                            mecanumDrive(2, .2, 0, 0);
                        } else if (robotAction == 9) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 10) {
                            mecanumDrive(1, -0.2, 0, 0);
                        } else if (robotAction == 11) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 12) {
                            moveSlidesForTime(0.3, -0.50);
                        } else if (robotAction == 13) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 14) {
                            toggleOutsideClaw();
                        } else if (robotAction == 15) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 16) {
                            moveSlidesForTime(0.05, 0.5);
                        } else if (robotAction == 17) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 18) {
                            toggleOutsideClaw();
                        } else if (robotAction == 19) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 20) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 21) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 22) {
                            mecanumDrive(1, -.2, 0, 0);
                        } else if (robotAction == 23) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 24) {
                            mecanumDrive(1.50, 0, -0.50, 0);
                        } else if (robotAction == 25) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 26) {
                            mecanumDrive(1.1, 0, 0, 1);
                        } else if (robotAction == 27) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 28) {
                            mecanumDrive(1.25, 0.5, 0, -0.025);
                        } else if (robotAction == 29) {
                            waitThenGoToNextAction(0.2);
                        } else if(!aprilTagFound && robotAction == 30) {
                            scanForAprilTag(apriltags.runTag(4), -1);
                        } else if (aprilTagFound && robotAction == 30) {
                            armToggle();
                        } else if (aprilTagFound && robotAction == 31) {
                            toggleOutsideClaw();
                        } else if (aprilTagFound && robotAction == 32) {
                            tagXPos = findTagX(apriltags.getxLocation(4), apriltags.getYaw(4)) - 5;   //Find X position
                            tagYPos = findTagY(apriltags.getyLocation(4), apriltags.getzLocation(4), apriltags.getPitch(4)) - 5.4;  //Find Y pos
                            if(tagYPos > 0) {
                                lineUpToTag(tagXPos, tagYPos);
                            }
                        } else if (robotAction == 33) {
                            toggleSmallClaw();
                        } else if (robotAction == 34) {
                            mecanumDrive(0.75, -0.2, 0, 0);
                        } else if (robotAction == 35) {
                            armToggle();
                        } else if (robotAction == 36) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 37) {
                            mecanumDrive(1, 0, 0.75, 0);
                        } else if (robotAction == 38) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction ==39) {
                            mecanumDrive(0.5, .5, 0, 0);
                        }
                    } else if (position.equals("middle")) {                                         //Middle spike mark
                        /*if (robotAction == 0) {
                            mecanumDrive(0.2, 1, 0, 0);
                        } else if (robotAction == 1) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 2) {
                            mecanumDrive(3.50, 0, 0.50, 0);
                        } else if (robotAction == 3) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 4) {
                            toggleSmallClaw();
                        } */
                        if (robotAction == 0) {
                            moveSlidesForTime(0.7, 0.50);
                        } else if (robotAction == 1) {
                            mecanumDrive(0.2, 0.50, 0, 0);
                        } else if (robotAction == 2) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 3) {
                            mecanumDrive(1.75, .5, 0, 0);
                        } else if (robotAction == 4) {
                            waitThenGoToNextAction(0.2);
                        }else if (robotAction == 5) {
                            mecanumDrive(1.25, -0.2, 0, 0);
                        } else if (robotAction == 6) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 7) {
                            moveSlidesForTime(0.5, -0.50);
                        } else if (robotAction == 8) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 9) {
                            toggleOutsideClaw();
                        } else if (robotAction == 10) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 11) {
                            moveSlidesForTime(0.1, 0.5);
                        } else if (robotAction == 12) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 13) {
                            toggleOutsideClaw();
                        } else if (robotAction == 14) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 15) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 16) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 17) {
                            mecanumDrive(.85, -0.50, 0, 0);
                        } else if (robotAction == 18) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 19) {
                            mecanumDrive(1.75, 0, 0.50, 0.025);
                        } else if (robotAction == 20) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 21) {
                            mecanumDrive(1.4, 0, 0, 0.5);
                        } else if (robotAction == 22) {
                            waitThenGoToNextAction(0.2);
                        } else if(!aprilTagFound && robotAction == 23) {
                            scanForAprilTag(apriltags.runTag(4), -1);
                        } else if (aprilTagFound && robotAction == 23) {
                            armToggle();
                        } else if (robotAction == 24) {
                            toggleOutsideClaw();
                        } else if (robotAction == 25) {
                            tagXPos = findTagX(apriltags.getxLocation(5), apriltags.getYaw(5)) - 5;   //Find X position
                            tagYPos = findTagY(apriltags.getyLocation(5), apriltags.getzLocation(5), apriltags.getPitch(5)) - 5.4;  //Find Y pos
                            if(tagYPos > 0) {
                                lineUpToTag(tagXPos, tagYPos);
                            }
                        } else if (robotAction == 26) {
                            toggleSmallClaw();
                        } else if (robotAction == 27) {
                            mecanumDrive(0.75, -0.2, 0, 0);
                        } else if (robotAction == 28) {
                            armToggle();
                        } else if (robotAction == 29) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 30) {
                            mecanumDrive(0.9, 0, 0.75, 0);
                        } else if (robotAction == 31) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 32) {
                            mecanumDrive(0.75, .5, 0, 0);
                        }
                    }
                }
            } else if (fieldSide == 1 || position.equals("right")) {
                /*if (robotAction == 0) {
                    mecanumDrive(0.2, 1, 0, 0);
                } else if (robotAction == 1) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 2) {
                    mecanumDrive(3.50, 0, 0.50, 0);
                } else if (robotAction == 3) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 4) {
                    toggleSmallClaw();
                } */
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 1) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 2) {
                    mecanumDrive(0.2, 1, 0, 0);
                } else if (robotAction == 3) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 4) {
                    mecanumDrive(.5, .75, 0.1, 0);
                } else if (robotAction == 5) {
                    waitThenGoToNextAction(0.35);
                } else if (robotAction == 6) {
                    mecanumDrive(0.5, 0, 0, 1);
                } else if (robotAction == 7) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 8) {
                    mecanumDrive(1.2, .2, 0, 0);
                } else if (robotAction == 9) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 10) {
                    mecanumDrive(1, -0.2, 0, 0);
                } else if (robotAction == 11) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 12) {
                    moveSlidesForTime(0.5, -0.50);
                } else if (robotAction == 13) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 14) {
                    toggleOutsideClaw();
                } else if (robotAction == 15) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 16) {
                    moveSlidesForTime(0.05, 0.5);
                } else if (robotAction == 17) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 18) {
                    toggleOutsideClaw();
                } else if (robotAction == 19) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 20) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 21) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 22) {
                    mecanumDrive(.75, -0.2, 0.0, 0.0);
                } else if (robotAction == 23) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 24) {
                    mecanumDrive(1.75, 0, 0.50, 0.025);
                } else if (robotAction == 25) {
                    waitThenGoToNextAction(0.1);
                } else if (robotAction == 26) {
                    mecanumDrive(.6, 0.75, 0, 0);
                } else if (robotAction == 27) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 28) {
                    waitThenGoToNextAction(0.2);
                } else if(!aprilTagFound && robotAction == 29) {
                    scanForAprilTag(apriltags.runTag(4), -1);
                } else if (aprilTagFound && robotAction == 29) {
                    armToggle();
                } else if (aprilTagFound && robotAction == 30) {
                    toggleOutsideClaw();
                } else if (aprilTagFound && robotAction == 31) {
                    tagXPos = findTagX(apriltags.getxLocation(6), apriltags.getYaw(6)) - 5;   //Find X position
                    tagYPos = findTagY(apriltags.getyLocation(6), apriltags.getzLocation(6), apriltags.getPitch(6)) - 5.4;  //Find Y pos
                    lineUpToTag(tagXPos, tagYPos);
                } else if (robotAction == 32) {
                    toggleSmallClaw();
                } else if (robotAction == 33) {
                    mecanumDrive(0.75, -0.2, 0, 0);
                } else if (robotAction == 34) {
                    armToggle();
                } else if (robotAction == 35) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 36) {
                    mecanumDrive(1.2, 0, 0.75, 0);
                } else if (robotAction == 37) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 38) {
                    mecanumDrive(0.75, .5, 0, 0);
                }
            }
        } else {
            //Code for Blue on right side
            if (colorProp != null) {
                if (fieldSide == 1 && colorProp.equals("BlueProp")) {
                    if (position.equals("left")) {                                                      //Left spike mark
                        if (robotAction == 0) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 1) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 2) {
                            mecanumDrive(0.2, 1, 0, 0);
                        } else if (robotAction == 3) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 4) {
                            mecanumDrive(.35, .8, 0, 0);
                        } else if (robotAction == 5) {
                            waitThenGoToNextAction(0.35);
                        } else if (robotAction == 6) {
                            mecanumDrive(0.45, 0, 0, -1);
                        } else if (robotAction == 7) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 8) {
                            mecanumDrive(1.25, .2, 0, 0);
                        } else if (robotAction == 9) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 10) {
                            mecanumDrive(0.85, -0.2, 0, 0);
                        } else if (robotAction == 11) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 12) {
                            moveSlidesForTime(0.5, -0.50);
                        } else if (robotAction == 13) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 14) {
                            toggleSmallClaw();
                        } else if (robotAction == 15) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 16) {
                            moveSlidesForTime(0.05, 0.5);
                        } else if (robotAction == 17) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 18) {
                            toggleSmallClaw();
                        } else if (robotAction == 19) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 20) {
                            moveSlidesForTime(0.75, 0.50);
                        } else if (robotAction == 21) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 22) {
                            mecanumDrive(1, -.5, 0, 0);
                        }/* else if (robotAction == 23) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 24) {
                            mecanumDrive(1.5, 0, 0.50, 0);
                        } else if (robotAction == 25) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 26) {
                            moveSlidesForTime(0.5, -0.5);
                        } else if (robotAction == 27) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 28) {
                            armToggle();
                        } else if (robotAction == 29) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 30) {
                            mecanumDrive(2.5, 0.75, 0, 0);
                        } else if (robotAction == 31) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 32) {
                            toggleSmallClaw();
                        }*/
                    } else if (position.equals("middle")) {                                             //Middle spike mark
                        if (robotAction == 0) {
                            moveSlidesForTime(0.2, 0.50);
                        } else if (robotAction == 1) {
                            mecanumDrive(0.2, 0.50, 0, 0);
                        } else if (robotAction == 2) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 3) {
                            mecanumDrive(1.50, .5, 0, 0);
                        } else if (robotAction == 4) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 5) {
                            mecanumDrive(1.35, -0.2, 0, 0);
                        } else if (robotAction == 6) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 7) {
                            moveSlidesForTime(0.5, -0.50);
                        } else if (robotAction == 8) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 9) {
                            toggleSmallClaw();
                        } else if (robotAction == 10) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 11) {
                            moveSlidesForTime(0.05, 0.5);
                        } else if (robotAction == 12) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 13) {
                            toggleSmallClaw();
                        } else if (robotAction == 14) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 15) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 16) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 17) {
                            mecanumDrive(1, -0.75, 0, 0);
                        } /*else if (robotAction == 18) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 19) {
                            mecanumDrive(1.175, 0, 0.50, -0.5);
                        } else if (robotAction == 20) {
                            waitThenGoToNextAction(0.0);
                        } else if (robotAction == 21) {
                            mecanumDrive(1.25, 0.0, 0.5, 0);
                        } else if (robotAction == 22) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 23) {
                            moveSlidesForTime(0.5, -0.5);
                        } else if (robotAction == 24) {
                            armToggle();
                        } else if (robotAction == 25) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 26) {
                            mecanumDrive(2.5, 0.75, 0, 0);
                        } else if (robotAction == 27) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 28) {
                            toggleSmallClaw();
                        }*/
                    }
                }
            } else if (fieldSide == 1 || position.equals("right")){
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 1) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 2) {
                    mecanumDrive(0.2, 1, 0, 0);
                } else if (robotAction == 3) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 4) {
                    mecanumDrive(.85, .5, 0, 0);
                } else if (robotAction == 5) {
                    waitThenGoToNextAction(0.35);
                } else if (robotAction == 6) {
                    mecanumDrive(0.475, 0, 0, 1);
                } else if (robotAction == 7) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 8) {
                    mecanumDrive(1.5, .2, 0, 0);
                } else if (robotAction == 9) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 10) {
                    mecanumDrive(1.00, -0.2, 0, 0);
                } else if (robotAction == 11) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 12) {
                    moveSlidesForTime(0.5, -0.50);
                } else if (robotAction == 13) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 14) {
                    toggleSmallClaw();
                } else if (robotAction == 15) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 16) {
                    moveSlidesForTime(0.05, 0.5);
                } else if (robotAction == 17) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 18) {
                    toggleSmallClaw();
                } else if (robotAction == 19) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 20) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 21) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 22) {
                    mecanumDrive(0.5, -.2, 0, 0);
                } /*else if (robotAction == 23) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 24) {
                    mecanumDrive(1.5, 0, -0.50, 0);
                } else if (robotAction == 25) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 26) {
                    mecanumDrive(1.05, 0, 0, 1);
                } else if (robotAction == 27) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 28) {
                    moveSlidesForTime(0.5, -0.5);
                } else if (robotAction == 29) {
                    armToggle();
                } else if (robotAction == 30) {
                    mecanumDrive(2.75, 0.75, 0, 0);
                } else if (robotAction == 31) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 32) {
                    toggleSmallClaw();
                } */
            }
        }
        if (teamColor == 0) {
                    //Red prop on the Left side
                    if (colorProp != null) {
                        if (colorProp.equals("RedProp") && fieldSide == 0) {
                            if (position.equals("left")) {                                                       //Left spike mark
                                if (robotAction == 0) {
                                    moveSlidesForTime(0.5, 0.50);
                                } else if (robotAction == 1) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 2) {
                                    mecanumDrive(0.2, 1, 0, 0);
                                } else if (robotAction == 3) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 4) {
                                    mecanumDrive(1, .5, 0, 0);
                                } else if (robotAction == 5) {
                                    waitThenGoToNextAction(0.35);
                                } else if (robotAction == 6) {
                                    mecanumDrive(0.5, 0, 0, -1);
                                } else if (robotAction == 7) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 8) {
                                    mecanumDrive(1.5, .2, 0, 0);
                                } else if (robotAction == 9) {
                                    waitThenGoToNextAction(0.2);
                                }else if (robotAction == 10) {
                                    mecanumDrive(1, -0.2, 0, 0);
                                } else if (robotAction == 11) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 12) {
                                    moveSlidesForTime(0.5, -0.50);
                                } else if (robotAction == 13) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 14) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 15) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 16) {
                                    moveSlidesForTime(0.05, 0.5);
                                } else if (robotAction == 17) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 18) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 19) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 20) {
                                    moveSlidesForTime(0.5, 0.50);
                                } else if (robotAction == 21) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 22) {
                                    mecanumDrive(0.5, -.2, 0, 0);
                                } else if (robotAction == 23) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 24) {
                                    mecanumDrive(1.5, 0, -0.50, 0);
                                }/* else if (robotAction == 25) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 26) {
                                    mecanumDrive(1, 0, 0, 1);
                                } else if (robotAction == 27) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 28) {
                                    moveSlidesForTime(0.5, -0.5);
                                } else if (robotAction == 29) {
                                    armToggle();
                                } else if (robotAction == 30) {
                                    mecanumDrive(1, 0.75, 0, 0);
                                } else if (robotAction == 31) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 32) {
                                    waitThenGoToNextAction(0.2);
                                } else if(!aprilTagFound && robotAction == 33) {
                                    scanForAprilTag(apriltags.runTag(4), -1);
                                } else if (aprilTagFound && robotAction == 33) {
                                    armToggle();
                                } else if (aprilTagFound && robotAction == 34) {
                                    toggleOutsideClaw();
                                } else if (aprilTagFound && robotAction == 35) {
                                    tagXPos = findTagX(apriltags.getxLocation(4), apriltags.getYaw(4)) - 5;   //Find X position
                                    tagYPos = findTagY(apriltags.getyLocation(4), apriltags.getzLocation(4), apriltags.getPitch(4)) - 5.4;  //Find Y pos
                                    if(tagYPos > 0) {
                                        lineUpToTag(tagXPos, tagYPos);
                                    }
                                } else if (robotAction == 36) {
                                    toggleSmallClaw();
                                } else if (robotAction == 37) {
                                    mecanumDrive(0.75, -0.2, 0, 0);
                                } else if (robotAction == 38) {
                                    armToggle();
                                } else if (robotAction == 39) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 40) {
                                    mecanumDrive(1.2, 0, 0.75, 0);
                                } else if (robotAction == 41) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 42) {
                                    mecanumDrive(0.75, .5, 0, 0);
                                }  */
                            } else if (position.equals("middle")) {                                              //Middle spike mark
                        if(robotAction == 0) {
                            moveSlidesForTime(0.2, 0.50);
                        } else if (robotAction == 1) {
                            mecanumDrive(0.2, 0.50, 0, 0);
                        } else if (robotAction == 2) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 3) {
                            mecanumDrive(1.75, .50, 0, 0);
                        } else if (robotAction == 4) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 5) {
                            mecanumDrive(.6, -.2, 0, 0);
                        } else if (robotAction == 6) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 7) {
                            moveSlidesForTime(0.5, -0.50);
                        } else if (robotAction == 8) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 9) {
                            toggleSmallClaw();
                        } else if (robotAction == 10) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 11) {
                            moveSlidesForTime(0.05, 0.5);
                        } else if (robotAction == 12) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 13) {
                            toggleSmallClaw();
                        } else if (robotAction == 14) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 15) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 16) {
                            waitThenGoToNextAction(0.2);
                        }else if (robotAction == 17) {
                            mecanumDrive(1, -0.25, 0, 0);
                        } /*else if (robotAction == 18) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 19) {
                            mecanumDrive(1.25, 0, -0.50, .50);
                        } else if (robotAction == 20) {
                            mecanumDrive(1.15, 0, -.5, 0);
                        } else if (robotAction == 21) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 22) {
                            moveSlidesForTime(0.5, -0.5);
                        } else if (robotAction == 23) {
                            armToggle();
                        } else if (robotAction == 24) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 25) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 26) {
                            mecanumDrive(2.75, 0.75, 0, 0);
                        } else if (robotAction == 27) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 28) {
                            toggleSmallClaw();
                        } */
                            }
                        }
                    } else if (fieldSide == 0 && position.equals("right")) {
                        if (robotAction == 0) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 1) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 2) {
                            mecanumDrive(0.2, 1, 0, 0);
                        } else if (robotAction == 3) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 4) {
                            mecanumDrive(1, 0.5, 0, 0);
                        } else if (robotAction == 5) {
                            waitThenGoToNextAction(0.35);
                        } else if (robotAction == 6) {
                            mecanumDrive(0.5, 0, 0, 1);
                        } else if (robotAction == 7) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 8) {
                            mecanumDrive(1.00, .2, 0, 0);
                        } else if (robotAction == 9) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 10) {
                            mecanumDrive(0.5, -0.2, 0, 0);
                        } else if (robotAction == 11) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 12) {
                            moveSlidesForTime(0.5, -0.50);
                        } else if (robotAction == 13) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 14) {
                            toggleOutsideClaw();
                        } else if (robotAction == 15) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 16) {
                            moveSlidesForTime(0.05, 0.5);
                        } else if (robotAction == 17) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 18) {
                            toggleOutsideClaw();
                        } else if (robotAction == 19) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 20) {
                            moveSlidesForTime(0.5, 0.50);
                        } else if (robotAction == 21) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 22) {
                            mecanumDrive(0.85, -.2, 0, 0);
                        } else if (robotAction == 23) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 24) {
                            mecanumDrive(1.65, 0, 0.50, 0);
                        } /*else if (robotAction == 25) {
                            moveSlidesForTime(0.5, -0.5);
                        } else if (robotAction == 26) {
                            armToggle();
                        } else if (robotAction == 27) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 28) {
                            mecanumDrive(2.75, 0.75, 0, 0);
                        } else if (robotAction == 29) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 30) {
                            toggleOutsideClaw();
                        } */
                    }
                } else {
                    //Blue prop on the left side
                    if (colorProp != null) {
                        if (fieldSide == 0 && colorProp.equals("BlueProp")) {
                            if (position.equals("left")) {                                                      //Left spike mark
                                if (robotAction == 0) {
                                    moveSlidesForTime(0.5, 0.75);
                                } else if (robotAction == 1) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 2) {
                                    mecanumDrive(0.2, 1, 0, 0);
                                } else if (robotAction == 3) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 4) {
                                    mecanumDrive(.5, .75, 0.05, 0);
                                } else if (robotAction == 5) {
                                    waitThenGoToNextAction(0.35);
                                } else if (robotAction == 6) {
                                    mecanumDrive(0.5, 0, 0, -1.0);
                                } else if (robotAction == 7) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 8) {
                                    mecanumDrive(1.75, .2, 0, 0);
                                } else if (robotAction == 9) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 10) {
                                    mecanumDrive(0.9, -0.2, 0, 0);
                                } else if (robotAction == 11) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 12) {
                                    moveSlidesForTime(0.1, -0.50);
                                } else if (robotAction == 13) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 14) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 15) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 16) {
                                    moveSlidesForTime(0.2, 0.5);
                                } else if (robotAction == 17) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 18) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 19) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 20) {
                                    moveSlidesForTime(0.5, 0.50);
                                } else if (robotAction == 21) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 22) {
                                    mecanumDrive(0.75, -.2, 0, 0);
                                } else if (robotAction == 23) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 24) {
                                    mecanumDrive(1.75, 0, -0.50, 0);
                                } else if (robotAction == 25) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 26) {
                                    mecanumDrive(1.25, 0.5, 0, 0.125);
                                } else if (robotAction == 27) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 29) {
                                    waitThenGoToNextAction(0.2);
                                } else if(!aprilTagFound && robotAction == 30) {
                                    scanForAprilTag(apriltags.runTag(1), 1);
                                } else if (aprilTagFound && robotAction == 30) {
                                    armToggle();
                                } else if (aprilTagFound && robotAction == 31) {
                                    toggleOutsideClaw();
                                } else if (aprilTagFound && robotAction == 32) {
                                    tagXPos = findTagX(apriltags.getxLocation(1), apriltags.getYaw(1)) - 5;   //Find X position
                                    tagYPos = findTagY(apriltags.getyLocation(1), apriltags.getzLocation(1), apriltags.getPitch(1)) - 5.4;  //Find Y pos
                                    if(tagYPos > 0) {
                                        lineUpToTag(tagXPos, tagYPos);
                                    }
                                } else if (robotAction == 33) {
                                    toggleSmallClaw();
                                } else if (robotAction == 34) {
                                    mecanumDrive(0.75, -0.2, 0, 0);
                                } else if (robotAction == 35) {
                                    armToggle();
                                } else if (robotAction == 36) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 37) {
                                    mecanumDrive(1, 0, -0.75, 0);
                                } else if (robotAction == 38) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction ==39) {
                                    mecanumDrive(0.75, .5, 0, 0);
                                }
                            } else if (position.equals("middle")) {                                 //Middle spike mark
                        /*if (robotAction == 0) {
                            mecanumDrive(0.2, 1, 0, 0);
                        } else if (robotAction == 1) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 2) {
                            mecanumDrive(3.50, 0, -0.50, 0);
                        } else if (robotAction == 3) {
                            waitThenGoToNextAction(0.2);
                        } else if (robotAction == 4) {
                            toggleSmallClaw();
                        } */
                                if (robotAction == 0) {
                                    moveSlidesForTime(0.5, 0.75);
                                } else if (robotAction == 1) {
                                    mecanumDrive(0.2, 0.50, 0, 0);
                                } else if (robotAction == 2) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 3) {
                                    mecanumDrive(1.8675/*309*/, .5, 0, 0);
                                } else if (robotAction == 4) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 5) {
                                    mecanumDrive(1.75, -0.2, 0, 0);
                                } else if (robotAction == 6) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 7) {
                                    moveSlidesForTime(0.5, -0.50);
                                } else if (robotAction == 8) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 9) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 10) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 11) {
                                    moveSlidesForTime(0.05, 0.5);
                                } else if (robotAction == 12) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 13) {
                                    toggleOutsideClaw();
                                } else if (robotAction == 14) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 15) {
                                    moveSlidesForTime(0.5, 0.50);
                                } else if (robotAction == 16) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 17) {
                                    mecanumDrive(.85, -0.50, 0, 0);
                                } else if (robotAction == 18) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 19) {
                                    mecanumDrive(2.25, 0, -0.5, 0.075);
                                } else if (robotAction == 20) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 21) {
                                    mecanumDrive(1.5, 0, 0, -.5);
                                } else if (robotAction == 22) {
                                    waitThenGoToNextAction(0.2);
                                } else if(!aprilTagFound && robotAction == 23) {
                                    scanForAprilTag(apriltags.runTag(2), 1);
                                } else if (aprilTagFound && robotAction == 24) {
                                    armToggle();
                                } else if (aprilTagFound && robotAction == 25) {
                                    toggleOutsideClaw();
                                } else if (aprilTagFound && robotAction == 26) {
                                    tagXPos = findTagX(apriltags.getxLocation(2), apriltags.getYaw(2)) - 5;   //Find X position
                                    tagYPos = findTagY(apriltags.getyLocation(2), apriltags.getzLocation(2), apriltags.getPitch(2)) - 5.4;  //Find Y pos
                                    if(tagYPos > 0) {
                                        lineUpToTag(tagXPos, tagYPos);
                                    }
                                } else if (robotAction == 27) {
                                    toggleSmallClaw();
                                } else if (robotAction == 28) {
                                    mecanumDrive(0.75, -0.2, 0, 0);
                                } else if (robotAction == 29) {
                                    armToggle();
                                } else if (robotAction == 30) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 31) {
                                    mecanumDrive(1.2, 0, -0.75, 0);
                                } else if (robotAction == 32) {
                                    waitThenGoToNextAction(0.2);
                                } else if (robotAction == 33) {
                                    mecanumDrive(0.75, .5, 0, 0);
                                }
                            }
                        }
                    } else if(fieldSide == 0 || position.equals("right")) {
                /*if (robotAction == 0) {
                    mecanumDrive(0.2, 1, 0, 0);
                } else if (robotAction == 1) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 2) {
                    mecanumDrive(3.50, 0, -0.50, 0);
                } else if (robotAction == 3) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 4) {
                    toggleSmallClaw();
                } */
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 1) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 2) {
                    mecanumDrive(0.2, 1, 0, 0);
                } else if (robotAction == 3) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 4) {
                    mecanumDrive(.4, 0.75, .1, 0);
                } else if (robotAction == 5) {
                    waitThenGoToNextAction(0.35);
                } else if (robotAction == 6) {
                    mecanumDrive(0.5, 0, 0, 1);
                } else if (robotAction == 7) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 8) {
                    mecanumDrive(1.25, .2, 0, 0);
                } else if (robotAction == 9) {
                    waitThenGoToNextAction(0.2);
                }else if (robotAction == 10) {
                    mecanumDrive(1.25, -0.2, 0, 0);
                } else if (robotAction == 11) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 12) {
                    moveSlidesForTime(0.5, -0.50);
                } else if (robotAction == 13) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 14) {
                    toggleOutsideClaw();
                } else if (robotAction == 15) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 16) {
                    moveSlidesForTime(0.05, 0.5);
                } else if (robotAction == 17) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 18) {
                    toggleOutsideClaw();
                } else if (robotAction == 19) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 20) {
                    moveSlidesForTime(0.5, 0.50);
                } else if (robotAction == 21) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 22) {
                    mecanumDrive(0.75, -.2, 0, 0);
                } else if (robotAction == 23) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 24) {
                    mecanumDrive(1.75, 0, 0.50, -0.075);
                } else if (robotAction == 25) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 26) {
                    mecanumDrive(1.1, 0, 0, 1);
                } else if (robotAction == 27) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 28) {
                    mecanumDrive(.75, 0.75, 0, 0);
                }else if (robotAction == 29) {
                    waitThenGoToNextAction(0.2);
                } else if(!aprilTagFound && robotAction == 30) {
                    scanForAprilTag(apriltags.runTag(3), 1);
                } else if (aprilTagFound && robotAction == 30) {
                    armToggle();
                } else if (aprilTagFound && robotAction == 31) {
                    toggleOutsideClaw();
                } else if (aprilTagFound && robotAction == 32) {
                    tagXPos = findTagX(apriltags.getxLocation(3), apriltags.getYaw(3)) - 5;   //Find X position
                    tagYPos = findTagY(apriltags.getyLocation(3), apriltags.getzLocation(3), apriltags.getPitch(3)) - 5.4;  //Find Y pos
                    if(tagYPos > 0) {
                        lineUpToTag(tagXPos, tagYPos);
                    }
                } else if (robotAction == 33) {
                    toggleSmallClaw();
                } else if (robotAction == 34) {
                    mecanumDrive(0.75, -0.2, 0, 0);
                } else if (robotAction == 35) {
                    armToggle();
                } else if (robotAction == 36) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 37) {
                    mecanumDrive(1.2, 0, 0.75, 0);
                } else if (robotAction == 38) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction ==39) {
                    mecanumDrive(0.75, .5, 0, 0);
                }
                    }
                }


        if(actionRuntime.time() >= 5.00) {
            robotAction++;
            actionRuntime.reset();
        }
                mecanumDrive.telemetryOutput();
                telemetry.addData("Current Action = ", robotAction);
                telemetry.addData("Current Side: ", position);
                telemetry.addData("Current Color: ", (teamColor == 0) ? "Red":"Blue");
                telemetry.addData("Elapsed Time: ", actionRuntime.time());
                telemetry.addData("Position: ", position);
                apriltags.telemetryAprilTagAll();
        }


    /**
     * This lets our code know that we want a motor power applied to motors in a specific way, and
     * this OpMode will keep track of the time.
     * @param secondsToRunFor is how long this OpMode needs to keep track of.
     * @param yAxisPower is how fast forward and back we want to go.
     * @param xAxisPower is how fast to the left or right we want to strafe.
     * @param rotation is how fast clockwise or counter-clockwise we want to turn.
     */
    public void mecanumDrive(double secondsToRunFor, double yAxisPower, double xAxisPower, double rotation) {
        mecanumDrive.slowMode(true); //Despite what it says, this will make the robot go at max speed
        yAxisPower = yAxisPower * -1;
        mecanumDrive.setPower(xAxisPower, yAxisPower, rotation);
        if(actionRuntime.time() >= secondsToRunFor) {
            robotAction++; //moves to next action
            actionRuntime.reset(); //resets timer
            mecanumDrive.setPower(0, 0, 0); //stops the motors
        }
    }

    /**
     * This is a custom-built wait() command. We have to have this, because OpModes do not like to
     * looping.
     * @param secondsToWait is our value to wait for,
     */
    public void waitThenGoToNextAction(double secondsToWait) {
        if(actionRuntime.time() >= secondsToWait) {
            robotAction++; //moves to next action
            actionRuntime.reset(); //resets timer
        }
    }

    /**
     * This opens and closes the inside claw with a single line of code.
     */
    public void toggleSmallClaw(){
        clawControl.openInsideClaw();
        robotAction++;
        actionRuntime.reset();
    }

    /**
     * This does the same as the above code, only for the outside claw.
     */
    public void toggleOutsideClaw(){
        clawControl.openOutsideClaw();
        robotAction++;
        actionRuntime.reset();
    }

    /**
     * This moves the claw arm up and down with a single line of code.
     */
    public void armToggle() {
        clawControl.toggleArm();
        robotAction++;
        actionRuntime.reset();
    }

    /**
     * This works like the mecanumDrive() command, only used for the linear slides.
     * @param time is how long we want to move the slides for.
     * @param power is how fast we want to move the slides.
     */
    public void moveSlidesForTime(double time, double power) {
        double slidePower = power * -1;
        linearSlide.setPower(slidePower);
        if(actionRuntime.time() >= time) {
            robotAction++;
            actionRuntime.reset();
            linearSlide.setPower(-0.05);
        }
    }


    /*
    One thing to note on the below commands. This is the aprilTag's Pose RELATIVE TO THE ROBOT, NOT
    THE OTHER WAY AROUND. Hence why some people may end up with the incorrect results. I had run
    headlong into that dead end when I first tried this.
     */
    /**
     * Using the aprilTag's Pose, we are able to make an educated guess about where the robot is on
     * the X axis in relation to that aprilTag, and where we want to end up (directly in front of
     * the aprilTag). The math can be found in my little leather notebook. If you want to see it, I
     * will try and add a link to a web page here:
     * If there is nothing there, ask the team, and I will gladly allow them to grab a hold of the
     * notebook.
     * @param x is the aprilTag's X position relative to the robot.
     * @param yaw is the aprilTag's yaw rotation relative to the robot.
     * @return is the number that is the result of all the juicy trigonometry involved.
     */
    public double findTagX(double x, double yaw) {
        //return ((x * Math.cos(Math.abs(yaw))) - 5);                                               //Formula that was made on 2/14/2024
        return (x * Math.cos(Math.toRadians(yaw)));
    }

    /**
     * Using the aprilTag's Pose, we are able to make an educated guess about the distance on the Y
     * axis from the BASE of the backdrop, with a constant that makes that guess about the Y
     * distance from where we want to end up. Once again, little leather notebook.
     * @param y is the aprilTag's Y position relative to the robot.
     * @param z is the aprilTag's Z position relative to the robot.
     * @param pitch is the aprilTag's pitch rotation relative to the robot.
     * @return is the number that results from the OTHER juicy trigonometric equation down below.
     */
    public double findTagY(double y, double z, double pitch) {
        //return ((3 * (y * Math.cos(Math.toDegrees(Math.asin(z / y))) + Math.abs(pitch))) / 4);    This was pretty off, but I typed it wrong
        return ((3 * (y * Math.cos(Math.asin(z / y) + Math.toRadians(pitch))) / 4) - 2);
    }

    /**
     * This takes tagX, and tagY and divides whichever is smaller by whichever is greater. This way
     * we end up with a ratio that is some number over 1, as motor power scales from -1 to 1.
     * @param tagX is the result of findTagX().
     * @param tagY is the result of findTagY().
     */
    public void lineUpToTag(double tagX, double tagY) {
        if(timesReset == 0){
            distance = Math.sqrt(Math.pow(tagX, 2) + Math.pow(tagY, 2));
            moveTime.reset();
            timesReset++;
        }
        time = distance * INCHTOTIME * 0.5;
        time = time + (time * 0.35) + 0.05;
        telemetry.addData("Time to move: ", time);
        telemetry.addData("Distance from board (inch): ", distance);
        if(tagX > tagY) {
            if(powerX < 0) {
                powerX = -1;
            } else if (powerX > 0) {
                powerX = 1;
            }
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
            mecanumDrive.setPower(.35 * powerX, -.25 * powerY, 0);
        } else if (moveTime.time() >= time) {
            mecanumDrive.setPower(0, 0, 0);
            robotAction++;
        }
    }

    /**
     * This moves the robot to the left or right (depending on what side it is on) searching for the
     * aprilTag that matches the position of the team prop. This will move towards the center in 5
     * short bursts, and if it is unable to find the aprilTag, then it will park. If the aprilTags
     * are found, the robot will calculate it's X and Y distance from where it wishes to end up,
     * allowing for the robot to accurately place a pixel on the backboard.
     * @param foundTag is the boolean value that keeps track of whether or not the aprilTag that the
     *                 robot is looking for is found.
     * @param direction is the positive or negative value that keeps track of the direction the
     *                  robot is to strafe in order to move to the center.
     */
    public void scanForAprilTag(boolean foundTag, float direction) {
        mecanumDrive.slowMode(true); //Despite what it says, this will make the robot go at max speed
        if(foundTag) {
            aprilTagFound = true;
            mecanumDrive.setPower(0, 0, 0); //stops the motors
        } else {
            if(!(moveTime.time() >= 0.75)) {
                mecanumDrive.setPower(0.5 * direction, 0, 0.01825 * multiplier);
            } else if (moveTime.time() >= 1.5) {
                moveTime.reset();
                reps++;
            } else {
                mecanumDrive.setPower(0, 0, 0);
            }

            if(reps > 4) {
                robotAction = 50;
                mecanumDrive.setPower(0, 0, 0);
            }
        }
    }
}
