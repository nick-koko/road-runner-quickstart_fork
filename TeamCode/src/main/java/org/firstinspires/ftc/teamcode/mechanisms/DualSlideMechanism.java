package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DualSlideMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private DcMotor slideMotorL;
    private DcMotor slideMotorR;
    // Target positions for the slide mechanism
    private static final int LOW_POSITION_LEFT = 0;
    private static final int MIDDLE_POSITION_LEFT = 1200;
    private static final int HIGH_POSITION_LEFT = 2000;
    private static final int DRIVE_POSITION_LEFT = 800;
    private static final int LOW_POSITION_RIGHT = -0;
    private static final int MIDDLE_POSITION_RIGHT = -1200;
    private static final int HIGH_POSITION_RIGHT = -2000;
    private static final int DRIVE_POSITION_RIGHT = -800;
//TODO
    public enum SLIDE_STATES{
        SLIDE_INTAKE_POS, SLIDE_LOW_POS, SLIDE_DRIVE_POS, SLIDE_MIDDLE_POS, SLIDE_HIGH_POS
    }

    private SLIDE_STATES curSlideState = null;
    private SLIDE_STATES nextSlideState = null;

    public void init(HardwareMap hwMap) {
//stop don't rock to me, boulder pebble wannabe like oh, concrete.
        slideMotorL = hwMap.get(DcMotor.class, "slide_motor_left");
        this.slideMotorL.setDirection(DcMotor.Direction.REVERSE);
        this.slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorR = hwMap.get(DcMotor.class, "slide_motor_right"); //Mr. Todone-->😎👌👌
        this.slideMotorR.setDirection(DcMotor.Direction.REVERSE);
        this.slideMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        curSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        nextSlideState = SLIDE_STATES.SLIDE_LOW_POS;
    }


    // Method to extend the slide
    public void extendSlide() {
        // Set the motor power to a positive value to extend the slide
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorL.setPower(0.4);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorR.setPower(0.4);
    }

    // Method to retract the slide 😎👌👌👌👌👌👌
    public void retractSlide() {
        // Set the motor power to a negative value to retract the slide
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorL.setPower(-0.4);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorR.setPower(-0.4);
    }

    // Method to stop the slide
    public void stopSlide() {
        // Set the motor power to zero to stop the slide
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorL.setPower(0);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorR.setPower(0);
    }

    // Method to move the slide to the low position
    public void slidePositionLow() {
        nextSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        moveToPosition(LOW_POSITION_LEFT, LOW_POSITION_RIGHT);
    }

    // Method to move the slide to the middle position
    public void slidePositionMiddle() {

        nextSlideState = SLIDE_STATES.SLIDE_MIDDLE_POS;
        moveToPosition(MIDDLE_POSITION_LEFT, MIDDLE_POSITION_RIGHT);
    }

    // Method to move the slide to the high position
    public void slidePositionHigh() {

        nextSlideState = SLIDE_STATES.SLIDE_HIGH_POS;
        moveToPosition(HIGH_POSITION_LEFT, HIGH_POSITION_RIGHT);
    }

    // Method to move the slide to the drive position
    public void slidePositionDrive() {

        nextSlideState = SLIDE_STATES.SLIDE_DRIVE_POS;
        moveToPosition(DRIVE_POSITION_LEFT, DRIVE_POSITION_RIGHT);
    }

    // Private method to move the slide to a specific position
    private void moveToPosition(int leftPosition, int rightPosition) {
        this.slideMotorL.setTargetPosition(leftPosition);
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotorL.setPower(0.4);
        this.slideMotorR.setTargetPosition(rightPosition);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotorR.setPower(0.4);
        
        // Wait until the motor reaches the target position
        /*while (this.slideMotor.isBusy()) {
            // Optional: Add any telemetry or logging here
        }*/
        
        // Stop the motor once the target position is reached
        // this.slideMotor.setPower(0);
    }

    public SLIDE_STATES getSlideState() {
        if (this.slideMotorL.getCurrentPosition() < (DRIVE_POSITION_LEFT - 100)) {
            curSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        }
        else {
            curSlideState = SLIDE_STATES.SLIDE_DRIVE_POS;
        }
        return curSlideState;
    }

    public SLIDE_STATES getNextSlideState() {
        return nextSlideState;
    }
}