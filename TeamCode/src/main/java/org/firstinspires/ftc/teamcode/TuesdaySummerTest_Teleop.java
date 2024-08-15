package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.mechanisms.ArmMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeMotorSpinner;
import org.firstinspires.ftc.teamcode.mechanisms.SensorColor;
import org.firstinspires.ftc.teamcode.mechanisms.SlideMechanism;

@TeleOp(name="TuesdaySummerTest_Teleop")
// @Disabled
public class TuesdaySummerTest_Teleop extends OpMode{
    
    private DcMotor frontLeftDriveMotor = null;
    private DcMotor frontRightDriveMotor = null;
    private DcMotor backLeftDriveMotor = null;
    private DcMotor backRightDriveMotor = null;

    //INTAKE SUBSYSTEM
    IntakeMotorSpinner frontIntake = new IntakeMotorSpinner();
    //SLIDE SUBSYSTEM
    SlideMechanism slide =  new SlideMechanism();
    //ARM SUBSYSTEM
    ArmMechanism intakeArmServo = new ArmMechanism();

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

        //Slide
        slide.init(hardwareMap);

        //Arm
        intakeArmServo.init(hardwareMap);

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
        intakeArmServo.armPositionIntake();
        slide.slidePositionLow();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //Mecanum
            double drive = -gamepad1.right_stick_y;
            double strafe = gamepad1.right_stick_x;
            double rotate = gamepad1.left_stick_x;
            
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
        if (gamepad1.dpad_up) {
            // Call the intake method
            frontIntake.Intake();
        }
        else if (gamepad1.dpad_down) {
            frontIntake.Stop();
        }

        if (gamepad2.dpad_left) {
            frontIntake.Intake();
        }
        else if (gamepad2.dpad_right) {
            frontIntake.Stop();
        }

        // SLIDE CONDITIONS
        if (gamepad1.y || gamepad2.y) {
            // Go to the high position
            slide.slidePositionHigh();
        }
        else if(gamepad1.a || gamepad2.a){
            // Go to low position
            if ((intakeArmServo.getARMState() == ArmMechanism.ARM_STATES.ARM_LOW_POS) || (intakeArmServo.getARMState() == ArmMechanism.ARM_STATES.ARM_INTAKE_POS)) {
                slide.slidePositionLow();
            }
        }
        else if(gamepad1.b || gamepad2.b){
            // Go to middle position
            slide.slidePositionMiddle();
        }

        if (gamepad2.dpad_up) {
            slide.slidePositionDrive();
            while (slide.getSlideState() == SlideMechanism.SLIDE_STATES.SLIDE_LOW_POS) {

            }
            intakeArmServo.armPositionDrive();
        }
        else if (gamepad2.dpad_down) {
            intakeArmServo.armPositionIntake();
            while ((intakeArmServo.getARMState() != ArmMechanism.ARM_STATES.ARM_INTAKE_POS) && (intakeArmServo.getARMState() != ArmMechanism.ARM_STATES.ARM_LOW_POS)) {

            }
            slide.slidePositionLow();
        }

        // ARM CONDITIONS
        if (gamepad2.left_bumper || gamepad2.right_bumper) {
            // Go to dumping position
            if(slide.getSlideState() != SlideMechanism.SLIDE_STATES.SLIDE_LOW_POS) {
                intakeArmServo.armPositionDump();
            }
        }
        else {
            // Go to drive position
            if((slide.getSlideState() != SlideMechanism.SLIDE_STATES.SLIDE_LOW_POS) && (slide.getNextSlideState() != SlideMechanism.SLIDE_STATES.SLIDE_LOW_POS)) {
                intakeArmServo.armPositionDrive();
            }
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
        telemetry.addData("Slide State: ", slide.getSlideState());
        telemetry.addData("Arm State: ", intakeArmServo.getARMState());
        telemetry.addData("Intake Detect Dist(mm):", intakeBoxColorSensor.objectDistance());

        NormalizedRGBA objectColor = intakeBoxColorSensor.objectColorRGB();
        telemetry.addLine("Intake Box Object Color RGB:")
                .addData("Red", "%.3f", objectColor.red)
                .addData("Green", "%.3f", objectColor.green)
                .addData("Blue", "%.3f", objectColor.blue);

        telemetry.addData("Best Guess Object Color:", intakeBoxColorSensor.objectColorName());
        telemetry.addData("Front right Pos: ", frontRightDriveMotor.getCurrentPosition());
        telemetry.addData("Front left pos: ", frontLeftDriveMotor.getCurrentPosition());
        telemetry.addData("Back right pos: ", backRightDriveMotor.getCurrentPosition());
        telemetry.addData("Back left pos: ", backLeftDriveMotor.getCurrentPosition());
        telemetry.update();

    }
}
