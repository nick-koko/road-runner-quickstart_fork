package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private DcMotor slideMotor;

    // Target positions for the slide mechanism
    private static final int LOW_POSITION = 0;
    private static final int MIDDLE_POSITION = 1200;
    private static final int HIGH_POSITION = 2000;
    private static final int DRIVE_POSITION = 800;

    public enum SLIDE_STATES{
        SLIDE_INTAKE_POS, SLIDE_LOW_POS, SLIDE_DRIVE_POS, SLIDE_MIDDLE_POS, SLIDE_HIGH_POS
    }

    private SLIDE_STATES curSlideState = null;
    private SLIDE_STATES nextSlideState = null;

    public void init(HardwareMap hwMap) {

        slideMotor = hwMap.get(DcMotor.class, "slide_motor");
        this.slideMotor.setDirection(DcMotor.Direction.REVERSE);
        this.slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        curSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        nextSlideState = SLIDE_STATES.SLIDE_LOW_POS;
    }


    // Method to extend the slide
    public void extendSlide() {
        // Set the motor power to a positive value to extend the slide
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotor.setPower(0.4);
    }

    // Method to retract the slide
    public void retractSlide() {
        // Set the motor power to a negative value to retract the slide
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotor.setPower(-0.4);
    }

    // Method to stop the slide
    public void stopSlide() {
        // Set the motor power to zero to stop the slide
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotor.setPower(0);
    }

    // Method to move the slide to the low position
    public void slidePositionLow() {
        nextSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        moveToPosition(LOW_POSITION);
    }

    // Method to move the slide to the middle position
    public void slidePositionMiddle() {

        nextSlideState = SLIDE_STATES.SLIDE_MIDDLE_POS;
        moveToPosition(MIDDLE_POSITION);
    }

    // Method to move the slide to the high position
    public void slidePositionHigh() {

        nextSlideState = SLIDE_STATES.SLIDE_HIGH_POS;
        moveToPosition(HIGH_POSITION);
    }

    // Method to move the slide to the drive position
    public void slidePositionDrive() {

        nextSlideState = SLIDE_STATES.SLIDE_DRIVE_POS;
        moveToPosition(DRIVE_POSITION);
    }

    // Private method to move the slide to a specific position
    private void moveToPosition(int position) {
        this.slideMotor.setTargetPosition(position);
        this.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotor.setPower(0.4);
        
        // Wait until the motor reaches the target position
        /*while (this.slideMotor.isBusy()) {
            // Optional: Add any telemetry or logging here
        }*/
        
        // Stop the motor once the target position is reached
        // this.slideMotor.setPower(0);
    }

    public SLIDE_STATES getSlideState() {
        if (this.slideMotor.getCurrentPosition() < (DRIVE_POSITION - 100)) {
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