package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ClawMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo clawServo;

    // Target positions for the servo arm
    private static final double OPEN_POSITION = 0.5;
    private static final double CLOSE_POSITION = 0.25;
    private ElapsedTime clawTimer = new ElapsedTime();
    public enum CLAW_STATES{
        CLAW_OPEN_POS, CLAW_CLOSE_POS
    }

    private CLAW_STATES openClawState = null;
    private CLAW_STATES closeClawState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        clawServo = hwMap.get(Servo.class, "claw_servo");
        this.clawServo.setDirection(Servo.Direction.FORWARD);
        openClawState = CLAW_STATES.CLAW_OPEN_POS;
        closeClawState = CLAW_STATES.CLAW_CLOSE_POS;
    }

    // Method to move the claw to the open position
    public void clawPositionopen() {

        if (openClawState != CLAW_STATES.CLAW_OPEN_POS) {
            clawServo.setPosition(OPEN_POSITION);
            clawTimer.reset();
            stateDelayTime = 1.0;
            openClawState = CLAW_STATES.CLAW_OPEN_POS;
        }
    }

    // Method to move the claw to the close position
    public void clawPositionclose() {

        clawServo.setPosition(CLOSE_POSITION);
        closeClawState = CLAW_STATES.CLAW_CLOSE_POS;
        closeClawState = CLAW_STATES.CLAW_CLOSE_POS;
    }



