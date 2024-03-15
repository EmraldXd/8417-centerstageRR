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

    /**
     * This is the code that launches our drone from the slingshot/crossbow that is mounted on the
     * back of our bot. The bigRedButton parameter is the player 2 button to launch the drone. Note
     * that BOTH the player1Key and bigRedButton have to be pressed in order to launch the drone.
     * This was made so that we would not pre-fire the drone. Yes this has happened before, and yes
     * we took inspiration from how movies portray nuclear launches.
     * @param bigRedButton is the player two button that is pressed to launch the drone.
     * @param player1Key is the player one button that is pressed to launch the drone.
     */
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
