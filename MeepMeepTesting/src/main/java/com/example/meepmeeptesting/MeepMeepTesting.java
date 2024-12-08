package com.example.meepmeeptesting;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        int autonChoice = 1;
        Action blueCloseSideLeftLine;
        Action blueCloseSideCenterLine;
        Action blueCloseSideRightLine;
        Action specimenAuton;
        Action sampleAuton;
        Action getOneMoreSpecimen;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 40, Math.toRadians(180), Math.toRadians(180), 14)
                .build();
        if (autonChoice == 1) {
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-9.25, 62, Math.toRadians(90)))
//                .afterTime(0.1, outtakeSlide.specimenDrop())
                .strafeTo(new Vector2d(-10.25,32))

//                .stopAndAdd(outtakeClaw.dropPosition())
//                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-33, 17), Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-42, 16, Math.toRadians(-90)), Math.toRadians(120))
                .splineToConstantHeading(new Vector2d(-44, 46), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-55, 18), Math.toRadians(200))
                .splineToConstantHeading(new Vector2d(-57, 43), Math.toRadians(60))
//                .afterTime(.1, outtakeClaw.open())
                .splineToConstantHeading(new Vector2d(-46.5, 46), Math.toRadians(80))
                .waitSeconds(.5)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
//                .stopAndAdd(outtakeClaw.close())
//                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
//                .afterTime(.01, outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(-8.25, 31,Math.toRadians(90.01)),Math.toRadians(-90))
                //               .strafeTo(new Vector2d(-10.25,32))
//                .stopAndAdd(outtakeClaw.dropPosition())
//                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))
//                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-46.5, 46,Math.toRadians(-90)),Math.toRadians(120))
                //              .waitSeconds(.2)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
//                .stopAndAdd(outtakeClaw.close())
//                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
//                .afterTime(.01, outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(-6.25, 31,Math.toRadians(90.01)),Math.toRadians(-90))
                //              .strafeTo(new Vector2d(-11.25,32))
//                .stopAndAdd(outtakeClaw.dropPosition())
//                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))

//                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-46.5, 46,Math.toRadians(-90)),Math.toRadians(120))
                //              .waitSeconds(.2)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(-120)                .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
                    .splineToLinearHeading(new Pose2d(-2.0, 31,Math.toRadians(90.01)),Math.toRadians(-90))
                    //              .strafeTo(new Vector2d(-11.75,32))
//                    .stopAndAdd(outtakeClaw.dropPosition())
//                    .stopAndAdd(outtakeSlide.low())
                    .setTangent(Math.toRadians(135))
                   .strafeTo(new Vector2d(-50.5, 60), new TranslationalVelConstraint(65), new ProfileAccelConstraint(-99.0, 99.0))
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

            TrajectoryActionBuilder trajToBlueBars = myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(90)))
                    .lineToY(34)
                    .waitSeconds(.001);
            Action trajectoryActionCloseOut = trajToBlueBars.fresh()
                    .strafeTo(new Vector2d(48, 12))
                    .build();

            Action run1 = trajToBlueBars.build();
            myBot.runAction(run1);
            myBot.runAction(trajectoryActionCloseOut);

            //myBot.runAction(trajectoryActionCloseOut);

            /*myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.8, 61.7, Math.toRadians(90)))
                    .lineToY(37)
                    .setTangent(Math.toRadians(0))
                    .lineToX(18)
                    .waitSeconds(3)
                    .setTangent(Math.toRadians(0))
                    .lineToXSplineHeading(46, Math.toRadians(180))
                    .waitSeconds(3)
                    .build());*/
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
        }  else if (autonChoice == 7) {

            sampleAuton = myBot.getDrive().actionBuilder(new Pose2d(9.25, 62, Math.toRadians(90)))
//                .afterTime(0.1, outtakeSlide.specimenDrop())
                .lineToY(32)
                .waitSeconds((1.0))
//                .stopAndAdd(outtakeClaw.off())
//                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(60))
//                .afterTime(1.5, intakeArm.armIntake())
//                .afterTime(1.75, intakeSpinner.intakePosition())
                .splineToLinearHeading(new Pose2d(38.5, 25.5, Math.toRadians(0)), Math.toRadians(-61.9))
                .setTangent(0)
                .lineToX(48.5)
                // PICKUP 1st Yellow Sample
//                .afterTime(0.01, intakeArm.armTransfer())
//                .afterTime(0.25, intakeSpinner.outtakePosition())
                .lineToX(44.5)
//                .afterTime(0.1, outtakeSlide.high())
//                .afterTime(0.25, intakeSpinner.stopPosition())
//                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
//                .afterTime(0.01,outtakeDump.downPosition())
//                .afterTime(0.1,outtakeSlide.low())
//                .afterTime(0.5,intakeArm.armIntake())
//                .afterTime(0.5,intakeSpinner.intakePosition())
                .setTangent(180)
                .splineToLinearHeading(new Pose2d(41.5, 25.5, Math.toRadians(0)), Math.toRadians(10))
                .setTangent(0)
                // PICKUP 2nd Yellow Sample
                .lineToX(51.5)
//                .afterTime(0.01, intakeArm.armTransfer())
//                .afterTime(0.25, intakeSpinner.outtakePosition())
                .waitSeconds(0.2)
                .lineToX(49.0)
//                .afterTime(0.1, outtakeSlide.high())
//                .afterTime(0.25, intakeSpinner.stopPosition())
//                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
//                .afterTime(0.01,outtakeDump.downPosition())
//                .afterTime(0.1,outtakeSlide.low())
//                .afterTime(0.5,intakeArm.armIntake())
//                .afterTime(0.5,intakeSpinner.intakePosition())
                .splineToLinearHeading(new Pose2d(49, 25.5, Math.toRadians(0)), Math.toRadians(0))
                // PICKUP 3rd sample
                .lineToX(57.0)
//                .afterTime(0.01, intakeArm.armTransfer())
                .lineToX(54.0)
//                .stopAndAdd(intakeSpinner.outtakePosition())
                .setTangent(-180)
//                .afterTime(0.5, outtakeSlide.high())
//                .afterTime(0.25, intakeSpinner.stopPosition())
//                .afterTime(1.8, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(110))
                .waitSeconds(0.2)
//                .afterTime(0.01,outtakeDump.downPosition())
//                .afterTime(0.1,outtakeSlide.low())
//                .afterTime(0.5,outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(21, 0, Math.toRadians(0)), Math.toRadians(180))

                .build();

            myBot.runAction(sampleAuton);

         }  else if (autonChoice == 8) {

            specimenAuton = myBot.getDrive().actionBuilder(new Pose2d(-9.25, 62, Math.toRadians(90)))

            //        .afterTime(0.1, outtakeSlide.specimenDrop())
                    .strafeTo(new Vector2d(-9.25,32))
                    .waitSeconds((1.0))
             //       .stopAndAdd(outtakeClaw.off())
             //       .stopAndAdd(outtakeSlide.low())
                    .setTangent(Math.toRadians(120))
                    .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-33, 17), Math.toRadians(-90))
                    .splineToSplineHeading(new Pose2d(-42, 16, Math.toRadians(180)), Math.toRadians(120))
                    .splineToConstantHeading(new Vector2d(-44, 46), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-55, 18), Math.toRadians(200))
                    .splineToConstantHeading(new Vector2d(-57, 43), Math.toRadians(60))
             //       .afterTime(.1, outtakeClaw.open())
