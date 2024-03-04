package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.clawControl;
import org.firstinspires.ftc.teamcode.action.linearSlide;

@Autonomous
public class autoTest extends OpMode {
    org.firstinspires.ftc.teamcode.action.clawControl clawControl = new clawControl();
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();
    ElapsedTime timer = new ElapsedTime();
    ElapsedTime actionTime = new ElapsedTime();
    int robotAction = 0;

    @Override
    public void init() {
        clawControl.init(this);
        linearSlide.init(this);
        toggleSmallClaw();
        robotAction = 0;
    }

    @Override
    public void loop() {
        if(robotAction == 0) {
            toggleSmallClaw();
        }
        if (robotAction == 1 && timer.time() > 0.50){
            waitThenGoToNextAction(0.50);
        } else if (robotAction == 2 && timer.time() > 0.50) {
            moveSlidesForTime(0.50, 0.165);
        } else if (robotAction == 3 && timer.time() > 0.50){
            waitThenGoToNextAction(0.50);
        } else if (robotAction == 4 && timer.time() > 0.50) {
            toggleSmallClaw();
        } else if (robotAction == 5 && timer.time() > 0.50){
            waitThenGoToNextAction(0.50);
        } else if (robotAction == 6 && timer.time() > 0.50) {
            moveSlidesForTime(1.00, 0.50);
        } else if (robotAction == 7 && timer.time() > 0.50) {
            waitThenGoToNextAction(0.50);
        } else if (robotAction == 8 && timer.time() > 0.50) {
            armToggle();
        }
    }

    public void moveSlidesForTime(double time, double power) {
        double slidePower = power * -1;
        linearSlide.setPower(slidePower);
        if(actionTime.time() >= time) {
            robotAction++;
            actionTime.reset();
            timer.reset();
            linearSlide.setPower(0);
        }
    }

    public void toggleSmallClaw(){
        clawControl.openInsideClaw();
        robotAction++;
        timer.reset();
        actionTime.reset();
    }

    public void armToggle() {
        clawControl.toggleArm();
        robotAction++;
        timer.reset();
        actionTime.reset();
    }

    public void waitThenGoToNextAction(double secondsToWait) {
        if (actionTime.time() >= secondsToWait) {
            robotAction++; //moves to next action
            actionTime.reset(); //resets timer
        }
    }
}
