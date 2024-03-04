package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.mecanumDrive;

@Autonomous
public class timeTest extends OpMode {

    org.firstinspires.ftc.teamcode.action.mecanumDrive mecanumDrive = new mecanumDrive();
    ElapsedTime moveTime = new ElapsedTime();

    @Override
    public void init() {
        mecanumDrive.init(this);
        mecanumDrive.runWithoutEncoder();
    }

    public void init_loop() {
        moveTime.reset();
    }
    @Override
    public void loop() {
        if(moveTime.time() <= 1.00) {
            mecanumDrive.setPower(0, -0.75, 0);
        } else {
            mecanumDrive.setPower(0, 0, 0);
        }

        telemetry.addData("moveTime: ", moveTime.time());
    }
}
