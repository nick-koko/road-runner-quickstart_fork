package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DualSlideMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    DcMotor slideMotorL;
    DcMotor slideMotorR;
    // Target positions for the slide mechanism
    private static final int LOW_POSITION_LEFT = 0;
    private static final int SPECIMENDROP_POSITION_LEFT = 1000;
    private static final int HIGH_POSITION_LEFT = 2300;
    private static final int SPECIMENGRAB_POSITION_LEFT = 100;
    private static final int LOW_POSITION_RIGHT = 0;
    private static final int SPECIMENDROP_POSITION_RIGHT = 1000;
    private static final int HIGH_POSITION_RIGHT = 2300;
    private static final int SPECIMENGRAB_POSITION_RIGHT = 100;
    private static final int CLIMB_POSITION_LEFT = 800;
    private static final int CLIMB_POSITION_RIGHT = 800;
//TODO
    public enum SLIDE_STATES{
        SLIDE_INTAKE_POS, SLIDE_LOW_POS, SLIDE_SPECIMENGRAB_POS, SLIDE_SPECIMENDROP_POS, SLIDE_HIGH_POS, SLIDE_CLIMB_POS
    }

    private SLIDE_STATES curSlideState = null;
    private SLIDE_STATES nextSlideState = null;

    public void init(HardwareMap hwMap) {
//stop don't rock to me, boulder pebble wannabe like oh, concrete.
        slideMotorL = hwMap.get(DcMotor.class, "slide_motor_left");
        this.slideMotorL.setDirection(DcMotor.Direction.FORWARD);
        this.slideMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorR = hwMap.get(DcMotor.class, "slide_motor_right"); //Mr. Todone-->😎👌👌kitrbywudywgdddddddddddddd
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
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Check if we are close to reaching the maximum slide extension (TOP_Position)
        // If not we haven't yet, then set the power to a positive value
        if (this.slideMotorR.getCurrentPosition() < (HIGH_POSITION_RIGHT - 100)) {
            this.slideMotorR.setPower(0.4);
            this.slideMotorL.setPower(0.4);
        }
        // If we are close to reaching the max slide extension (TOP_POSITION - 100)
        // then stop the slide by setting power to 0 to make sure to not break anything
        else {
            this.slideMotorR.setPower(0.0);
            this.slideMotorL.setPower(0.0);
        }

    }

    // Method to retract the slide 😎👌👌👌👌👌👌
    public void retractSlide() {
        // Set the motor power to a negative value to retract the slide
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Check if we are close to reaching the zero point while retracting
        // 0 is the farthest we can go, but we want to stop before that to not break anything
        // If not we haven't yet, then set the power to a negative value
        if (this.slideMotorL.getCurrentPosition() > (100)) {
            this.slideMotorR.setPower(-0.4);
            this.slideMotorL.setPower(-0.4);
        }
        // If we are close to reaching the zero point (100)
        // then stop the slide by setting power to 0 to make sure to not break anything
        else {
            this.slideMotorR.setPower(0.0);
            this.slideMotorL.setPower(0.0);
        }

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

    public void slidePositionClimb() {
        nextSlideState = SLIDE_STATES.SLIDE_CLIMB_POS;
        moveToPosition(CLIMB_POSITION_LEFT, CLIMB_POSITION_RIGHT);
    }

    // Method to move the slide to the specimen drop position
    public void slidePositionSpecimenDrop() {

        nextSlideState = SLIDE_STATES.SLIDE_SPECIMENDROP_POS;
        moveToPosition(SPECIMENDROP_POSITION_LEFT, SPECIMENDROP_POSITION_RIGHT);
    }

    // Method to move the slide to the high position
    public void slidePositionHigh() {

        nextSlideState = SLIDE_STATES.SLIDE_HIGH_POS;
        moveToPosition(HIGH_POSITION_LEFT, HIGH_POSITION_RIGHT);
    }

    // Method to move the slide to the drive position
    public void slidePositionSpecimenGrab() {

        nextSlideState = SLIDE_STATES.SLIDE_SPECIMENGRAB_POS;
        moveToPosition(SPECIMENGRAB_POSITION_LEFT, SPECIMENGRAB_POSITION_RIGHT);
    }

    // Private method to move the slide to a specific position
    private void moveToPosition(int leftPosition, int rightPosition) {
        this.slideMotorL.setTargetPosition(leftPosition);
        this.slideMotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotorL.setPower(1.0);
        this.slideMotorR.setTargetPosition(rightPosition);
        this.slideMotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slideMotorR.setPower(1.0);
        
        // Wait until the motor reaches the target position
        /*while (this.slideMotor.isBusy()) {
            // Optional: Add any telemetry or logging here
        }*/
        
        // Stop the motor once the target position is reached
        // this.slideMotor.setPower(0);
    }

    public SLIDE_STATES getSlideState() {
        int slidePos = this.slideMotorL.getCurrentPosition();
        if (Math.abs(slidePos - SPECIMENDROP_POSITION_LEFT) < (15)) {
            curSlideState = SLIDE_STATES.SLIDE_SPECIMENDROP_POS;
        }
        else if(slidePos > (HIGH_POSITION_LEFT - 20)) {
            curSlideState = SLIDE_STATES.SLIDE_HIGH_POS;
        } else if(slidePos < (LOW_POSITION_LEFT + 20)){
            curSlideState = SLIDE_STATES.SLIDE_LOW_POS;
        } else if(Math.abs(slidePos - SPECIMENGRAB_POSITION_LEFT) < (15)){
            curSlideState = SLIDE_STATES.SLIDE_SPECIMENGRAB_POS;
        }
        return curSlideState;
    }





    public SLIDE_STATES getNextSlideState() {
        return nextSlideState;
    }
    public int getSlideLMotorPos() {
        return this.slideMotorL.getCurrentPosition();
    }
    public int getSlideRMotorPos() {
        return this.slideMotorR.getCurrentPosition();
    }
}