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
//import org.firstinspires.ftc.teamcode.actions.DualSlideActions;
//import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;


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
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPoseBlueSideBuckets);
        Action trajectoryActionBlueSideBuckets;
        //DualSlideActions outtakeSlide =  new DualSlideActions();
        //ClawMechanism outtakeClaw = new ClawMechanism();

        //Outtake
        //outtakeSlide.init(hardwareMap);

        trajectoryActionBlueSideBuckets = drive.actionBuilder(initialPoseBlueSideBuckets)
                .lineToY(34)
                //.stopAndAdd(outtakeSlide.liftUp())
                //.stopAndAdd(outtakeClaw.clawOpen())
                //.stopAndAdd(outtakeSlide.liftDown())
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
                .build();


        // actions that need to happen on init; for instance, a claw tightening.
        // Actions.runBlocking(outtakeClaw.clawClose());


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
