package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;




import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

public final class FirstDemoProgram extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        int autonChoice = 5;
        Action blueCloseSideLeftLine;
        Action blueCloseSideCenterLine;
        Action blueCloseSideRightLineSpline;
        Action blueCloseSideRightLineStraight;
        Action crazyTestPath;

        blueCloseSideLeftLine = drive.actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                .setTangent(Math.toRadians(300))
                .lineToYSplineHeading(30, Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(35, 30), Math.toRadians(0))
                .waitSeconds(2)
                .splineToConstantHeading(new Vector2d(41, 29), Math.toRadians(0))
                .waitSeconds(4)
                .strafeTo(new Vector2d(49, 60))
                .build();

        blueCloseSideCenterLine = drive.actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                .setTangent(Math.toRadians(270))
                .lineToYSplineHeading(12, Math.toRadians(270))
                .waitSeconds(2)
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(25, 15, Math.toRadians(315)), Math.toRadians(45))
                .splineToLinearHeading(new Pose2d(41, 35, Math.toRadians(0)), Math.toRadians(0))
                .waitSeconds(4)
                .strafeTo(new Vector2d(49, 60))
                .build();

        blueCloseSideRightLineStraight = drive.actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(48)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3)
                .build();

        blueCloseSideRightLineSpline = drive.actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(15, 48), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(32, 48), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(44.5, 30,Math.toRadians(180)), Math.toRadians(0))
                .lineToX(47.5)
                .waitSeconds(3)
                .build();

        crazyTestPath = drive.actionBuilder(new Pose2d(0, 0, 0))
                .splineTo(new Vector2d(48, 0), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(0, 48, Math.toRadians(270)), Math.toRadians(180))
                .lineToXSplineHeading(-43, Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-43, 0), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(-43, 60), Math.toRadians(180))
                .turn(Math.toRadians(360))
                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(90)), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(43, -35, Math.toRadians(270)), Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(0, 0, Math.toRadians(0)), Math.toRadians(180))
                .turn(Math.toRadians(-360))
                .build();

        waitForStart();

        if (autonChoice == 1) {
            Actions.runBlocking(blueCloseSideRightLineStraight);
        } else if (autonChoice == 2) {
            Actions.runBlocking(blueCloseSideRightLineSpline);
        } else if (autonChoice == 3) {
            Actions.runBlocking(blueCloseSideLeftLine);
       } else if (autonChoice == 4) {
            Actions.runBlocking(blueCloseSideCenterLine);
        } else if (autonChoice == 5) {
            Actions.runBlocking(crazyTestPath);
        }


    }
}
