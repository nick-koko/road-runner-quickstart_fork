package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        int autonChoice = 3;
        Action blueCloseSideLeftLine;
        Action blueCloseSideCenterLine;
        Action blueCloseSideRightLine;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 30, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        if (autonChoice == 1) {
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
           /*     .lineToX(40)
                .splineTo(new Vector2d(0, 20), -Math.PI / 2)
                .turn(Math.toRadians(-270))
                .splineTo(new Vector2d(-48, 48), -Math.PI / 2)  */
                //.lineToY(30)
                //.turn(Math.toRadians(90))
                //.lineToX(0)
                //.turn(Math.toRadians(90))
                //.lineToY(-10)
                //.turn(Math.toRadians(90))
                //.lineToX(40)
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                 .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(15, 48), Math.toRadians(0))
                //.setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(32, 48), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(44.5, 30,Math.toRadians(180)), Math.toRadians(0))
                //.turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3)
                .build());

        } else if (autonChoice == 2) {
            myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
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
                .build());
        } else if (autonChoice == 3) {
                myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                       // .lineToXSplineHeading(48, Math.toRadians(0))
                        .splineTo(new Vector2d(48, 0), Math.toRadians(0))
                        //.lineToX(48)
                        .splineToSplineHeading(new Pose2d(0, 48, Math.toRadians(270)), Math.toRadians(180))
                        .lineToXSplineHeading(-43, Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(-43, 0), Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(-43, 60), Math.toRadians(180))
                        .turn(Math.toRadians(360))
                        .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(90)), Math.toRadians(0))
                        .splineToSplineHeading(new Pose2d(43, -35, Math.toRadians(270)), Math.toRadians(270))
                        .splineToSplineHeading(new Pose2d(0, 0, Math.toRadians(0)), Math.toRadians(180))
                        .turn(Math.toRadians(-360))

                        //.splineTo(new Vector2d(0, 20), -Math.PI / 2)
                        //.turn(Math.toRadians(-270))
                        //.splineTo(new Vector2d(-48, 48), -Math.PI / 2)
                        //.lineToY(30)
                        //.turn(Math.toRadians(90))
                        //.lineToX(0)
                        //.turn(Math.toRadians(90))
                        //.lineToY(-10)
                        //.turn(Math.toRadians(90))
                        //.lineToX(40)
                        .build());
        } else if (autonChoice == 4) {
            myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                    .lineToX(-12)
                    .splineTo(new Vector2d(-24, -12), Math.toRadians(270))
                    .turn(Math.toRadians(90))
                    .turn(Math.toRadians(90))
                  //  .setReversed(true)
                    .setTangent(Math.toRadians(90))
                    .splineTo(new Vector2d(-36, 0), Math.toRadians(180))

 /*                   .strafeTo(new Vector2d(30, -20))
                    .setTangent(Math.toRadians(300))
                    .lineToX(48)
/*                    .splineToLinearHeading(new Pose2d(0, 48, Math.toRadians(270)), Math.toRadians(180))
                    .lineToXSplineHeading(-43, Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(-43, 0), Math.toRadians(270))
                    .splineToConstantHeading(new Vector2d(-43, 60), Math.toRadians(180))
                    .turn(Math.toRadians(360))
                    .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(90)), Math.toRadians(0))
                    .splineToSplineHeading(new Pose2d(43, -35, Math.toRadians(270)), Math.toRadians(270))
                    .splineToSplineHeading(new Pose2d(0, 0, Math.toRadians(0)), Math.toRadians(180))
                    .turn(Math.toRadians(-360)) */
                    .build());
        } else if (autonChoice == 5) {
            blueCloseSideLeftLine = myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                    .setTangent(Math.toRadians(300))
                    .lineToYSplineHeading(30, Math.toRadians(0))
                    .splineToConstantHeading(new Vector2d(35, 30), Math.toRadians(0))
                    //.waitSeconds(2)
                    .splineToConstantHeading(new Vector2d(41, 29), Math.toRadians(0))
                    //.waitSeconds(4)
                    .strafeTo(new Vector2d(49, 60))
                    .build();

            blueCloseSideCenterLine = myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(270)))
                    .setTangent(Math.toRadians(270))
                    .lineToYSplineHeading(12, Math.toRadians(270))
                    //.waitSeconds(2)
                    .setTangent(Math.toRadians(0))
                    .splineToSplineHeading(new Pose2d(25, 15, Math.toRadians(315)), Math.toRadians(45))
                    .splineToLinearHeading(new Pose2d(41, 35, Math.toRadians(0)), Math.toRadians(0))
                    //.waitSeconds(4)
                    .strafeTo(new Vector2d(49, 60))
                    .build();

            myBot.runAction(blueCloseSideLeftLine);
        }

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}