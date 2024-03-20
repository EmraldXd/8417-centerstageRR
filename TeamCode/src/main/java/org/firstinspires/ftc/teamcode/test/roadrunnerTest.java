package org.firstinspires.ftc.teamcode.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RR.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RR.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.other.tfSetup;

@Autonomous
public class roadrunnerTest extends LinearOpMode{
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
    //DECLARE NULL
    TrajectorySequence LeftAuto;
    TrajectorySequence MiddleAuto;
    TrajectorySequence RightAuto;
    SampleMecanumDrive drive;

    @Override
    public void runOpMode() {
        linearSlide.init(this);
        clawControl.init(this);
        tfSetup.init(this);

        drive = new SampleMecanumDrive(hardwareMap);

        while(opModeInInit()) {
                    RightAuto = drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270.00)))
                            .addTemporalMarker(2, () -> linearSlide.startRightSlideUp())
                            .addTemporalMarker(2.5, () -> linearSlide.stopSlideRight())
                            .addTemporalMarker(2, () -> linearSlide.startLeftSlideUp())
                            .addTemporalMarker(2.5, () -> linearSlide.stopSlideLeft())
                            .build();
                    drive.setPoseEstimate(RightAuto.start());
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

            drive.followTrajectorySequence(RightAuto);
        }
    }
}
