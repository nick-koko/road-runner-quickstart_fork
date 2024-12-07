package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
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
@Autonomous(name = "LEFT_START_With_YELLOW_SAMPLE_GigglyPicklesAuton", group = "Autonomous")
public class LEFT_START_WithYellowSample_IntoTheDeepPicklesAuton extends LinearOpMode {

    //OUTTAKE DUAL SLIDE SUBSYSTEM
        //TODO Add Outtake Dual Slide constants here
    //OUTTAKE CLAW SUBSYSTEM
        //TODO Add Outtake Claw constants here

    @Override
    public void runOpMode() {
        Pose2d initialPoseRightSideSpecimen = new Pose2d(-9.25, 62, Math.toRadians(90));
        Pose2d initialPoseLeftSideBuckets = new Pose2d(9.25, 62, Math.toRadians(90));
        Pose2d initialPoseLeftSideSampleStart = new Pose2d(32.875, 62, Math.toRadians(90));
        Pose2d startingPose = initialPoseLeftSideSampleStart;

        // Set standard max Velocities/Accels for this auto
        MecanumDrive.PARAMS.maxAngAccel = Math.PI;
        MecanumDrive.PARAMS.maxAngVel = Math.PI;
        MecanumDrive.PARAMS.maxProfileAccel = 50.0;
        MecanumDrive.PARAMS.maxWheelVel = 50.0;
        MecanumDrive.PARAMS.minProfileAccel = -30.0;

        PinpointDrive drive = new PinpointDrive(hardwareMap, startingPose);

        globalRobotData.hasAutonRun = true;

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
        int numPieces = 3;


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
/*        trajectoryActionRightSideSpecimen = drive.actionBuilder(initialPoseRightSideSpecimen)
                .afterTime(0.1, outtakeSlide.specimenDrop())
                .strafeTo(new Vector2d(-9.25,32))
                .stopAndAdd(outtakeClaw.off())
                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(-22, 36,Math.toRadians(180)), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-33, 17), Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-42, 16, Math.toRadians(-90)), Math.toRadians(120))
                .splineToConstantHeading(new Vector2d(-44, 46), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-55, 18), Math.toRadians(200))
                .splineToConstantHeading(new Vector2d(-57, 43), Math.toRadians(60))
                .afterTime(.1, outtakeClaw.open())
                .splineToConstantHeading(new Vector2d(-46.5, 46), Math.toRadians(80))
                .waitSeconds(.5)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(-10.25, 31,Math.toRadians(90)),Math.toRadians(-90))
 //               .strafeTo(new Vector2d(-10.25,32))
                .stopAndAdd(outtakeClaw.off())
                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))
                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-46.5, 46,Math.toRadians(-90)),Math.toRadians(120))
  //              .waitSeconds(.2)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(-11.25, 31,Math.toRadians(90)),Math.toRadians(-90))
  //              .strafeTo(new Vector2d(-11.25,32))
                .stopAndAdd(outtakeClaw.off())
                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(120))
                .afterTime(.1, outtakeClaw.open())
                .splineToLinearHeading(new Pose2d(-46.5, 46,Math.toRadians(-90)),Math.toRadians(120))
  //              .waitSeconds(.2)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-46.5,62.5))
                .build();

        trajectoryActionExtraSpecimen = drive.actionBuilder(new Pose2d(-46.5, 62.5,Math.toRadians(-90)))
                .stopAndAdd(outtakeClaw.close())
                .stopAndAdd(outtakeSlide.extendAction())
                .setTangent(-120)                .setTangent(Math.toRadians(-40))
                .afterTime(.01, outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(-2.0, 31,Math.toRadians(90)),Math.toRadians(-90))
  //              .strafeTo(new Vector2d(-11.75,32))
                .stopAndAdd(outtakeClaw.off())
                .stopAndAdd(outtakeSlide.low())
                .build();


 /*               .waitSeconds(.75)
                .setTangent(Math.toRadians(-40))
                .splineToLinearHeading(new Pose2d(-11.25, 32,Math.toRadians(90)),Math.toRadians(-48))
                .waitSeconds(1.0)
                .setTangent(Math.toRadians(120))
                .splineToLinearHeading(new Pose2d(-47, 50,Math.toRadians(-90)),Math.toRadians(120))
*/


        trajectoryActionSampleStart = drive.actionBuilder(initialPoseLeftSideSampleStart)

                .stopAndAdd(intakeArm.armTransfer())
                .stopAndAdd(intakeSpinner.outtakePosition())
                .waitSeconds(.2)
                .setTangent(Math.toRadians(-80))
                .afterTime(0.1, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(1.0)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .splineToLinearHeading(new Pose2d(38.5, 25.5, Math.toRadians(0)), Math.toRadians(-100))
                .setTangent(0)

                .lineToX(46.5)
                .setTangent(Math.toRadians(180))
                .lineToX(38.5)
                .stopAndAdd(intakeArm.armIntake())
                .stopAndAdd(intakeSpinner.intakePosition())
                .waitSeconds(0.4)
                .lineToX(42.5)
                // PICKUP 1st Yellow Sample
                .afterTime(0.01, intakeArm.armTransfer())
                .afterTime(0.25, intakeSpinner.outtakePosition())
                .lineToX(41.0)
                .afterTime(0.1, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(2.0, outtakeDump.dumpPosition())
                .setTangent(Math.toRadians(150))
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,intakeArm.armIntake())
                .afterTime(0.5,intakeSpinner.intakePosition())
                .setTangent(180)
                .splineToLinearHeading(new Pose2d(44.5, 25.5, Math.toRadians(0)), Math.toRadians(10))
                .setTangent(0)
                // PICKUP 2nd Yellow Sample
                .lineToX(50.5)
                .afterTime(0.01, intakeArm.armTransfer())
                .afterTime(0.25, intakeSpinner.outtakePosition())
                .waitSeconds(0.2)
                .lineToX(49.0)
                .afterTime(0.1, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,intakeArm.armIntake())
                .afterTime(0.5,intakeSpinner.intakePosition())
                .splineToLinearHeading(new Pose2d(49, 25.5, Math.toRadians(0)), Math.toRadians(0))
                // PICKUP 3rd sample
                .lineToX(57.0)
                .afterTime(0.01, intakeArm.armTransfer())
                .lineToX(54.0)
                .stopAndAdd(intakeSpinner.outtakePosition())
                .setTangent(-180)
                .afterTime(0.5, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(1.8, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(110))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,outtakeSlide.endAutonPos())
                .splineToLinearHeading(new Pose2d(21, 0, Math.toRadians(0)), Math.toRadians(180))
                .stopAndAdd(outtakeSlide.stopOuttakeSlide())
                .build();

/*
       trajectoryActionLeftSideBuckets = drive.actionBuilder(initialPoseLeftSideBuckets)

                .afterTime(0.1, outtakeSlide.specimenDrop())
                .lineToY(32)
                .stopAndAdd(outtakeClaw.off())
                .stopAndAdd(outtakeSlide.low())
                .setTangent(Math.toRadians(60))
                .afterTime(1.5, intakeArm.armIntake())
                .afterTime(1.75, intakeSpinner.intakePosition())
                .splineToLinearHeading(new Pose2d(38.5, 25.5, Math.toRadians(0)), Math.toRadians(-61.9))
                .setTangent(0)
                .lineToX(48.5)
                    // PICKUP 1st Yellow Sample
                .afterTime(0.01, intakeArm.armTransfer())
                .afterTime(0.25, intakeSpinner.outtakePosition())
                .lineToX(44.5)
                .afterTime(0.1, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,intakeArm.armIntake())
                .afterTime(0.5,intakeSpinner.intakePosition())
                .setTangent(180)
                .splineToLinearHeading(new Pose2d(41.5, 25.5, Math.toRadians(0)), Math.toRadians(10))
                .setTangent(0)
                    // PICKUP 2nd Yellow Sample
                .lineToX(51.5)
                .afterTime(0.01, intakeArm.armTransfer())
                .afterTime(0.25, intakeSpinner.outtakePosition())
                .waitSeconds(0.2)
                .lineToX(49.0)
                .afterTime(0.1, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(2.0, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(0))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,intakeArm.armIntake())
                .afterTime(0.5,intakeSpinner.intakePosition())
                .splineToLinearHeading(new Pose2d(49, 25.5, Math.toRadians(0)), Math.toRadians(0))
                    // PICKUP 3rd sample
                .lineToX(57.0)
                .afterTime(0.01, intakeArm.armTransfer())
                .lineToX(54.0)
                .stopAndAdd(intakeSpinner.outtakePosition())
                .setTangent(-180)
                .afterTime(0.5, outtakeSlide.high())
                .afterTime(0.25, intakeSpinner.stopPosition())
                .afterTime(1.8, outtakeDump.dumpPosition())
                .splineToLinearHeading(new Pose2d(57, 55, Math.toRadians(-135)), Math.toRadians(110))
                .waitSeconds(0.2)
                .afterTime(0.01,outtakeDump.downPosition())
                .afterTime(0.1,outtakeSlide.low())
                .afterTime(0.5,outtakeSlide.specimenDrop())
                .splineToLinearHeading(new Pose2d(21, 0, Math.toRadians(0)), Math.toRadians(180))
                .build();

*/
        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(outtakeClaw.close());
        Actions.runBlocking(intakeArm.armDrive());
        Actions.runBlocking(outtakeDump.downPosition());
        climbingServo.hookPositionDown();


        drive.pose = startingPose;
        while (opModeInInit()) {
            fieldSide = SIDE.LEFT;
            startingPose =  initialPoseLeftSideSampleStart;
            drive.pose = startingPose;
            numPieces = 4;

            telemetry.addLine("Hello Pickle of the robot");
            telemetry.addLine("This is an Mr. Todone Speaking,");
            telemetry.addLine("----------------------------------------------");
            if (fieldSide == SIDE.RIGHT) {
                telemetry.addLine("Right SIDE selected");
            } else {
                telemetry.addLine("Left SIDE selected");
            }
            if(numPieces == 3){
                if (fieldSide == SIDE.RIGHT) {
                    telemetry.addLine("THREE Specimens");
                } else {
                    telemetry.addLine("Preload SPECIMEN, THREE in the BUCKET");
                }
            } else if (numPieces == 4){
                if (fieldSide == SIDE.RIGHT) {
                    telemetry.addLine("FOUR Specimens");
                } else {
                    telemetry.addLine("FOUR in the BUCKET");
                }
            }

            telemetry.update();

            //TODO Add code to continue to run during initialization here
        }

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionSampleStart
                )
        );

        globalRobotData.autonPose = drive.pose;
    }
    public enum SIDE {
        LEFT,
        RIGHT
    }
}
