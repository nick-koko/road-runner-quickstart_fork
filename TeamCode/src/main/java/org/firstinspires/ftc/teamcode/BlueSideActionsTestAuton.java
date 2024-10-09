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
import org.firstinspires.ftc.teamcode.actions.DualSlideActions;


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
        DualSlideActions outtakeSlide =  new DualSlideActions();
        //ClawMechanism outtakeClaw = new ClawMechanism(); TODO Make a claw mechanism class

        //Outtake
        outtakeSlide.init(hardwareMap);

        TrajectoryActionBuilder trajToBlueBars = drive.actionBuilder(initialPoseBlueSideBuckets)
                .lineToY(34);
        TrajectoryActionBuilder trajYellowSample1 = trajToBlueBars.fresh()
                .setTangent(Math.toRadians(60))
                .splineToLinearHeading(new Pose2d(48.5, 37.5, Math.toRadians(-90)), Math.toRadians(0));
        TrajectoryActionBuilder trajToBucketsYellowSample1 = trajYellowSample1.fresh()
                .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(-135)), Math.toRadians(0));
        TrajectoryActionBuilder trajYellowSample2 = trajToBucketsYellowSample1.fresh()
                .splineToLinearHeading(new Pose2d(58, 37.5, Math.toRadians(-90)), Math.toRadians(0));


        Action trajectoryActionToBlueBars = trajToBlueBars.build();

        Action trajectoryActionYellowSample1 = trajYellowSample1.build();

        Action trajectoryActionToBucketsYellowSample1 = trajToBucketsYellowSample1.build();

        Action trajectoryActionYellowSample2 = trajYellowSample2.build();


        // actions that need to happen on init; for instance, a claw tightening.
        // Actions.runBlocking(claw.closeClaw());


        while (opModeInInit()) {
            //TODO Add code to continue to run during initialization here
        }

        Actions.runBlocking(
               new SequentialAction(
                        new ParallelAction(
                                trajectoryActionToBlueBars,
                                outtakeSlide.liftUp()
                        ),
                       // claw.openClaw(),
                       outtakeSlide.liftDown(),
                       trajectoryActionYellowSample1,
                       trajectoryActionToBucketsYellowSample1,
                       trajectoryActionYellowSample2
                )
        );
    }
}
