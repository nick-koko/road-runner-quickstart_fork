package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.ClawActions;
import org.firstinspires.ftc.teamcode.actions.DualSlideActions;
import org.firstinspires.ftc.teamcode.actions.IntakeArmActions;
import org.firstinspires.ftc.teamcode.actions.IntakeSlideAction;
import org.firstinspires.ftc.teamcode.actions.IntakeservoSpinnerActions;
import org.firstinspires.ftc.teamcode.actions.OuttakeDumpActions;
import org.firstinspires.ftc.teamcode.mechanisms.ClimbingHooks;


@Config
@Autonomous(name = "Test_5_Specimen_Auton", group = "Autonomous")
public class Test_Right_5_Specimens_Auton extends LinearOpMode {

    //OUTTAKE DUAL SLIDE SUBSYSTEM
        //TODO Add Outtake Dual Slide constants here
    //OUTTAKE CLAW SUBSYSTEM
        //TODO Add Outtake Claw constants here

    @Override
    public void runOpMode() {
        Pose2d initialPoseRightSideSpecimen = new Pose2d(-9.25, 62, Math.toRadians(90));
        Pose2d initialPoseLeftSideBuckets = new Pose2d(9.25, 62, Math.toRadians(90));
        Pose2d initialPoseLeftSideSampleStart = new Pose2d(32.875, 62, Math.toRadians(90));
        Pose2d startingPose = initialPoseRightSideSpecimen;

        // Set higher max Velocities/Accels for this auto
        MecanumDrive.PARAMS.maxAngAccel = 5.0;
        MecanumDrive.PARAMS.maxAngVel = 5.0;
        MecanumDrive.PARAMS.maxProfileAccel = 68.0;
        MecanumDrive.PARAMS.maxWheelVel = 75.0;
        MecanumDrive.PARAMS.minProfileAccel = -50.0;

        PinpointDrive drive = new PinpointDrive(hardwareMap, startingPose);

        Action trajectoryActionLeftSideBuckets;
        Action trajectoryActionRightSideSpecimen;
        Action trajectoryActionExtraSpecimen;
        Action trajectoryActionSampleStart;
        OuttakeDumpActions outtakeDump = new OuttakeDumpActions();
        DualSlideActions outtakeSlide =  new DualSlideActions();
        ClawActions outtakeClaw = new ClawActions();
        IntakeSlideAction intakeSlide = new IntakeSlideAction();
        IntakeArmActions intakeArm = new IntakeArmActions();
        IntakeservoSpinnerActions intakeSpinner = new IntakeservoSpinnerActions();
        ClimbingHooks climbingServo = new ClimbingHooks();
        SIDE fieldSide = SIDE.RIGHT;
        int numPieces = 4;


        //Outtake
        outtakeSlide.init(hardwareMap);
        outtakeDump.init(hardwareMap);
        intakeArm.init(hardwareMap);
        intakeSpinner.init(hardwareMap);
        intakeSlide.init(hardwareMap);
        climbingServo.init(hardwareMap);
        intakeSlide.resetSlide();
        outtakeSlide.resetSlide();

        //Claw
        outtakeClaw.init(hardwareMap);
        trajectoryActionRightSideSpecimen = drive.actionBuilder(initialPoseRightSideSpecimen)
                .afterTime(0.1, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                // First Specimen target 11.5"
                .strafeTo(new Vector2d(-11.5,32))
                .stopAndAdd(outtakeSlide.specimenDropDown())
                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-25, 17), Math.toRadians(-90)) //TODO Check all sample locations to make sure pushed in Observation Zone
                .splineToSplineHeading(new Pose2d(-48, 17, Math.toRadians(-90)), Math.toRadians(120))

