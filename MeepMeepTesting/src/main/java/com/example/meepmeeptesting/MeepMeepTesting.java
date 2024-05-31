package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        int autonChoice = 3;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
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
                        .lineToX(48)
                        .splineToLinearHeading(new Pose2d(0, 48, Math.toRadians(270)), Math.toRadians(180))
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
            }

        meepMeep.setBackground(MeepMeep.Background.GRID_BLUE)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}