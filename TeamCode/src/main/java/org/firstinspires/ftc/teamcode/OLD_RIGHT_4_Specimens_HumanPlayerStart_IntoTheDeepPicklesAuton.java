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


@Config
@Autonomous(name = "zOLD_RIGHT_FOUR_SPECIMENS_HumanPlayer_GigglyPicklesAuton", group = "Autonomous")
public class OLD_RIGHT_4_Specimens_HumanPlayerStart_IntoTheDeepPicklesAuton extends LinearOpMode {

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
        SIDE fieldSide = SIDE.RIGHT;
        int numPieces = 3;


        //Outtake
        outtakeSlide.init(hardwareMap);
        outtakeDump.init(hardwareMap);
        intakeArm.init(hardwareMap);
        intakeSpinner.init(hardwareMap);
        intakeSlide.init(hardwareMap);
        intakeSlide.resetSlide();
        outtakeSlide.resetSlide();

        //Claw
        outtakeClaw.init(hardwareMap);
        trajectoryActionRightSideSpecimen = drive.actionBuilder(initialPoseRightSideSpecimen)
                .afterTime(0.1, outtakeSlide.specimenDrop())
                .strafeTo(new Vector2d(-10.25,32))
                .stopAndAdd(outtakeClaw.dropPosition())
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
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                .splineToLinearHeading(new Pose2d(-8.25, 31.5,Math.toRadians(90.001)),Math.toRadians(-90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-38.0, 68.0))
 //               .strafeTo(new Vector2d(-10.25,32))
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low

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
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                .splineToLinearHeading(new Pose2d(-6.25, 31.5,Math.toRadians(90.001)),Math.toRadians(-90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-38.0, 68.0))
  //              .strafeTo(new Vector2d(-11.25,32))
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low


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
                .setTangent(Math.toRadians(-40))

                .afterTime(.01, outtakeSlide.specimenDrop())
                .afterTime(0.5, outtakeClaw.dropPosition()) //change to aftertime
                .splineToLinearHeading(new Pose2d(-2.0, 31.5,Math.toRadians(90.001)),Math.toRadians(-90), new TranslationalVelConstraint(60), new ProfileAccelConstraint(-38.0, 68.0))
  //              .strafeTo(new Vector2d(-11.75,32))
                .stopAndAdd(outtakeSlide.specimenDropDown()) //TODO move before slide goes to low
                .setTangent(Math.toRadians(135))
                .strafeTo(new Vector2d(-50.5, 60), new TranslationalVelConstraint(65), new ProfileAccelConstraint(-99.0, 99.0))

                .build();



        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(outtakeClaw.close());
        Actions.runBlocking(intakeArm.armDrive());
        Actions.runBlocking(outtakeDump.downPosition());


        drive.pose = startingPose;
        while (opModeInInit()) {

            fieldSide = SIDE.RIGHT;
            startingPose =  initialPoseRightSideSpecimen;
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
                            trajectoryActionRightSideSpecimen,
                            trajectoryActionExtraSpecimen
                    )
            );

        globalRobotData.autonPose = drive.pose;
    }
    public enum SIDE {
        LEFT,
        RIGHT
    }
}
