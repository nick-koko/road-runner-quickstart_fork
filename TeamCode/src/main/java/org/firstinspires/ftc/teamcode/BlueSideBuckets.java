package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "BkueSideBuckets", group = "Autonomous")
@Disabled
public final class BlueSideBuckets extends LinearOpMode {
    @Override
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(9.25, 62, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
            drive.actionBuilder(beginPose)
                    .lineToY(34)
                    .waitSeconds(0.9)
                    .setTangent(Math.toRadians(60))
                    .splineToLinearHeading(new Pose2d(48.5, 37.5, Math.toRadians(-90)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .splineToLinearHeading(new Pose2d(58, 37.5, Math.toRadians(-90)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .splineToLinearHeading(new Pose2d(57, 37.5, Math.toRadians(-45)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .setTangent(Math.toRadians(90))
                    .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
                    .waitSeconds(0.99)
                    .splineToLinearHeading(new Pose2d(25, 0, Math.toRadians(-180)), Math.toRadians(180))
                    //.splineTo(new Vector2d(20, 30), Math.PI / 2)
                    //.splineTo(new Vector2d(0, 60), Math.PI)
                    .build());

    }
}
