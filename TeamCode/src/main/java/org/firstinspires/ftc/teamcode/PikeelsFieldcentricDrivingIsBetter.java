package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeServoSpinner;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeSlide;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.SensorColor;

@TeleOp(name="PikeelzFieldDriveYay")
public class PikeelsFieldcentricDrivingIsBetter extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //INTAKE SUBSYSTEM
        IntakeServoSpinner frontIntake = new IntakeServoSpinner();

        ClawMechanism specimenClaw = new ClawMechanism();

        //SLIDE SUBSYSTEM
        DualSlideMechanism outtakeSlide =  new DualSlideMechanism();  //Mr. Todone
        IntakeSlide intakeSlide =  new IntakeSlide();  //Mr. Todone
        //ARM SUBSYSTEM
        IntakeArm intakeArmServo = new IntakeArm();

        OuttakeDumpMechanism dumper = new OuttakeDumpMechanism();

        SensorColor intakeBoxColorSensor = new SensorColor();

        boolean halfSpeed = false;
        boolean quarterSpeed = true;

        double intakeSlidePower = 0.0;
        //Intake
        frontIntake.init(hardwareMap);

        //Intake slide
        intakeSlide.init(hardwareMap);

        //Outtake slide
        outtakeSlide.init(hardwareMap);

        //Arm
        intakeArmServo.init(hardwareMap);

        dumper.init(hardwareMap);

        //Claw
        specimenClaw.init(hardwareMap);

        //ColorSensor
        intakeBoxColorSensor.init(hardwareMap);

        PinpointDrive robot = new PinpointDrive(hardwareMap, new Pose2d(9.25, 62, Math.toRadians(90)));
        boolean fieldCentric = false;
        boolean goToTargetAngle;
        double targetAngleDeg = -135.0;
        double targetAngleRad;
        double propAngleGain = 0.5;
        double maxRotate = 0.8;

        waitForStart();
        dumper.DumperPositionDown();
        intakeSlide.slidePositionTransfer();
        intakeArmServo.armPositionDrive();
        specimenClaw.clawOpen();


        while (opModeIsActive()) {
            double driving = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double botHeadingRad = robot.pose.heading.toDouble();
            double botHeading = Math.toDegrees(botHeadingRad);

            if (gamepad1.left_bumper) {
                targetAngleDeg = -135.0;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_down) {
                targetAngleDeg = 180.0;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_right) {
                targetAngleDeg = -90.0;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_left) {
                targetAngleDeg = 90.0;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_up) {
                targetAngleDeg = 0.0;
                goToTargetAngle = true;
            } else {
                goToTargetAngle = false;
            }

            targetAngleRad = Math.toRadians(targetAngleDeg);

            if (goToTargetAngle) {
                double targetAngleDiff = botHeadingRad - targetAngleRad;
                if (targetAngleDiff > Math.PI) {
                    targetAngleDiff = (targetAngleDiff - 2 * (Math.PI));
                } else if (targetAngleDiff < -Math.PI) {
                    targetAngleDiff = (2 * (Math.PI) + targetAngleDiff);
                }
                rotate = targetAngleDiff * propAngleGain;
                rotate = Math.max(Math.min(rotate, maxRotate), -maxRotate);
            }

            double rotX = strafe * Math.cos(-botHeadingRad) - driving * Math.sin(-botHeadingRad);
            double rotY = strafe * Math.sin(-botHeadingRad) + driving * Math.cos(-botHeadingRad);

            if (gamepad1.y) {
                fieldCentric = true;
            } else if (gamepad1.a) {
                fieldCentric = false;
            }

            if (fieldCentric) {
                strafe = rotX;
                driving = rotY;
            }
            robot.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(driving, -strafe), -rotate));

            // INTAKE CONDITIONS
            intakeSlidePower = -(gamepad2.left_stick_y)/2;

            if (intakeSlidePower > 0.05) {
                intakeSlide.extendSlide(intakeSlidePower);
                intakeArmServo.armPositionIntake();
                frontIntake.Intake();
            } else if (intakeSlidePower < -0.05) {
                if (intakeSlide.getSlideState() == IntakeSlide.SLIDE_STATES.SLIDE_INTAKE_POS) {
                    intakeArmServo.armPositionDrive();
                    frontIntake.Stop();
                    intakeSlide.retractSlide(intakeSlidePower);
                } else {
                    intakeSlide.slidePositionTransfer();
                    intakeArmServo.armPositionTransfer();
                    frontIntake.Outtake();
                }
            } else {
                intakeSlide.stopSlide();
                if (frontIntake.getIntakeState() != IntakeServoSpinner.INTAKE_SPINNER_STATES.SPINNER_INTAKING) {
                    frontIntake.Stop();
                }
            }

            if (gamepad2.dpad_left) {
                frontIntake.Intake();
                //       } else if (gamepad2.dpad_down) {
                //           frontIntake.Stop();
            } else if (gamepad2.dpad_right) {
                frontIntake.Outtake();
            }

            if (gamepad1.dpad_left) {
                intakeArmServo.armPositionTransfer();
            } else if (gamepad1.dpad_up) {
                intakeArmServo.armPositionDrive();
            } else if (gamepad1.dpad_right) {
                intakeArmServo.armPositionIntake();
            }

            // SLIDE CONDITIONS

            if (gamepad2.y) {
                outtakeSlide.slidePositionHigh();
            }
            else if (gamepad2.a) {
                outtakeSlide.slidePositionLow();
            }
            else if (gamepad2.x) {
                outtakeSlide.slidePositionSpecimenGrab();
            }
            else if (gamepad2.b) {
                outtakeSlide.slidePositionSpecimenDrop();
            }

            // ARM CONDITIONS


            if (gamepad2.right_bumper) {
                dumper.DumperPositionDump();
            } else {
                dumper.DumperPositionDown();
            }

            robot.updatePoseEstimate();

            telemetry.addData("Intake Arm State: ", intakeArmServo.getARMState());
            telemetry.addData("Intake Slide State: ", intakeSlide.getSlideState());
            telemetry.addData("Dumper State: ", dumper.getDumperState());
            telemetry.addData("Outtake Slide State: ", outtakeSlide.getSlideState());

            telemetry.addData("Intake Slide Power:", intakeSlidePower);
            telemetry.addData("Slide Motor Position:", intakeSlide.getSlideMotorPos());
            telemetry.addData("Slide Motor R Position:", outtakeSlide.getSlideRMotorPos());
            telemetry.addData("Slide Motor L Position:", outtakeSlide.getSlideLMotorPos());

            telemetry.addData("x", robot.pose.position.x);
            telemetry.addData("y", robot.pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(robot.pose.heading.toDouble()));
            //telemetry.addData("Intake Detect Dist(mm):", intakeBoxColorSensor.objectDistance());
        /*NormalizedRGBA objectColor = intakeBoxColorSensor.objectColorRGB();
        telemetry.addData("Best Guess Object Color:", intakeBoxColorSensor.objectColorName());
        telemetry.addLine("Intake Box Object Color RGB:")
                .addData("Red", "%.3f", objectColor.red)
                .addData("Green", "%.3f", objectColor.green)
                .addData("Blue", "%.3f", objectColor.blue); */
            telemetry.update();


            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), robot.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
