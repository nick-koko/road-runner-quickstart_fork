package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.ClimbingHooks;
import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeServoSpinner;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeSlide;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;

@TeleOp(name="Teleop_PikeelzFieldDriveYay_2024")
public class PikeelsFieldcentricDrivingIsBetter extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Odometry Intitialization
        Pose2d startingPose = new Pose2d(9.25, 62, Math.toRadians(90));

        if (globalRobotData.hasAutonRun){
            startingPose = globalRobotData.autonPose;
            globalRobotData.hasAutonRun = false;
        }

        PinpointDrive robot = new PinpointDrive(hardwareMap, startingPose);

        //Mechanisim Initialization
        IntakeServoSpinner frontIntake = new IntakeServoSpinner();
        frontIntake.init(hardwareMap);

        ClawMechanism specimenClaw = new ClawMechanism();
        specimenClaw.init(hardwareMap);

        DualSlideMechanism outtakeSlide =  new DualSlideMechanism();  //Mr. Todone
        outtakeSlide.init(hardwareMap);

        ClimbingHooks climbingServo = new ClimbingHooks();
        climbingServo.init(hardwareMap);

        IntakeSlide intakeSlide =  new IntakeSlide();  //Mr. Todone
        intakeSlide.init(hardwareMap);

        IntakeArm intakeArmServo = new IntakeArm();
        intakeArmServo.init(hardwareMap);

        OuttakeDumpMechanism dumper = new OuttakeDumpMechanism();
        dumper.init(hardwareMap);


        double intakeSlidePower = 0.0;
        double intakeSlidePowerFactor;
        boolean fieldCentric = true;
        boolean goToTargetAngle;
        double targetAngleDeg = -135.0;
        double targetAngleRad;
        double propAngleGain = 0.5;
        double minAnglePower = 0.075;
        double maxRotate = 0.8;
        Alliance whichAlliance = Alliance.RED;
        double angleAllianceOffset = -90.0;
        ElapsedTime intakeArmTime = new ElapsedTime();
        double stateDelayTime = 0.0;
        boolean clawopen = true;

        // Wait for the game to start (Display Gyro value while waiting)
        while (opModeInInit()) {

        }
        robot.pose = startingPose;

        dumper.DumperPositionDown();
        intakeSlide.slidePositionTransfer();
        intakeArmServo.armPositionDrive();
        specimenClaw.clawOpen();
        climbingServo.hookPositionDown();


        while (opModeIsActive()) {
            double driving = -gamepad1.right_stick_y;
            double strafe = gamepad1.right_stick_x;
            double rotate = (gamepad1.left_stick_x) * 0.5;

            double botHeadingRad = robot.pose.heading.toDouble();
            double botHeading = Math.toDegrees(botHeadingRad);

            if (gamepad1.back) {
//TODO add reset of pose here
                robot.pose = new Pose2d(9.25, 62, Math.toRadians(90));
            }
            if (gamepad1.left_bumper) {
                targetAngleDeg = -45.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_down) {
                targetAngleDeg = 180.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_right) {
                targetAngleDeg = -90.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_left) {
                targetAngleDeg = 90.0 + angleAllianceOffset;
                goToTargetAngle = true;
            } else if (gamepad1.dpad_up) {
                targetAngleDeg = 0.0 + angleAllianceOffset;
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
                if (rotate > 0.0) {
                    rotate = rotate + minAnglePower;
                } else if (rotate < 0.0) {
                    rotate = rotate - minAnglePower;
                }
                rotate = Math.max(Math.min(rotate, maxRotate), -maxRotate);
            }

            double angleAllianceOffsetRad = Math.toRadians(angleAllianceOffset);

            double rotX = strafe * Math.cos(-(botHeadingRad-angleAllianceOffsetRad)) - driving * Math.sin(-(botHeadingRad-angleAllianceOffsetRad));
            double rotY = strafe * Math.sin(-(botHeadingRad-angleAllianceOffsetRad)) + driving * Math.cos(-(botHeadingRad-angleAllianceOffsetRad));

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
            if(-gamepad2.right_stick_y > 0){
                intakeSlidePowerFactor = 0.20;
            }
            else{
                intakeSlidePowerFactor = 0.4;
            }
            intakeSlidePower = -(gamepad2.right_stick_y * intakeSlidePowerFactor);

            if (intakeSlidePower > 0.05) {
                if ((intakeArmServo.getARMState() == IntakeArm.INTAKE_ARM_STATES.INTAKE_ARM_TRANSFER_POS) ||
                        (intakeArmServo.getARMState() == IntakeArm.INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS)) {
                        intakeArmTime.reset();
                        stateDelayTime = 0.5;
                    }
                if (!gamepad2.right_stick_button) {
                    if (intakeArmTime.time() > stateDelayTime) {
                        if (intakeSlide.getSlideMotorPos() > 380) {
                            intakeArmServo.armPositionFarIntake();
                        } else {
                            intakeArmServo.armPositionIntake();
                        }
                    } else {
                        intakeArmServo.armPositionAbyss();
                    }
                }
                //intakeArmServo.armPositionIntake();
                frontIntake.Intake();
                intakeSlide.extendSlide(intakeSlidePower);
            } else if (intakeSlidePower < -0.05) {
                if (intakeSlide.getSlideState() != IntakeSlide.SLIDE_STATES.SLIDE_INTAKE_POS){
                    intakeArmServo.armPositionTransfer();
                    //frontIntake.Stop();
                    intakeSlide.retractSlide(intakeSlidePower);
                } else {
                    frontIntake.Stop();
                    intakeSlide.slidePositionTransfer();
                    intakeArmServo.armPositionAbyss();
                    //frontIntake.Outtake();
                }
            } else {
                if (intakeSlide.getSlideState() == IntakeSlide.SLIDE_STATES.SLIDE_TRANSFER_POS)
                {
                    intakeSlide.slidePositionTransfer();
                } else {
                    intakeSlide.stopSlide();
                }

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

            // Specimen Claw
            if (gamepad2.left_trigger > 0.05) {
               specimenClaw.clawClose();
            } else if (gamepad2.right_trigger > 0.05) {
                specimenClaw.clawOpen();

            }


            // SLIDE CONDITIONS

            if (gamepad2.y) {
                intakeArmServo.armPositionDrive();
                outtakeSlide.slidePositionHigh();
            }
            else if (gamepad2.a) {
                if (outtakeSlide.getSlideState() == DualSlideMechanism.SLIDE_STATES.SLIDE_SPECIMENDROP_POS){
                    specimenClaw.clawDropPosition();
                }
                intakeArmServo.armPositionDrive();
                outtakeSlide.slidePositionLow();
            }
            else if (gamepad2.x) {
                intakeArmServo.armPositionDrive();
                outtakeSlide.slidePositionMiddle();
            }
            else if (gamepad2.b) {
                intakeArmServo.armPositionDrive();
                outtakeSlide.slidePositionSpecimenDrop();
            }
            else if (gamepad2.start) {
                intakeArmServo.armPositionDrive();
                specimenClaw.clawClose();
                outtakeSlide.slidePositionClimb();
                climbingServo.hookPositionHigh();
            } else if (gamepad2.back) {
                climbingServo.hookPositionHigh();
                outtakeSlide.slidePositionEndHang();
            }
            else {
                if ((outtakeSlide.getSlideState() == DualSlideMechanism.SLIDE_STATES.SLIDE_LOW_POS) &&
                        (outtakeSlide.getNextSlideState() == DualSlideMechanism.SLIDE_STATES.SLIDE_LOW_POS)) {
                    outtakeSlide.stopSlide();
                }
            }
            // ARM CONDITIONS


            if (gamepad2.right_bumper) {
                if (intakeArmServo.getARMState() == IntakeArm.INTAKE_ARM_STATES.INTAKE_ARM_TRANSFER_POS) {
                    intakeArmServo.armPositionDrive();
                }
                dumper.DumperPositionDump();
            } else {
                dumper.DumperPositionDown();
            }
            if (gamepad2.left_bumper) {
                frontIntake.Outtake();
            } else if (frontIntake.getIntakeState() != IntakeServoSpinner.INTAKE_SPINNER_STATES.SPINNER_INTAKING){
                frontIntake.Stop();
            }
            robot.updatePoseEstimate();

            telemetry.addData("IsFieldCentric?", fieldCentric);
            telemetry.addData("Which Alliance?", whichAlliance);

            telemetry.addData("Intake Arm State: ", intakeArmServo.getARMState());
            telemetry.addData("Intake Slide State: ", intakeSlide.getSlideState());
            telemetry.addData("Dumper State: ", dumper.getDumperState());
            telemetry.addData("Outtake Slide State: ", outtakeSlide.getSlideState());

            telemetry.addData("Intake Slide Power:", intakeSlidePower);
            telemetry.addData("Slide Motor Position:", intakeSlide.getSlideMotorPos());
            telemetry.addData("Slide Motor R Position:", outtakeSlide.getSlideRMotorPos());
            telemetry.addData("Slide Motor L Position:", outtakeSlide.getSlideLMotorPos());
            telemetry.addData("Left Trigger: ", gamepad2.left_trigger);
            telemetry.addData("Right Trigger: ", gamepad2.right_trigger);
            telemetry.addData("Claw State:    ",  specimenClaw.getClawState());

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
    public enum Alliance {
        RED,
        BLUE
    }

}
