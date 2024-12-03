package com.example.meepmeeptesting;

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

public class RightSideSpecimenFAST_MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        int autonChoice = 1;

        Action trajectoryActionRightSideSpecimen;
        Action trajectoryActionExtraSpecimen;
        Pose2d initialPoseRightSideSpecimen = new Pose2d(-9.25, 62, Math.toRadians(90));

        double pickupXPosition = -45.5;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(75, 68, 5, 5, 14)
                .build();
        if (autonChoice == 1) {

            trajectoryActionRightSideSpecimen = myBot.getDrive().actionBuilder(initialPoseRightSideSpecimen)
//                    .afterTime(0.1, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                    // First Specimen target 11.5"
                    .strafeTo(new Vector2d(-11.5,32))
//                    .stopAndAdd(outtakeSlide.specimenDropDown())
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(120))
                    .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-23, 17), Math.toRadians(-90)) //TODO Check all sample locations to make sure pushed in Observation Zone
                    .splineToSplineHeading(new Pose2d(-48, 17, Math.toRadians(-90)), Math.toRadians(120))
                    .splineToConstantHeading(new Vector2d(-45, 50), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-56, 10), Math.toRadians(200))
                    .splineToConstantHeading(new Vector2d(-57, 53), Math.toRadians(60))
                    .splineToConstantHeading(new Vector2d(-67, 11), Math.toRadians(200))
                    .splineToConstantHeading(new Vector2d(-68, 49), Math.toRadians(70))

//                    .afterTime(.1, outtakeClaw.open())
                    .splineToConstantHeading(new Vector2d(-45.5, 55), Math.toRadians(89.9), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-15.0, 68.0)) //change from 46 to 55
                    //     .waitSeconds(.001) //change from 0.5 to 0.1
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-45.5, 62.5), new TranslationalVelConstraint(15), new ProfileAccelConstraint(-20.0, 68.0)) //change from 46 to 55
                    //     .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-35))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                    // Second Specimen target 0.0"
                    .splineToLinearHeading(new Pose2d(0.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-50), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(150))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-45.5, 55,Math.toRadians(-90.0)),Math.toRadians(110), null, new ProfileAccelConstraint(-25.0, 68.0))
                    .waitSeconds(.002)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-45.5,62.5),  new TranslationalVelConstraint(15), new ProfileAccelConstraint(-15.0, 68.0)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                    // Third Sepcimen target -3.0"
                    .splineToLinearHeading(new Pose2d(-3.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-50), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(150))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-45.5, 55,Math.toRadians(-90.0)),Math.toRadians(110), null, new ProfileAccelConstraint(-25.0, 68.0))
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-45.5,62.5), new TranslationalVelConstraint(15), new ProfileAccelConstraint(-15.0, 68.0)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                    //Fourth Specimen Target -6.0"
                    .splineToLinearHeading(new Pose2d(-6.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-50), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(135))
                    .strafeTo(new Vector2d(-50.5, 60), null, new ProfileAccelConstraint(-99.0, 99.0))
                    .build();


        myBot.runAction(
                new SequentialAction(
                        trajectoryActionRightSideSpecimen
                )

        );

    }


                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}