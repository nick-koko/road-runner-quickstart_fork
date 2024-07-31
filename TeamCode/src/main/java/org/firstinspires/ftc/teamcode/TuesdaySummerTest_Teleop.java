package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name="TuesdaySummerTest_Teleop")
// @Disabled
public class TuesdaySummerTest_Teleop extends OpMode{
    
    /**
     * Chassis Subsystem
     * 4 DC Motors
     * 1 Gyro - IMU
     * 1 Camera
     */
    private DcMotor frontLeftDriveMotor = null;
    private DcMotor frontRightDriveMotor = null;
    private DcMotor backLeftDriveMotor = null;
    private DcMotor backRightDriveMotor = null;
    private IMU             imu         = null;      // Control/Expansion Hub IMU
    
    //INTAKE SUBSYSTEM
    private DcMotor intakeMotor = null;
    private CRServo intakeServo = null;

    private boolean moveToOtherPos = false;
    
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
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        intakeServo = hardwareMap.get(CRServo.class, "up_servo");

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // This sample expects the IMU to be in a REV Hub and named "imu".
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();
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
        //runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double intakePower = gamepad2.right_trigger - gamepad2.left_trigger;

        if(gamepad1.dpad_up){
            moveToOtherPos = true;
        }
        else if(gamepad1.dpad_down){
            moveToOtherPos = true;
        }
        else {
            moveToOtherPos = false;
        }

        //Mecanum
            double drive = -gamepad1.right_stick_y;
            double strafe = -gamepad1.right_stick_x;
            double rotate = (gamepad1.right_trigger - gamepad1.left_trigger);
            
            if(drive < 0){
                drive = drive * 0.5;
            }
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

        updateTelemetry();
        //SLIDE STATE MACHINE -> This will also change states of other subsystems


    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public void updateTelemetry(){
        telemetry.addData("Front right Pos: ", frontRightDriveMotor.getCurrentPosition());
        telemetry.addData("Front left pos: ", frontLeftDriveMotor.getCurrentPosition());
        telemetry.addData("Back right pos: ", backRightDriveMotor.getCurrentPosition());
        telemetry.addData("Back left pos: ", backLeftDriveMotor.getCurrentPosition());
    }
}
