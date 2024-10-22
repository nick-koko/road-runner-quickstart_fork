package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSlide {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private DcMotor slideMotor;

    // Target positions for the slide mechanism
    private static final int AUTON_POSITION = 100;
    private static final int TOP_POSITION = 500;
    private static final int STARTING_POSITION = 0;
    private static final int TRANSFER_POSITION = 10;
    public enum SLIDE_STATES{
        SLIDE_INTAKE_POS, SLIDE_STARTING_POS, SLIDE_AUTON_POS, SLIDE_TRANSFER_POS, SLIDE_HIGH_POS
    }

    private SLIDE_STATES curSlideState = null;
    private SLIDE_STATES nextSlideState = null;

    public void init(HardwareMap hwMap) {

        slideMotor = hwMap.get(DcMotor.class, "intake_slide_motor");
        this.slideMotor.setDirection(DcMotor.Direction.FORWARD);
        this.slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        curSlideState = SLIDE_STATES.SLIDE_STARTING_POS;
        nextSlideState = SLIDE_STATES.SLIDE_STARTING_POS;
    }


    // Method to extend the slide
    public void extendSlide(double power) {
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Check if we are close to reaching the maximum slide extension (TOP_Position)
        // If not we haven't yet, then set the power to a positive value
        if (this.slideMotor.getCurrentPosition() < (TOP_POSITION - 100)) {
            this.slideMotor.setPower(power);
        }
        // If we are close to reaching the max slide extension (TOP_POSITION - 100)
        // then stop the slide by setting power to 0 to make sure to not break anything
        else {
            this.slideMotor.setPower(0.0);
        }
    }

    // Method to retract the slide
    public void retractSlide(double power) {
        // Set the motor power to a negative value to retract the slide
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Check if we are close to reaching the zero point while retracting
        // 0 is the farthest we can go, but we want to stop before that to not break anything
        // If not we haven't yet, then set the power to a negative value
        if (this.slideMotor.getCurrentPosition() > (0)) {
            this.slideMotor.setPower(power);
        }
        // If we are close to reaching the zero point
        // then stop the slide by setting power to 0 to make sure to not break anything
        else {
            this.slideMotor.setPower(0.0);
        }
    }

    // Method to stop the slide
    public void stopSlide() {
        // Set the motor power to zero to stop the slide
        this.slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotor.setPower(0);
    }

    // Method to move the slide to the middle position
    public void slidePositionTransfer() {

        nextSlideState = SLIDE_STATES.SLIDE_TRANSFER_POS;
        moveToPosition(TRANSFER_POSITION);
    }

    // Method to move the slide to the high position
    public void slidePositionHigh() {

        nextSlideState = SLIDE_STATES.SLIDE_HIGH_POS;
        moveToPosition(TOP_POSITION);
    }


    // Private method to move the slide to a specific position
    private void moveToPosition(int position) {
        this.slideMotor.setTargetPosition(position);
        this.slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotor.setPower(0.8);

    }

    public SLIDE_STATES getSlideState() {
        int slidePos = this.slideMotor.getCurrentPosition();
        if (Math.abs(slidePos - TRANSFER_POSITION) < (10)) {
            curSlideState = SLIDE_STATES.SLIDE_TRANSFER_POS;
        }
        else if(slidePos > TRANSFER_POSITION) {
            curSlideState = SLIDE_STATES.SLIDE_INTAKE_POS;
        } else {
            curSlideState = SLIDE_STATES.SLIDE_STARTING_POS;
        }
        return curSlideState;
    }

    public int getSlideMotorPos() {
        return this.slideMotor.getCurrentPosition();
    }
}