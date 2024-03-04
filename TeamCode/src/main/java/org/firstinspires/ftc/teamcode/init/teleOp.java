package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.droneLauncher;
import org.firstinspires.ftc.teamcode.action.droneSlingshot;
import org.firstinspires.ftc.teamcode.action.linearSlide;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.action.winchControl;

@TeleOp(name = "Mechanum Drive", group = "Main")
public class teleOp extends OpMode {

    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();
    org.firstinspires.ftc.teamcode.action.droneLauncher droneLauncher = new droneLauncher();
    org.firstinspires.ftc.teamcode.action.droneSlingshot droneSlingshot = new droneSlingshot();
    org.firstinspires.ftc.teamcode.action.clawControl clawControl = new clawControl();
    org.firstinspires.ftc.teamcode.action.winchControl winchControl = new winchControl();

    @Override
    public void init() {
        //Initialized mecanumdrive so we can actually move :)
        mecanumDrive.init(this);
        //Initialized linearSlide so we can move the slides up and down.
        linearSlide.init(this);
        //Initialized drone launcher
        //droneLauncher.init(this);
        //Initialized drone slingshot
        droneSlingshot.init(this);
        //Initialized claw control
        clawControl.init(this);
        //Initialized winch control
        winchControl.init(this);
    }

    public void start() {
        mecanumDrive.runWithoutEncoder(); //Will set the robot to run without encoders
    }

    @Override
    public void loop() {
        //Let the driving fun begin :)
        mecanumDrive.slowMode(gamepad1.left_bumper);
        mecanumDrive.setPower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        mecanumDrive.telemetryOutput();
        /* telemetry.addData("X input: ", gamepad1.left_stick_x);
        telemetry.addData("Y input: ", gamepad1.left_stick_y);
        telemetry.addData("Rotation: ", gamepad1.right_stick_x); */
        //This is where the linear slide does stuff
        linearSlide.setPower(gamepad2.left_stick_y);
        if(gamepad2.left_stick_y == 0) {
            linearSlide.setPower(-0.05);
        }
        linearSlide.telemetryOutput();
        /* This is where the drone launcher launches
        droneLauncher.launch(gamepad2.right_trigger);
        droneLauncher.telemetry();
        */
        //This is where the drone slingshot code is
        droneSlingshot.slingshot(gamepad2.b, gamepad1.a);
        droneSlingshot.telemetry();
        //This is claw stuff
        clawControl.openClaw(gamepad2.right_bumper, gamepad2.left_bumper);
        clawControl.moveArm(gamepad2.dpad_up, gamepad2.dpad_down);
        clawControl.telemetry();
        //This is winch stuff
       winchControl.lockSet(gamepad1.right_bumper);
       winchControl.telemetry();
       winchControl.windWinch(gamepad1.b);
    }
}
