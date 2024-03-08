package org.firstinspires.ftc.teamcode.init;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RR.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Autonomous(name = "roadRunner")
public class tfodRR extends LinearOpMode {
    //CONSTRUCT
    org.firstinspires.ftc.teamcode.other.tfSetup tfSetup = new tfSetup();
    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
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
    //DECLARE NULL
    TrajectorySequence LeftAuto;
    TrajectorySequence MiddleAuto;
    TrajectorySequence RightAuto;
    SampleMecanumDrive drive;

    @Override
    public void runOpMode() {
        mecanumDrive.init(this);
        linearSlide.init(this);
        clawControl.init(this);
        tfSetup.init(this);

        mecanumDrive.slowMode(true); //This allows the robot to go at max speed
        mecanumDrive.runWithoutEncoder();

        drive = new SampleMecanumDrive(hardwareMap);
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

                    LeftAuto = drive.trajectorySequenceBuilder(new Pose2d(12.16, -59.77, Math.toRadians(91.10)))
                            .splineTo(new Vector2d(48.27, -29.19), Math.toRadians(0.00))
                            .lineTo(new Vector2d(8.81, -34.14))
                            .splineTo(new Vector2d(60.21, -59.62), Math.toRadians(0.00))
                            .build();

                    MiddleAuto = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(48.00, -36.00), Math.toRadians(0.00))
                            .lineTo(new Vector2d(24.00, -24.00))
                            .splineTo(new Vector2d(62.00, -60.00), Math.toRadians(0.00))
                            .build();


                    RightAuto = drive.trajectorySequenceBuilder(new Pose2d(12.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(48.00, -42.00), Math.toRadians(0.00))
                            .lineTo(new Vector2d(31.00, -30.00))
                            .splineTo(new Vector2d(61.00, -60.00), Math.toRadians(0.00))
                            .build();

                } else if (leftSide && teamColor.equals("blue")) {

                    LeftAuto = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(-90.00)))
                            .splineTo(new Vector2d(48.00, 42.00), Math.toRadians(0.00))
                            .lineTo(new Vector2d(9.00, 35.00))
                            .splineTo(new Vector2d(62.00, 60.00), Math.toRadians(0.00))
                            .build();

                    MiddleAuto = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(270.00)))
                            .splineTo(new Vector2d(48.00, 36.00), Math.toRadians(360.00))
                            .lineTo(new Vector2d(24.00, 24.00))
                            .splineTo(new Vector2d(62.00, 60.00), Math.toRadians(360.00))
                            .build();

                    RightAuto = drive.trajectorySequenceBuilder(new Pose2d(12.00, 63.00, Math.toRadians(-90.00)))
                            .splineTo(new Vector2d(48.00, 30.00), Math.toRadians(0.00))
                            .lineTo(new Vector2d(31.00, 35.00))
                            .splineTo(new Vector2d(62.00, 60.00), Math.toRadians(0.00))
                            .build();

                } else if (leftSide && teamColor.equals("red")) {

                    LeftAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, -63.00, Math.toRadians(90.00)))
                            .UNSTABLE_addTemporalMarkerOffset(0.00,() -> {})
                            .UNSTABLE_addTemporalMarkerOffset(0.00,() -> {})
                            .lineTo(new Vector2d(-47.00, -42.00))
                            .splineTo(new Vector2d(-36.00, -12.00), Math.toRadians(90.00))
                            .lineTo(new Vector2d(26.00, -11.00))
                            .lineTo(new Vector2d(48.00, -30.00))
                            .build();

                    MiddleAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, -63.00, Math.toRadians(90.00)))
                            .lineTo(new Vector2d(-51.00, -39.00))
                            .lineToConstantHeading(new Vector2d(-47.39, -24.24))
                            .splineToConstantHeading(new Vector2d(-8.95, -9.25), Math.toRadians(0.00))
                            .lineTo(new Vector2d(26.00, -11.00))
                            .lineTo(new Vector2d(48.00, -36.00))
                            .build();

                    RightAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, -63.00, Math.toRadians(90.00)))
                            .splineTo(new Vector2d(-32.00, -35.00), Math.toRadians(0.00))
                            .lineTo(new Vector2d(-53.00, -13.00))
                            .lineTo(new Vector2d(26.00, -11.00))
                            .splineToSplineHeading(new Pose2d(48.00, -42.00, Math.toRadians(0.00)), Math.toRadians(0.00))
                            .build();

                } else if (!leftSide && teamColor.equals("blue")) {

                    RightAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(270.00)))
                            .splineTo(new Vector2d(-32.00, 35.00), Math.toRadians(360.00))
                            .lineTo(new Vector2d(-53.00, 13.00))
                            .lineTo(new Vector2d(26.00, 11.00))
                            .splineToSplineHeading(new Pose2d(48.00, 42.00, Math.toRadians(360.00)), Math.toRadians(360.00))
                            .build();

                    MiddleAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(270.00)))
                            .lineTo(new Vector2d(-51.00, 39.00))
                            .lineToConstantHeading(new Vector2d(-47.39, 24.24))
                            .splineToConstantHeading(new Vector2d(-8.95, 9.25), Math.toRadians(360.00))
                            .lineTo(new Vector2d(26.00, 11.00))
                            .lineTo(new Vector2d(48.00, 36.00))
                            .build();

                    LeftAuto = drive.trajectorySequenceBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(270.00)))
                            .UNSTABLE_addTemporalMarkerOffset(0.00,() -> {})
                            .UNSTABLE_addTemporalMarkerOffset(0.00,() -> {})
                            .lineTo(new Vector2d(-47.00, 42.00))
                            .splineTo(new Vector2d(-36.00, 12.00), Math.toRadians(270.00))
                            .lineTo(new Vector2d(26.00, 11.00))
                            .lineTo(new Vector2d(48.00, 30.00))
                            .build();

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

            if(propPos.equals("right")) {
                drive.followTrajectorySequence(RightAuto);
            }

            if(propPos.equals("middle")) {
                drive.followTrajectorySequence(MiddleAuto);
            }

            if(propPos.equals("left")) {
                drive.followTrajectorySequence(LeftAuto);
            }
        }
    }
}
