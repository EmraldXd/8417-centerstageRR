package org.firstinspires.ftc.teamcode.other;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class apriltags {
    Telemetry telemetry;
    HardwareMap hardwareMap;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;
    double tagX;
    double tagY;
    double tagZ;
    double tagPitch;
    double tagYaw;
    boolean tagFound = false;
    public void init (@NonNull OpMode opMode) {
        telemetry = opMode.telemetry;
        hardwareMap = opMode.hardwareMap;
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.addProcessor(aprilTag);
        builder.build();
    }

    public boolean runTag(int keyID) {
        if(aprilTag != null){
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for (AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagFound = true;
                    }
                }
            } else {
                tagFound = false;
            }
        }
        return tagFound;
    }

    public double getxLocation(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagX = detection.ftcPose.x;
                    }
                }
            }
        }
        return tagX;
    }

    public double getyLocation(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagY = detection.ftcPose.y;
                    }
                }
            }
        }
        return tagY;
    }

    public double getzLocation(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagZ = detection.ftcPose.z;
                    }
                }
            }
        }
        return tagZ;
    }

    public double getPitch(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagPitch = detection.ftcPose.pitch;
                    }
                }
            }
        }
        return tagPitch;
    }

    public double getYaw(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagYaw = detection.ftcPose.yaw;
                    }
                }
            }
        }
        return tagYaw;
    }

    public double test(int keyID) {
        if(aprilTag != null) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            if(currentDetections != null) {
                for(AprilTagDetection detection : currentDetections) {
                    if(detection.id == keyID) {
                        tagYaw = detection.ftcPose.yaw;
                    }
                }
            }
        }
        return tagYaw;
    }

    public void telemetryAprilTagAll() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");

    }
}
