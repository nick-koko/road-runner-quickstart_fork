package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.mechanisms.ArmMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeServoSpinner;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeSlide;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.SensorColor;
import org.firstinspires.ftc.teamcode.mechanisms.SlideMechanism;

@TeleOp(name="RunThisPikeelzTeleop2024.com")
// @Disabled
public class RunThisPikeelzTeleop2024 extends OpMode{
    
    private DcMotor frontLeftDriveMotor = null;
    private DcMotor frontRightDriveMotor = null;
    private DcMotor backLeftDriveMotor = null;
    private DcMotor backRightDriveMotor = null;

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
    
    
    @Override
    public void init() {
        
        // Initialize motors
        //CHASSIS
        frontLeftDriveMotor = hardwareMap.get(DcMotor.class, "front_left");
        frontRightDriveMotor = hardwareMap.get(DcMotor.class, "front_right");
        backLeftDriveMotor = hardwareMap.get(DcMotor.class, "back_left");
        backRightDriveMotor = hardwareMap.get(DcMotor.class, "back_right");
        frontLeftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftDriveMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDriveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
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

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        //Goto Intake Position
        //intakeArmServo.armPositionDrive();
    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //Mecanum

        /*TODO :
           We can use Odometry location and remove the Mecanum code if we use the MecanumDrive class. Then the
           drive code would look something like:
               robot.setDrivePowers(PoseVelocity2d(
                Vector2d((-gamepad1.right_stick_y).toDouble(), (-gamepad1.right_stick_x).toDouble()),
                gamepad1.left_stick_x.toDouble()))
           Refer to LocalizationTest.java as an example
         */
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            
            //Max speed stays at 1 or 100% (limited to) 😎👌👌
            double frontLeftSpeed = drive + strafe + rotate;
            double frontRightSpeed = drive - strafe - rotate;
            double backLeftSpeed = drive - (strafe) + rotate;
            double backRightSpeed = drive + (strafe) - rotate;
            double maxSpeed = 1;

            if (Math.abs(frontLeftSpeed) > Math.abs(maxSpeed)) {
                maxSpeed = frontLeftSpeed;
            }
            if (Math.abs(frontRightSpeed) > Math.abs(maxSpeed)) {
                maxSpeed = frontRightSpeed;
            }
            if (Math.abs(maxSpeed) < Math.abs(backLeftSpeed)) {
                maxSpeed = backLeftSpeed;
            }
            if (Math.abs(maxSpeed) < Math.abs(backRightSpeed)) {
                maxSpeed = backRightSpeed;
            }
            //For Half Speed
            if (gamepad1.left_bumper) {
                halfSpeed = true;
                quarterSpeed = false;
            }
            //For Quarter Speed
            if (gamepad1.right_bumper) {
                halfSpeed = false;
                quarterSpeed = true;
            }
            frontLeftSpeed = frontLeftSpeed / Math.abs(maxSpeed);
            frontRightSpeed = frontRightSpeed / Math.abs(maxSpeed);
            backLeftSpeed = backLeftSpeed / Math.abs(maxSpeed);
            backRightSpeed = backRightSpeed / Math.abs(maxSpeed);

        frontLeftDriveMotor.setPower(frontLeftSpeed);
        frontRightDriveMotor.setPower(frontRightSpeed);
        backLeftDriveMotor.setPower(backLeftSpeed);
        backRightDriveMotor.setPower(backRightSpeed);

        // INTAKE CONDITIONS

        if (gamepad2.dpad_left) {
            frontIntake.Intake();
        } else if (gamepad2.dpad_down) {
            frontIntake.Stop();
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

        if (gamepad2.dpad_up) {
            intakeSlide.extendSlide(0.3);
        }
        else if (gamepad2.dpad_down) {
            intakeSlide.retractSlide(-0.5);;
        } else{
            intakeSlide.stopSlide();
        }

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


        updateTelemetry();

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public void updateTelemetry(){
        telemetry.addData("Intake Arm State: ", intakeArmServo.getARMState());
        telemetry.addData("Intake Slide State: ", intakeSlide.getSlideState());
        telemetry.addData("Dumper State: ", dumper.getDumperState());
        telemetry.addData("Outtake Slide State: ", outtakeSlide.getSlideState());

        telemetry.addData("Slide Motor Position:", intakeSlide.getSlideMotorPos());
        telemetry.addData("Slide Motor R Position:", outtakeSlide.getSlideRMotorPos());
        telemetry.addData("Slide Motor L Position:", outtakeSlide.getSlideLMotorPos());

        telemetry.addData("Front right Pos: ", frontRightDriveMotor.getCurrentPosition());
        telemetry.addData("Front left pos: ", frontLeftDriveMotor.getCurrentPosition());
        telemetry.addData("Back right pos: ", backRightDriveMotor.getCurrentPosition());
        telemetry.addData("Back left pos: ", backLeftDriveMotor.getCurrentPosition());
        //telemetry.addData("Intake Detect Dist(mm):", intakeBoxColorSensor.objectDistance());
        /*NormalizedRGBA objectColor = intakeBoxColorSensor.objectColorRGB();
        telemetry.addData("Best Guess Object Color:", intakeBoxColorSensor.objectColorName());
        telemetry.addLine("Intake Box Object Color RGB:")
                .addData("Red", "%.3f", objectColor.red)
                .addData("Green", "%.3f", objectColor.green)
                .addData("Blue", "%.3f", objectColor.blue); */
        telemetry.update();
    }
}