                .splineToLinearHeading(new Pose2d(-49, 53, Math.toRadians(-90)), Math.toRadians(100))
//                    .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(-54, 9, Math.toRadians(-90)), Math.toRadians(200))
//                    .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(-52, 53, Math.toRadians(-90)), Math.toRadians(-80), new TranslationalVelConstraint(55), new ProfileAccelConstraint(-50.0, 68.0))
                .splineToConstantHeading(new Vector2d(-58, 11), Math.toRadians(130), new TranslationalVelConstraint(50), new ProfileAccelConstraint(-50.0, 68.0))
//                    .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(-68, 40), Math.toRadians(90), new TranslationalVelConstraint(50), new ProfileAccelConstraint(-50.0, 68.0))




/* old                .splineToConstantHeading(new Vector2d(-45, 49), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-54, 10), Math.toRadians(200))
//                    .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(-57, 51), Math.toRadians(-60))
                .splineToConstantHeading(new Vector2d(-67, 11), Math.toRadians(100))
//                    .waitSeconds(.5)
                .splineToConstantHeading(new Vector2d(-70, 49), Math.toRadians(90), new TranslationalVelConstraint(50), new ProfileAccelConstraint(-50.0, 68.0))
*/
                .afterTime(.1, outtakeClaw.open())
                .splineToConstantHeading(new Vector2d(-38.5, 55), Math.toRadians(89.9), new TranslationalVelConstraint(35), new ProfileAccelConstraint(-30.0, 68.0)) //change from 46 to 55
           //     .waitSeconds(.001) //change from 0.5 to 0.1
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-38.5, 63.0), new TranslationalVelConstraint(15), new ProfileAccelConstraint(-16.0, 68.0)) //change from 46 to 55
           //     .strafeTo(new Vector2d(-46.5,62.5)) //TODO Maybe reduce max decel
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-35))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                // Second Specimen target -2.0"
                .splineToLinearHeading(new Pose2d(-2.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-50), null, new ProfileAccelConstraint(-39.0, 68.0)) //TODO Fix drop location and slow down decel
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                .setTangent(Math.toRadians(150))
                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-38.5, 55,Math.toRadians(-90.0)),Math.toRadians(140), null, new ProfileAccelConstraint(-45.0, 68.0))
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-38.5,63.0),  new TranslationalVelConstraint(15), new ProfileAccelConstraint(-16.0, 68.0)) //TODO Maybe reduce max decel
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                // Third Sepcimen target -4.0"
                .splineToLinearHeading(new Pose2d(-4.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-65), null, new ProfileAccelConstraint(-38.0, 68.0)) //TODO Fix drop location and slow down decel
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                .setTangent(Math.toRadians(150))
                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-38.5, 55,Math.toRadians(-90.0)),Math.toRadians(140), null, new ProfileAccelConstraint(-45.0, 68.0))
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-38.5,63.0), new TranslationalVelConstraint(15), new ProfileAccelConstraint(-16.0, 68.0)) //TODO Maybe reduce max decel
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                //Fourth Specimen Target -6.0"
                .splineToLinearHeading(new Pose2d(-6.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-75), null, new ProfileAccelConstraint(-38.0, 68.0)) //TODO Fix drop location and slow down decel
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                .setTangent(Math.toRadians(150))
                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-38.5, 55,Math.toRadians(-90)),Math.toRadians(140), null, new ProfileAccelConstraint(-45.0, 68.0))
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-38.5,63.0), new TranslationalVelConstraint(15), new ProfileAccelConstraint(-16.0, 68.0)) //TODO Maybe reduce max decel
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime

                // Fifth Specimen target -8.0"
                .splineToLinearHeading(new Pose2d(-8.0, 31.5,Math.toRadians(89.9)),Math.toRadians(-80), null, new ProfileAccelConstraint(-38.0, 68.0)) //TODO Fix drop location and slow down decel
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                .setTangent(Math.toRadians(135))
                .waitSeconds(0.05)
//                .strafeTo(new Vector2d(-50.5, 60), null, new ProfileAccelConstraint(-99.0, 99.0))
                .build();



        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(outtakeClaw.close());
        Actions.runBlocking(intakeArm.armDrive());
        Actions.runBlocking(outtakeDump.downPosition());
        climbingServo.hookPositionDown();

        drive.pose = startingPose;
        while (opModeInInit()) {

            fieldSide = SIDE.RIGHT;
            startingPose =  initialPoseRightSideSpecimen;
            drive.pose = startingPose;
            numPieces = 5;

            telemetry.addLine("Hello Pickle of the robot");
            telemetry.addLine("This is an Mr. Todone Speaking,");
            telemetry.addLine("----------------------------------------------");
            if (fieldSide == SIDE.RIGHT) {
                telemetry.addLine("Right SIDE selected");
            } else {
                telemetry.addLine("Left SIDE selected");
            }
            if(numPieces == 4){
                if (fieldSide == SIDE.RIGHT) {
                telemetry.addLine("FOUR Specimens");
                } else {
                    telemetry.addLine("Preload SPECIMEN, THREE in the BUCKET");
                }
            } else if (numPieces == 5){
                if (fieldSide == SIDE.RIGHT) {
                    telemetry.addLine("FIVE Specimens");
                } else {
                    telemetry.addLine("FOUR in the BUCKET");
                }
            }


            telemetry.update();

            //TODO Add code to continue to run during initialization here
        }

            Actions.runBlocking(
                    new SequentialAction(
                            trajectoryActionRightSideSpecimen
                           /* ,
                            trajectoryActionExtraSpecimen*/
                    )
            );

        globalRobotData.autonPose = drive.pose;
        globalRobotData.hasAutonRun = true;
    }
    public enum SIDE {
        LEFT,
        RIGHT
    }
}