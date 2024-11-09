package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ClawMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private ServoImplEx clawServo;

    // Target positions for the servo claw
    private static final double OPEN_POSITION = 0.6;
    private static final double DROP_POSITION = 0.365;
    private static final double CLOSE_POSITION = 0.34;
    private ElapsedTime clawTimer = new ElapsedTime();

    public enum CLAW_STATES {
        CLAW_OPEN_POS, CLAW_CLOSE_POS, CLAW_DROP_POS
    }

    private CLAW_STATES curClawState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        clawServo = hwMap.get(ServoImplEx.class, "claw_servo");
        this.clawServo.setDirection(Servo.Direction.REVERSE);
        curClawState = CLAW_STATES.CLAW_OPEN_POS;
    }

    // Method to move the claw to the open position
    public void clawOpen() {
        clawServo.setPosition(OPEN_POSITION);
        curClawState = CLAW_STATES.CLAW_OPEN_POS;
    }

    public void clawDropPosition() {
        clawServo.setPosition(DROP_POSITION);
        curClawState = CLAW_STATES.CLAW_DROP_POS;
    }

    // Method to move the claw to the close position
    public void clawClose() {
        clawServo.setPosition(CLOSE_POSITION);
        curClawState = CLAW_STATES.CLAW_CLOSE_POS;
    }

    public CLAW_STATES getClawState() {
        return curClawState;
    }

    public void clawOff() {
        clawServo.setPwmDisable();

    }
}