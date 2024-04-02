package org.firstinspires.ftc.teamcode.init;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RR.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Autonomous (name = "roadRunnerAuto")
public class tfodLoopRR extends OpMode {
    //CONSTRUCT
    org.firstinspires.ftc.teamcode.other.tfSetup tfSetup = new tfSetup();
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();
    org.firstinspires.ftc.teamcode.action.clawControl clawControl = new clawControl();
    ElapsedTime actionRuntime = new ElapsedTime();
    ElapsedTime autoRuntime = new ElapsedTime();
    //DECLARE CUSTOM
    public String teamColor = "red"; //Stores the color alliance we are on as a string variable. Defaults to red.
    public String propPos = "right"; //Stores the robot position for the robot's auto as a string variable. Defaults to right unless told otherwise.

    public String name = "redProp"; //Stores the prop name as a string variable. Just like teamColor, defaults to red.
    boolean leftSide = false; //Stores the location of the prop as a string variable. Defaults to right unless told otherwise.
    boolean teamChosen = false; //Lets the robot know if the team is chosen
    boolean autoFinished = false;
    public int robotAction = 0;
    boolean backdropSide = false;
    //DECLARE NULL
    TrajectorySequence LeftAutoOne;
    TrajectorySequence LeftAutoTwo;
    TrajectorySequence LeftAutoThree;
    TrajectorySequence MiddleAutoOne;
    TrajectorySequence MiddleAutoTwo;
    TrajectorySequence MiddleAutoThree;
    TrajectorySequence RightAutoOne;
    TrajectorySequence RightAutoTwo;
    TrajectorySequence RightAutoThree;
    SampleMecanumDrive drive;

    @Override
    public void init() {
        linearSlide.init(this);
        clawControl.init(this);
        tfSetup.init(this);
        drive = new SampleMecanumDrive(hardwareMap);
    }

