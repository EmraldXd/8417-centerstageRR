package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class linearSlide {

    static final DecimalFormat df = new DecimalFormat("0.00");
    //Declare NULL
    DcMotor mL;
    DcMotor mR;
    Telemetry telemetry;
    DigitalChannel touchSensor;
    double totalPower = 0.90;
    double slidePower;


    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        mL = hardwareMap.get(DcMotor.class, "Left Nerd");
        mR = hardwareMap.get(DcMotor.class, "Right Slide");
        touchSensor = hardwareMap.get(DigitalChannel.class, "Touch Sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        mR.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setPower(double slidePowerInput) {
        slidePower = slidePowerInput * totalPower * -1;
        if(!(touchSensorPressed() && slidePower < 0) && !(mR.getCurrentPosition() > 2700 && slidePower > 0)){
            mL.setPower(slidePower * .5);
            mR.setPower(slidePower * .5);
        }
    }

    public boolean touchSensorPressed() {
        return !touchSensor.getState();
    }

    public void telemetryOutput() {
        telemetry.addData("Slide power: ", df.format(slidePower));
        telemetry.addData("Left slide position: ", df.format(mL.getCurrentPosition()));
        telemetry.addData("Right slide position: ", df.format(mR.getCurrentPosition()));
        telemetry.addData("Sensor pressed", touchSensorPressed());
    }
}
