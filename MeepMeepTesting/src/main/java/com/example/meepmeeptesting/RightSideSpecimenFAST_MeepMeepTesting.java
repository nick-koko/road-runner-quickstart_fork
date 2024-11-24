package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(75, 68, 5, 5, 14)
                .build();
        if (autonChoice == 1) {

            trajectoryActionRightSideSpecimen = myBot.getDrive().actionBuilder(initialPoseRightSideSpecimen)
//                    .afterTime(0.1, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                    .strafeTo(new Vector2d(-9.25,32))
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .waitSeconds(.5)
                    .setTangent(Math.toRadians(120))
                    .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-25, 17), Math.toRadians(-90)) //TODO Go closer to sub to avoid hitting the sample
                    .splineToSplineHeading(new Pose2d(-47, 16, Math.toRadians(-90)), Math.toRadians(120))
                    .splineToConstantHeading(new Vector2d(-44, 46), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-55, 10), Math.toRadians(200))
                    .splineToConstantHeading(new Vector2d(-57, 48), Math.toRadians(60)) //TODO drive closer to human player to make sure sample is pushed in
                    .splineToConstantHeading(new Vector2d(-66, 11), Math.toRadians(200))
                    .splineToConstantHeading(new Vector2d(-66, 50), Math.toRadians(70))
                    //+
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToConstantHeading(new Vector2d(-46.5, 55), Math.toRadians(80), null, new ProfileAccelConstraint(-35.0, 68.0)) //change from 46 to 55
                    .waitSeconds(.1) //change from 0.5 to 0.1
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                    .splineToLinearHeading(new Pose2d(-9.25, 31,Math.toRadians(90)),Math.toRadians(-90), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
                    //               .strafeTo(new Vector2d(-10.25,32))
                    .waitSeconds(.5)
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .setTangent(Math.toRadians(120))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-46.5, 55,Math.toRadians(-90)),Math.toRadians(120), null, new ProfileAccelConstraint(-45.0, 68.0))
                    //              .waitSeconds(.2)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                    .splineToLinearHeading(new Pose2d(-10.25, 31,Math.toRadians(90)),Math.toRadians(-90), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
                    //              .strafeTo(new Vector2d(-11.25,32))
                    .waitSeconds(.5)
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .setTangent(Math.toRadians(120))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-46.5, 55,Math.toRadians(-90)),Math.toRadians(120), null, new ProfileAccelConstraint(-45.0, 68.0))
                    //              .waitSeconds(.2)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(-120)                .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                    .splineToLinearHeading(new Pose2d(-2.0, 31,Math.toRadians(90)),Math.toRadians(-90), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
                    //              .strafeTo(new Vector2d(-11.75,32))
                    .waitSeconds(.5)
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                    .setTangent(Math.toRadians(120))
//                    .afterTime(.1, outtakeClaw.open())
                    .splineToLinearHeading(new Pose2d(-46.5, 55,Math.toRadians(-90)),Math.toRadians(120), null, new ProfileAccelConstraint(-45.0, 68.0))
                    //              .waitSeconds(.2)
                    .setTangent(Math.toRadians(90))
                    .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
//                    .stopAndAdd(outtakeClaw.close())
//                    .stopAndAdd(outtakeSlide.extendAction())
                    .setTangent(-120)                .setTangent(Math.toRadians(-40))
//                    .afterTime(.01, outtakeSlide.specimenDrop())
//                    .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                    .splineToLinearHeading(new Pose2d(-2.0, 31,Math.toRadians(90)),Math.toRadians(-90), null, new ProfileAccelConstraint(-35.0, 68.0)) //TODO Fix drop location and slow down decel
                    //              .strafeTo(new Vector2d(-11.75,32))
                    .waitSeconds(.5)
//                    .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
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