    public void init_loop() {
        if (gamepad1.b) {
            teamColor = "red";
        } else if (gamepad1.x) {
            teamColor = "blue";
        } else if (gamepad1.y) {
            teamChosen = true;
        }

        //Player 1 sets the robot side using the right and left bumpers respectively
        if (gamepad1.right_bumper) {
            leftSide = false;
        } else if (gamepad1.left_bumper) {
            leftSide = true;
        }

        if(robotAction == 0) {
            moveSlidesForTime(0.7, 0.75);
        }

        if (teamChosen) {
            if (!leftSide && teamColor.equals("red")) {

                //These autonomous paths ARE FINALIZED
                LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, -35.00), Math.toRadians(0.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineToLinearHeading(new Pose2d(9.00, -40.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build();

                LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(7.50, -40.00, Math.toRadians(180.00)))
                        .lineToSplineHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                        .lineTo(new Vector2d(61.00, -60.00))
                        .build();


                MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, -40.00), Math.toRadians(0.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineTo(new Vector2d(45.00, -40.00))
                        .lineToLinearHeading(new Pose2d(23.00, -25.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build();

                MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(23.00, -25.00, Math.toRadians(180.00)))
                        .lineTo(new Vector2d(28, -25))
                        .lineToLinearHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                        .splineTo(new Vector2d(61.00, -60.00), Math.toRadians(0.00))
                        .build();


                RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, -50.00), Math.toRadians(0.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineToSplineHeading(new Pose2d(33.00, -36.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build();

                RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(31.00, -36.00, Math.toRadians(180.00)))
                        .lineTo(new Vector2d(37.00, -36.00))
                        .lineToSplineHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                        .lineTo(new Vector2d(61.00, -60.00))
                        .build();

                drive.setPoseEstimate(LeftAutoOne.start());

            } else if (leftSide && teamColor.equals("blue")) {

                //These auto programs ARE FINALIZED
                LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, 42.50), Math.toRadians(360.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineToSplineHeading(new Pose2d(31.00, 30.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(31.00, 30.00, Math.toRadians(180.00)))
                        .lineToSplineHeading(new Pose2d(36.00, 60.00, Math.toRadians(360)))
                        .lineTo(new Vector2d(61.00, 60.00))
                        .build();


                MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, 38.50), Math.toRadians(360.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineTo(new Vector2d(45.00, 38.50))
                        .lineToLinearHeading(new Pose2d(22.00, 20.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build();

                MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(22.00, 20.00, Math.toRadians(180.00)))
                        .addDisplacementMarker(() -> clawControl.openInsideClaw())
                        .lineToLinearHeading(new Pose2d(36.00, 55.00, Math.toRadians(360.00)))
                        .splineTo(new Vector2d(61.00, 60.00), Math.toRadians(360.00))
                        .build();


                RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .splineTo(new Vector2d(48.00, 33.50), Math.toRadians(360.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .lineToLinearHeading(new Pose2d(7.50, 40.00, Math.toRadians(181.00)))
                        .addDisplacementMarker(this::trajectoryFinished)
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build();

                RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(23.00, 30.00, Math.toRadians(180.00)))
                        .lineToLinearHeading(new Pose2d(36.00, 57.50, Math.toRadians(360.00)))
                        .splineTo(new Vector2d(61.00, 57.50), Math.toRadians(360.00))
                        .build();

                drive.setPoseEstimate(LeftAutoOne.start());

            } else if (leftSide && teamColor.equals("red")) {

                //This auto code is NOT FINALIZED
                LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(-47.00, -42.00), Math.toRadians(90.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .splineTo(new Vector2d(-35.00, -63.00), Math.toRadians(-90.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*

                LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(11.00, 13.00, Math.toRadians(90.00)))
                        .lineToSplineHeading(new Pose2d(48.00, -30.00, Math.toRadians(2.44)))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                LeftAutoThree = drive.trajectorySequenceBuilder(new Pose2d(48.00, -30.00, Math.toRadians(2.44)))
                        .lineTo(new Vector2d(43.00, -13.00))
                        .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.37))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                */


                MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(-47.00, -25.00), Math.toRadians(0.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .splineTo(new Vector2d(-35.00, -63.00), Math.toRadians(-90.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*

                MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                        .lineToSplineHeading(new Pose2d(48.00, -36.00, Math.toRadians(0.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                MiddleAutoThree = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(0.00)))
                        .lineTo(new Vector2d(43.00, -13.00))
                        .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                */


                RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                        .splineTo(new Vector2d(-32.00, -30.00), Math.toRadians(0.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .splineTo(new Vector2d(-35.00, -60.00), Math.toRadians(-90.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*

                RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                        .lineToSplineHeading(new Pose2d(48.00, -36.00, Math.toRadians(0.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                RightAutoThree = drive.trajectorySequenceBuilder(new Pose2d(48.00, -36.00, Math.toRadians(0.00)))
                        .lineTo(new Vector2d(43.00, -13.00))
                        .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                */

                drive.setPoseEstimate(LeftAutoOne.start());

            } else if (!leftSide && teamColor.equals("blue")) {

                //This auto code IS FINALIZED
                LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                        .splineTo(new Vector2d(-32.00, 36.00), Math.toRadians(360.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .splineTo(new Vector2d(-35.00, 63.00), Math.toRadians(-270.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*
                LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(-53.00, 13.00, Math.toRadians(270.00)))
                        .lineToSplineHeading(new Pose2d(48.00, 43.00, Math.toRadians(360.00)))
                        .build();

                LeftAutoThree = drive.trajectorySequenceBuilder(new Pose2d(48.00, 43.00, Math.toRadians(360.00)))
                        .lineTo(new Vector2d(43.00, 13.00))
                        .splineTo(new Vector2d(61.00, 13.00), Math.toRadians(360.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();  */

                MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                        .splineTo(new Vector2d(-47.00, 25.00), Math.toRadians(360.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .splineTo(new Vector2d(-35.00, 63.00), Math.toRadians(-270.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*
                MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(53.00, 13.00, Math.toRadians(270.00)))
                        .lineToSplineHeading(new Pose2d(48.00, 36.00, Math.toRadians(360.00)))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                MiddleAutoThree = drive.trajectorySequenceBuilder(new Pose2d(48.00, 36.00, Math.toRadians(360.00)))
                        .lineTo(new Vector2d(43.00, 13.00))
                        .splineTo(new Vector2d(61.00, 13.00), Math.toRadians(360.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();  */

                RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                        .splineTo(new Vector2d(-47.00, 42.00), Math.toRadians(270.00))
                        .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                        .setReversed(true)
                        .lineTo(new Vector2d(-35.00, 63.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .build();

                /*
                RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(-36.00, 13.00, Math.toRadians(270.00)))
                        .addDisplacementMarker(() -> clawControl.toggleArm())
                        .lineTo(new Vector2d(-12.00, 13.00))
                        .lineTo(new Vector2d(11.00, 13.00))
                        .lineToSplineHeading(new Pose2d(48.00, 30.00, Math.toRadians(357.56)))
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .addDisplacementMarker(this::trajectoryFinished)
                        .build();

                RightAutoThree = drive.trajectorySequenceBuilder(new Pose2d(48.00, 30.00, Math.toRadians(357.56)))
                        .lineTo(new Vector2d(43.00, 13.00))
                        .lineTo(new Vector2d(61.00, 13.00))
                        .addDisplacementMarker(this::addRobotAction)
                        .addDisplacementMarker(() -> actionRuntime.reset())
                        .build(); */

                drive.setPoseEstimate(LeftAutoOne.start());

            }
            if (teamColor.equals("red")) {
                name = tfSetup.runTfodLabel("redProp");
            } else if (teamColor.equals("blue")) {
                name = tfSetup.runTfodLabel("blueProp");
            }
            if (name != null) {
                propPos = tfSetup.runTfodSide();
            }

            backdropSide = (teamColor.equals("red") && !leftSide) || (teamColor.equals("blue") && leftSide);

        }
        //TELEMETRY
        telemetry.addData("Current team: ", teamColor);
        telemetry.addData("Team locked in? ", teamChosen);
        telemetry.addData("Team prop label: ", name);
        telemetry.addData("Team prop position: ", propPos);
        telemetry.addData("Team side: ", leftSide ? "Left" : "right");
    }

    public void start() {
        actionRuntime.reset();
        autoRuntime.reset();
        robotAction = 0;
    }

    @Override
    public void loop() {
        if (backdropSide) {
            if (propPos.equals("right") || !(propPos.equals("middle") || propPos.equals("left"))) {
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.75);
                } else if (robotAction == 1) {
                    drive.followTrajectorySequence(RightAutoOne);
                    robotAction++;
                    clawControl.toggleArm();
                } else if (robotAction == 2) {
                    actionRuntime.reset();
                } else if (autoFinished && robotAction == 3) {
                    moveSlidesForTime(1, -0.75);
                } else if (robotAction == 4) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 5) {
                    clawControl.openInsideClaw();
                    drive.followTrajectorySequence(RightAutoTwo);
                    robotAction++;
                }
            }

            if (propPos.equals("middle")) {
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.75);
                } else if (robotAction == 1) {
                    drive.followTrajectorySequence(MiddleAutoOne);
                    robotAction++;
                    clawControl.toggleArm();
                } else if (robotAction == 2) {
                    actionRuntime.reset();
                } else if (autoFinished && robotAction == 3) {
                    moveSlidesForTime(1, -0.75);
                } else if (robotAction == 4) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 5) {
                    clawControl.openInsideClaw();
                    drive.followTrajectorySequence(MiddleAutoTwo);
                    robotAction++;
                }
            }

            if (propPos.equals("left")) {
                if (robotAction == 0) {
                    moveSlidesForTime(0.5, 0.75);
                } else if (robotAction == 1) {
                    drive.followTrajectorySequence(LeftAutoOne);
                    clawControl.toggleArm();
                    robotAction++;
                } else if (robotAction == 2) {
                    actionRuntime.reset();
                } else if (autoFinished && robotAction == 3) {
                    moveSlidesForTime(1, -0.75);
                } else if (robotAction == 4) {
                    waitThenGoToNextAction(0.2);
                } else if (robotAction == 5) {
                    clawControl.openInsideClaw();
                    drive.followTrajectorySequence(LeftAutoTwo);
                    robotAction++;
                }
            }
        }
        linearSlide.telemetryOutput();
        telemetry.addData("Runtime elapsed: ", autoRuntime.time());
        telemetry.addData("Robot action: ", robotAction);

        if (!backdropSide) {
            if (propPos.equals("right") || !(propPos.equals("middle") || propPos.equals("left"))) {
                if(robotAction == 0) {
                    drive.followTrajectorySequence(RightAutoOne);
                }
            }

            if (propPos.equals("middle")) {
                if(robotAction == 0) {
                    drive.followTrajectorySequence(MiddleAutoOne);
                }
            }

            if (propPos.equals("left")) {
                if (robotAction == 0) {
                    drive.followTrajectorySequence(LeftAutoOne);
                }
            }
        }
    }

    public void addRobotAction() {
        robotAction++;
    }

    public void moveSlidesForTime(double time, double power) {
        double slidePower = power * -1;
        linearSlide.setPower(slidePower);
        if (actionRuntime.time() >= time) {
            robotAction++;
            actionRuntime.reset();
            linearSlide.setPower(-0.05);
        }
    }

    public void waitThenGoToNextAction(double secondsToWait) {
        if(actionRuntime.time() >= secondsToWait) {
            robotAction++; //moves to next action
            actionRuntime.reset(); //resets timer
        }
    }

    public void trajectoryFinished() {
        autoFinished = true;
    }
}
