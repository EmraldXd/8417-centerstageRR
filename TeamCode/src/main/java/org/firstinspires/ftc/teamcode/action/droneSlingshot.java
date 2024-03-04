package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class droneSlingshot {

    Servo servo;
    Telemetry telemetry;

    public void init(@NonNull OpMode opMode){
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        servo = hardwareMap.get(Servo.class, "James"); //We, in sleep depravity, named the servo James
        servo.setPosition(0.00);
    }

    public void start(){
    }

    public void slingshot(boolean bigRedButton, boolean player1Key)
    {
        if(bigRedButton && player1Key)
        {
            servo.setPosition(0.4);
        }
    }

    public void telemetry(){
        telemetry.addData("Servo Position", servo.getPosition());
    }
}
