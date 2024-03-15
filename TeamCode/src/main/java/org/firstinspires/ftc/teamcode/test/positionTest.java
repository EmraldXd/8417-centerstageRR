package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.action.linearSlide;

@Autonomous
public class positionTest extends LinearOpMode {
    org.firstinspires.ftc.teamcode.action.linearSlide linearSlide = new linearSlide();

    @Override
    public void runOpMode(){
        while(opModeInInit()) {
            linearSlide.init(this);
        }
        while(opModeIsActive()) {
            //linearSlide.slideTo(1000);

            linearSlide.telemetryOutput();
            telemetry.update();
        }
    }
}
