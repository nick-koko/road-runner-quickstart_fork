package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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
import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;


@Config
@Autonomous(name = "BlueSideActionsTestAuton", group = "Autonomous")
public class BlueSideActionsTestAuton extends LinearOpMode {

    //OUTTAKE DUAL SLIDE SUBSYSTEM
        //TODO Add Outtake Dual Slide constants here
    //OUTTAKE CLAW SUBSYSTEM
        //TODO Add Outtake Claw constants here

    @Override
    public void runOpMode() {
        Pose2d initialPoseBlueSideBuckets = new Pose2d(9.25, 62, Math.toRadians(90));
        PinpointDrive drive = new PinpointDrive(hardwareMap, initialPoseBlueSideBuckets);

        Action trajectoryActionBlueSideBuckets;
        OuttakeDumpActions outtakeDump = new OuttakeDumpActions();
        DualSlideActions outtakeSlide =  new DualSlideActions();
        ClawActions outtakeClaw = new ClawActions();
        IntakeSlideAction intakeSlide = new IntakeSlideAction();
        IntakeArmActions intakeArm = new IntakeArmActions();
        IntakeservoSpinnerActions intakeSpinner = new IntakeservoSpinnerActions();


        //Outtake
        outtakeSlide.init(hardwareMap);
        outtakeDump.init(hardwareMap);
        intakeArm.init(hardwareMap);
        intakeSpinner.init(hardwareMap);
        intakeSlide.init(hardwareMap);

        //Claw
        outtakeClaw.init(hardwareMap);

        trajectoryActionBlueSideBuckets = drive.actionBuilder(initialPoseBlueSideBuckets)
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

                //        .waitSeconds(0.99)
        //        .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
        //        .waitSeconds(0.99)
        //       .splineToLinearHeading(new Pose2d(58, 37.5, Math.toRadians(-90)), Math.toRadians(0))
        //        .waitSeconds(0.99)
        //        .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
        //        .waitSeconds(0.99)
        //        .splineToLinearHeading(new Pose2d(57, 37.5, Math.toRadians(-45)), Math.toRadians(0))
        //        .waitSeconds(0.99)
        //        .setTangent(Math.toRadians(90))
        //        .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0))
        //       .waitSeconds(0.99)
        //        .splineToLinearHeading(new Pose2d(25, 0, Math.toRadians(-180)), Math.toRadians(180))
                .build();


        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(outtakeClaw.close());
        Actions.runBlocking(intakeArm.armDrive());
        Actions.runBlocking(outtakeDump.downPosition());


        while (opModeInInit()) {
            //TODO Add code to continue to run during initialization here
        }

        Actions.runBlocking(
               new SequentialAction(
                       trajectoryActionBlueSideBuckets
/*                     new ParallelAction(
                                trajectoryActionToBlueBars,
                                outtakeSlide.liftUp()
                        ),
                       // claw.openClaw(),
                       outtakeSlide.liftDown(),
                       trajectoryActionYellowSample1,
                       trajectoryActionToBucketsYellowSample1,
                        */
                )
        );
    }
}
