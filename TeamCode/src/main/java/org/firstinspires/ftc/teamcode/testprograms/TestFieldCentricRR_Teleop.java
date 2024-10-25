package org.firstinspires.ftc.teamcode.testprograms;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name="TestFieldCentricRR_Teleop")
public class TestFieldCentricRR_Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Pose2d startingPose = new Pose2d(9.25, 62, Math.toRadians(90));

        MecanumDrive robot = new MecanumDrive(hardwareMap, startingPose);
        boolean fieldCentric = true;
        boolean goToTargetAngle;
        double targetAngleDeg = -135.0;
        double targetAngleRad;
        double propAngleGain = 0.5;
        double maxRotate = 0.8;
        double minAnglePower = 0.075;

        Alliance whichAlliance = Alliance.RED;
        double angleAllianceOffset = 90.0;

        // Wait for the game to start (Display Gyro value while waiting)
        while (opModeInInit()) {
            if(gamepad1.x){
                whichAlliance = Alliance.BLUE;
                angleAllianceOffset = -90.0;
                startingPose = new Pose2d(9.25, 62, Math.toRadians(-90));
            } else if (gamepad1.b){
                whichAlliance = Alliance.RED;
                angleAllianceOffset = 90.0;
                startingPose = new Pose2d(-9.25, 6-2, Math.toRadians(90));
            }

            telemetry.addLine("Hello Human of the robot");
            telemetry.addLine("This is an AI Spoken from ChatGPT,");
            telemetry.addLine("Before continuing, select ALLIANCE");
            telemetry.addLine("press X to set ALLIANCE to BLUE and B for RED");
            telemetry.addLine("----------------------------------------------");
            if (whichAlliance == Alliance.RED) {
                telemetry.addLine("RED ALLIANCE selected");
            } else {
                telemetry.addLine("BLUE ALLIANCE selected");
            }

            telemetry.update();
        }
        robot.pose = startingPose;

        while (opModeIsActive()) {

            double driving = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double botHeadingRad = robot.pose.heading.toDouble();
            double botHeading = Math.toDegrees(botHeadingRad);

            if (gamepad1.left_bumper) {
                targetAngleDeg = -45.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_down) {
                targetAngleDeg = 180.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_right) {
                targetAngleDeg = -90.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_left) {
                targetAngleDeg = 90.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_up) {
                targetAngleDeg = 0.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else {
                goToTargetAngle = false;
            }

            targetAngleRad = Math.toRadians(targetAngleDeg);

            if (goToTargetAngle) {
                double targetAngleDiff = botHeadingRad - targetAngleRad;
                if (targetAngleDiff > Math.PI) {
                    targetAngleDiff = (targetAngleDiff - 2 * (Math.PI));
                } else if (targetAngleDiff < -Math.PI) {
                    targetAngleDiff = (2 * (Math.PI) + targetAngleDiff);
                }
                rotate = targetAngleDiff * propAngleGain;
                if (rotate > 0.0) {
                    rotate = rotate + minAnglePower;
                } else if (rotate < 0.0) {
                    rotate = rotate - minAnglePower;
                }
                rotate = Math.max(Math.min(rotate, maxRotate), -maxRotate);
            }

            double angleAllianceOffsetRad = Math.toRadians(angleAllianceOffset);

            double rotX = strafe * Math.cos(-(botHeadingRad-angleAllianceOffsetRad)) - driving * Math.sin(-(botHeadingRad-angleAllianceOffsetRad));
            double rotY = strafe * Math.sin(-(botHeadingRad-angleAllianceOffsetRad)) + driving * Math.cos(-(botHeadingRad-angleAllianceOffsetRad));

            if (gamepad1.y) {
                fieldCentric = true;
            } else if (gamepad1.a) {
                fieldCentric = false;
            }

            if (fieldCentric) {
                strafe = rotX;
                driving = rotY;
            }
            robot.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(driving, -strafe), -rotate));

            robot.updatePoseEstimate();

            telemetry.addData("x", robot.pose.position.x);
            telemetry.addData("y", robot.pose.position.y);
            telemetry.addData("heading (deg)", botHeading);
            telemetry.addData("Rotated X", rotX);
            telemetry.addData("Rotated Y", rotY);
            telemetry.addData("Drive", driving);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("IsFieldCentric?", fieldCentric);
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), robot.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
            }
     }
    public enum Alliance {
        RED,
        BLUE
    }
}
