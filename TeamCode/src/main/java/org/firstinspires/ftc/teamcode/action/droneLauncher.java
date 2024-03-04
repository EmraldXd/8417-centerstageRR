package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class droneLauncher {

    static final DecimalFormat df = new DecimalFormat("0.00");
    DcMotor launch;
    double motorPower;
    Telemetry telemetry;
    double totalPower = 1.00;

    public void init(@NonNull OpMode opMode){
        telemetry = opMode.telemetry;
        HardwareMap hardwareMap = opMode.hardwareMap;
        launch = hardwareMap.get(DcMotor.class, "Launcher");
    }

    public void launch(double input) {
        if(input > 0) {
            motorPower = 1 * totalPower;
            launch.setPower(motorPower);
        }
    }

    public void telemetry() {
        telemetry.addData("Launcher Power: ", df.format(motorPower));
    }
}