//                    .splineToSplineHeading(new Pose2d(-46.5, 56, Math.toRadians(-90)), Math.toRadians(70))

                    .splineToConstantHeading(new Vector2d(-46.5, 56), Math.toRadians(80))
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5))
                    .waitSeconds(.1)
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
                    .splineToLinearHeading(new Pose2d(-10.25, 31,Math.toRadians(90)),Math.toRadians(-90))
//                    .strafeTo(new Vector2d(-10.25,32))
                    .waitSeconds(1.0)
//                    .stopAndAdd(outtakeClaw.off())
//                    .stopAndAdd(outtakeSlide.low())
                    .setTangent(Math.toRadians(120))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-46.5, 56,Math.toRadians(-90)),Math.toRadians(120))
//                    .waitSeconds(.2)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5))
                    .waitSeconds(.1)
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
                    .splineToLinearHeading(new Pose2d(-11.25, 31,Math.toRadians(90)),Math.toRadians(-90))
//                    .strafeTo(new Vector2d(-11.25,32))
                    .waitSeconds(1.0)
 //                   .stopAndAdd(outtakeClaw.off())
 //                   .stopAndAdd(outtakeSlide.low())
                    .setTangent(Math.toRadians(120))
 //                   .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-46.5, 56,Math.toRadians(-90)),Math.toRadians(120))
 //                   .waitSeconds(.2)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5))
                    .waitSeconds(.1)
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
            /*        .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
                    .splineToLinearHeading(new Pose2d(-7.0, 31,Math.toRadians(90)),Math.toRadians(-90))
//                    .strafeTo(new Vector2d(-11.75,32))
                    .waitSeconds(1.0)
//                    .stopAndAdd(outtakeClaw.off())
//                    .stopAndAdd(outtakeSlide.low()) */
                    .build();
            getOneMoreSpecimen = myBot.getDrive().actionBuilder(new Pose2d(-46.5, 62.5, Math.toRadians(-90)))
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
                    .splineToLinearHeading(new Pose2d(-7.0, 31,Math.toRadians(90)),Math.toRadians(-90))
//                    .strafeTo(new Vector2d(-11.75,32))
                    .waitSeconds(1.0)
//                    .stopAndAdd(outtakeClaw.off())
//                    .stopAndAdd(outtakeSlide.low())
                    .build();

            Action runBoth = new SequentialAction(
                    specimenAuton
                   , getOneMoreSpecimen
            );

            myBot.runAction(runBoth);

        }   else if (autonChoice == 9) {
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-60.77, -48, Math.toRadians(0)))
                .splineTo(new Vector2d(-40.25, -18), Math.toRadians(90))
                .splineTo(new Vector2d(-60.77, 12), Math.toRadians(180))
                .build());
    }


                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}