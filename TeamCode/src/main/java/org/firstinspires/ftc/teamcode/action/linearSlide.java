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
        mL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Takes the slidePowerInput parameter and uses it to set the power of the linear slides.
     * @param slidePowerInput is player 2's left stick Y input which is used to move the slides.
     */
    public void setPower(double slidePowerInput) {
        slidePower = slidePowerInput * totalPower;
        if(!(touchSensorPressed() && slidePower > 0) && !(mR.getCurrentPosition() > 2700 && slidePower > 0)){
            mL.setPower(slidePower * .5);
            mR.setPower(slidePower * .5);
        }
    }

    /**
     * This exists to let the robot know that the slides have gone far enough down.
     * @return whether the sensor is pressed.
     */
    public boolean touchSensorPressed() {
        return !touchSensor.getState();
    }

    public void telemetryOutput() {
        telemetry.addData("Slide power: ", df.format(slidePower));
        telemetry.addData("Right slide position: ", df.format(mR.getCurrentPosition()));
        telemetry.addData("Sensor pressed", touchSensorPressed());
    }

    /**
     * Resets the encoder
     */
    public void resetEncoder() {
        mL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Starts the slides moving upward in our roadrunner autonomous.
     */
    public void startSlideUp() {
        mL.setPower(0.5);
        mR.setPower(0.5);
    }

    /**
     * Starts the slides moving downward in our roadrunner autonomous.
     */
    public void startSlidesDown() {
        mL.setPower(-0.5);
        mR.setPower(-0.5);
    }

    /**
     * Stops the slides and holds them in the position they are at.
     */
    public void stopSlide() {
        mL.setPower(0.06);
        mR.setPower(0.06);
    }
}
