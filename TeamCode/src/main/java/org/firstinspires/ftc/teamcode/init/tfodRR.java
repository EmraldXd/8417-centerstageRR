package org.firstinspires.ftc.teamcode.init;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RR.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Disabled
@Autonomous(name = "roadRunner")
public class tfodRR extends LinearOpMode {
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
    //DECLARE NULL
    TrajectorySequence LeftAutoOne;
    TrajectorySequence LeftAutoTwo;
    TrajectorySequence MiddleAutoOne;
    TrajectorySequence MiddleAutoTwo;
    TrajectorySequence RightAutoOne;
    TrajectorySequence RightAutoTwo;
    SampleMecanumDrive drive;

    @Override
    public void runOpMode() {
        if (opModeInInit()){
            linearSlide.init(this);
            clawControl.init(this);
            tfSetup.init(this);
            drive = new SampleMecanumDrive(hardwareMap);
        }
        while(opModeInInit()) {
            //Player 1 sets teamColor using X and B (the blue and red button respectively)
            if(gamepad1.b) {
                teamColor = "red";
            } else if (gamepad1.x) {
                teamColor = "blue";
            } else if (gamepad1.y) {
                teamChosen = true;
            }

            //Player 1 sets the robot side using the right and left bumpers respectively
            if(gamepad1.right_bumper) {
                leftSide = false;
            } else if (gamepad1.left_bumper) {
                leftSide = true;
            }

            if(teamChosen) {
                if (!leftSide && teamColor.equals("red")) {

                    LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, -30.00), Math.toRadians(0.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToLinearHeading(new Pose2d(9.00, -35.00, Math.toRadians(180.00)))
                            .build();

                    LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(9.00, -35.00, Math.toRadians(180.00)))
                            .lineToSplineHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                            .splineTo(new Vector2d(61.00, -60.00), Math.toRadians(0.00))
                            .build();


                    MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, -36.00), Math.toRadians(0.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToLinearHeading(new Pose2d(34.5, -30.5, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineToLinearHeading(new Pose2d(23.00, -25.00, Math.toRadians(180.00)))
                            .build();

                    MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(23.00, -25.00, Math.toRadians(180.00)))
                            .lineToLinearHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                            .splineTo(new Vector2d(61.00, -60.00), Math.toRadians(0.00))
                            .build();


                    RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, -43.00), Math.toRadians(0.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(31.00, -36.00, Math.toRadians(180.00)))
                            .build();

                    RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(31.00, -36.00, Math.toRadians(180.00)))
                            .addDisplacementMarker(0.45, 0, () -> clawControl.toggleArm())
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .lineToSplineHeading(new Pose2d(36.00, -60.00, Math.toRadians(0.00)))
                            .splineTo(new Vector2d(61.00, -60.00), Math.toRadians(0.00))
                            .build();

                    drive.setPoseEstimate(LeftAutoOne.start());

                } else if (leftSide && teamColor.equals("blue")) {

                    LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, 43.00), Math.toRadians(360.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(31.00, 36.00, Math.toRadians(180.00)))
                            .build();

                    LeftAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(31.00, 36.00, Math.toRadians(180.00)))
                            .lineToSplineHeading(new Pose2d(36.00, 60.00, Math.toRadians(360)))
                            .lineTo(new Vector2d(61.00, 60.00))
                            .build();


