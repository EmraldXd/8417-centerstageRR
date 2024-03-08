package org.firstinspires.ftc.teamcode.other;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;
public class tfSetup {
    String label;
    String position;
    float colorConfidence;
    float locationConfidence;
    float locationX;
    private static final String TFOD_FILE = "/sdcard/FIRST/tflitemodels/onFieldModel.tflite";
    private static final String[] LABLES = {
            "BlueProp",
            "RedProp",
    };
    //Gets our tfod processor
    private TfodProcessor tfod;
    //activates our vision portal
    private VisionPortal visionPortal;
    Telemetry telemetry;
    HardwareMap hardwareMap;

    public void init(@NonNull OpMode opMode) {
        telemetry = opMode.telemetry;
        hardwareMap = opMode.hardwareMap;
        //build the tfod model
        tfod = new TfodProcessor.Builder()
                .setModelFileName(TFOD_FILE)
                .setModelLabels(LABLES)
                .build();

        //build the vision portal
        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.addProcessor(tfod);
        visionPortal = builder.build();
    }

    public String runTfodLabel(String key) {
        if(tfod != null) {
            List<Recognition> recognitions = tfod.getRecognitions();
            if (recognitions != null) {
                for (Recognition recognition : recognitions) {
                    if (recognition.getConfidence() > colorConfidence && Math.abs(recognition.getRight() - recognition.getLeft()) <= 175) {
                        colorConfidence = recognition.getConfidence();
                        label = recognition.getLabel();
                    }
                }
                colorConfidence = -1;
                return label;
            }
        }
        colorConfidence = -1;
        return null;
    }

    public String runTfodSide() {
        if(tfod != null) {
            List<Recognition> recognitions = tfod.getRecognitions();
            if(recognitions != null) {
                for(Recognition recognition : recognitions) {
                    if(recognition.getConfidence() > locationConfidence) {
                        locationX = (recognition.getRight() + recognition.getLeft()) / 2;
                        if(locationX < 160) {
                            position = "left";
                        } else if(locationX > 160) {
                            position = "middle";
                        } else {
                            position = "right";
                        }
                    }
                }
            }
        }
        return position;
    }

    public void telemetry() {
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2;
            double y = (recognition.getTop() + recognition.getBottom()) / 2;

            telemetry.addData("", " ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            if (x < 160) {
                telemetry.addData("Side: ", "left");
            } else if (x > 160) {
                telemetry.addData("Side: ", "middle");
            } else if (currentRecognitions.size() == 0) {
                telemetry.addData("Side: ", "right");
            }
        }
    }

    public void quit() {
        tfod.shutdown();
        visionPortal.close();
    }
}
