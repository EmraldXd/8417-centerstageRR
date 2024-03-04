package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.droneSlingshot;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;

@Autonomous
public class otherAuto extends OpMode {

    public ElapsedTime autoRuntime = new ElapsedTime(); //How long autonomous has been doin' its thing for
    ElapsedTime actionRuntime = new ElapsedTime();
    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    org.firstinspires.ftc.teamcode.action.droneSlingshot droneSlingshot = new droneSlingshot();
    //Our custom stuff to use here
    int robotAction = 0;
    @Override
    public void init() {
        mecanumDrive.init(this);
        mecanumDrive.runWithoutEncoder();
        droneSlingshot.init(this);
    }

    public void start() {
        mecanumDrive.runWithoutEncoder(); //Will set the robot to run without encoders
        autoRuntime.reset();
        actionRuntime.reset();
    }

    @Override
    public void loop() {
        if(robotAction == 0) {
            mecanumDrive(0.2, 0.0, 1.0, 0.0);
        } else if(robotAction == 1) {
            waitThenGoToNextAction(0.5);
        } else if (robotAction == 2) {
            mecanumDrive(1.2, 1, 0, 0);

        }
    }

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

    public void waitThenGoToNextAction(double secondsToWait) {
        if (actionRuntime.time() >= secondsToWait) {
            robotAction++; //moves to next action
            actionRuntime.reset(); //resets timer
        }
    }
}