                    MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, 36.00), Math.toRadians(360.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToLinearHeading(new Pose2d(35.5, 30.5, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineToLinearHeading(new Pose2d(23.00, 25.00, Math.toRadians(180.00)))
                            .build();

                    MiddleAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(180.00)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .lineToLinearHeading(new Pose2d(36.00, 60.00, Math.toRadians(360.00)))
                            .splineTo(new Vector2d(61.00, 60.00), Math.toRadians(360.00))
                            .build();


                    RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .splineTo(new Vector2d(48.00, 36.00), Math.toRadians(360.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToLinearHeading(new Pose2d(23.00, 30.00, Math.toRadians(180.00)))
                            .build();

                    RightAutoTwo = drive.trajectorySequenceBuilder(new Pose2d(23.00, 30.00, Math.toRadians(180.00)))
                            .lineToLinearHeading(new Pose2d(36.00, 60.00, Math.toRadians(360.00)))
                            .splineTo(new Vector2d(61.00, 60.00), Math.toRadians(360.00))
                            .build();

                    drive.setPoseEstimate(LeftAutoOne.start());

                } else if (leftSide && teamColor.equals("red")) {

                    LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(-47.00, -42.00), Math.toRadians(90.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineTo(new Vector2d(-36.00, -42.00))
                            .splineTo(new Vector2d(-36.00, -13.00), Math.toRadians(90.00))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, -13.00))
                            .lineToSplineHeading(new Pose2d(48.00, -30.00, Math.toRadians(2.44)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(43.00, -13.00))
                            .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.37))
                            .build();

                    MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(-47.00, -25.00), Math.toRadians(0.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(-53.00, -13.00, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, -13.00))
                            .lineToSplineHeading(new Pose2d(48.00, -36.00, Math.toRadians(0.00)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .lineTo(new Vector2d(43.00, -13.00))
                            .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.00))
                            .build();

                    RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(-47.00, -25.00), Math.toRadians(0.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(-53.00, -13.00, Math.toRadians(90.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, -13.00))
                            .lineToSplineHeading(new Pose2d(48.00, -36.00, Math.toRadians(0.00)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(43.00, -13.00))
                            .splineTo(new Vector2d(61.00, -13.00), Math.toRadians(0.00))
                            .build();

                    drive.setPoseEstimate(LeftAutoOne.start());

                } else if (!leftSide && teamColor.equals("blue")) {

                    LeftAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                            .splineTo(new Vector2d(-32.00, 36.00), Math.toRadians(360.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(-53.00, 13.00, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, 13.00))
                            .lineToSplineHeading(new Pose2d(48.00, 43.00, Math.toRadians(360.00)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(43.00, 13.00))
                            .splineTo(new Vector2d(61.00, 13.00), Math.toRadians(360.00))
                            .build();

                    MiddleAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                            .splineTo(new Vector2d(-47.00, 25.00), Math.toRadians(360.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineToSplineHeading(new Pose2d(-53.00, 13.00, Math.toRadians(270.00)))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, 13.00))
                            .lineToSplineHeading(new Pose2d(48.00, 36.00, Math.toRadians(360.00)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(43.00, 13.00))
                            .splineTo(new Vector2d(61.00, 13.00), Math.toRadians(360.00))
                            .build();

                    RightAutoOne = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                            .splineTo(new Vector2d(-47.00, 42.00), Math.toRadians(270.00))
                            .addDisplacementMarker(() -> clawControl.openOutsideClaw())
                            .lineTo(new Vector2d(-36.00, 42.00))
                            .splineTo(new Vector2d(-36.00, 13.00), Math.toRadians(270.00))
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(11.00, 13.00))
                            .lineToSplineHeading(new Pose2d(48.00, 30.00, Math.toRadians(357.56)))
                            .addDisplacementMarker(() -> clawControl.openInsideClaw())
                            .addDisplacementMarker(() -> clawControl.toggleArm())
                            .lineTo(new Vector2d(43.00, 13.00))
                            .splineTo(new Vector2d(61.00, 13.00), Math.toRadians(359.63))
                            .build();

                    drive.setPoseEstimate(LeftAutoOne.start());

                }
                if(teamColor.equals("red")) {
                    name = tfSetup.runTfodLabel("redProp");
                } else if (teamColor.equals("blue")) {
                    name = tfSetup.runTfodLabel("blueProp");
                }
                if(name != null) {
                    propPos = tfSetup.runTfodSide();
                }
            }
            //TELEMETRY
            telemetry.addData("Current team: ", teamColor);
            telemetry.addData("Team locked in? ", teamChosen);
            telemetry.addData("Team prop label: ", name);
            telemetry.addData("Team prop position: ", propPos);
            telemetry.addData("Team side: ", leftSide ? "Left" : "right");
            telemetry.update();
        }
        //START
        actionRuntime.reset();
        autoRuntime.reset();
        linearSlide.resetEncoder();

        waitForStart();

        while(opModeIsActive() && !autoFinished) {
            //LOOP
            telemetry.addData("Auto time elapsed: ", autoRuntime.seconds());
            telemetry.addData("Action time elapsed: ", actionRuntime.seconds());
            linearSlide.telemetryOutput();

            if(propPos.equals("right") || (!propPos.equals("middle") && !propPos.equals("left")) && ((!leftSide && teamColor.equals("red")) || (leftSide && teamColor.equals("blue")))) {
                if(actionRuntime.time() <= 1.0 && robotAction == 0) {
                    linearSlide.setPower(-0.15);
                } else if (actionRuntime.time() >= 1.00 && robotAction == 0){
                    setRobotAction(0);
                    linearSlide.setPower(0);
                    drive.followTrajectorySequence(RightAutoOne);
                    clawControl.toggleArm();
                    actionRuntime.reset();
                }
                if(actionRuntime.time() <= 1.0 && robotAction == 1) {
                    linearSlide.setPower(0.15);
                } else if (actionRuntime.time() >= 0.75 && robotAction == 1) {
                    linearSlide.setPower(0);
                    clawControl.openInsideClaw();
                    drive.followTrajectorySequence(RightAutoTwo);
                }
            }

            if(propPos.equals("middle") && ((!leftSide && teamColor.equals("red")) || (leftSide && teamColor.equals("blue")))) {
                while(actionRuntime.time() <= 0.50) {
                    linearSlide.startRightSlideUp();
                    linearSlide.startLeftSlideUp();
                }
                linearSlide.stopSlideRight();
                linearSlide.stopSlideLeft();
                drive.followTrajectorySequence(MiddleAutoOne);
                clawControl.toggleArm();
                actionRuntime.reset();
                while(actionRuntime.time() <= 0.50) {
                    linearSlide.setPower(0.2);
                }
                linearSlide.setPower(0);
                drive.followTrajectorySequence(MiddleAutoTwo);
            }

            if(propPos.equals("left") && ((!leftSide && teamColor.equals("red")) || (leftSide && teamColor.equals("blue")))) {
                while(actionRuntime.time() <= 0.50) {
                    linearSlide.setPower(-0.2);
                }
                linearSlide.setPower(-0.06);
                drive.followTrajectorySequence(LeftAutoOne);
                clawControl.toggleArm();
                actionRuntime.reset();
                while(actionRuntime.time() <= 0.50) {
                    linearSlide.setPower(0.2);
                }
                linearSlide.setPower(-0.06);
                clawControl.openInsideClaw();
                drive.followTrajectorySequence(LeftAutoTwo);
            }
            telemetry.update();
        }
    }

    public void setRobotAction(int previousAction) {
        if(robotAction != previousAction) {
            robotAction++;
        }
    }

    public void moveSlidesForTime(double time, double power) {
        double slidePower = power * -1;
        linearSlide.setPower(slidePower);
        if(actionRuntime.time() >= time) {
            robotAction++;
            actionRuntime.reset();
            linearSlide.setPower(-0.05);
        }
    }
}
