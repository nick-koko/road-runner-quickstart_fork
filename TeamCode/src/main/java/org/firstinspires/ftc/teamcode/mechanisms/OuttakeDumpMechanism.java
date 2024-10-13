package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class OuttakeDumpMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo armServo;

    // Target positions for the servo arm
    private static final double INTAKE_POSITION = 0.5;
    private static final double DRIVE_POSITION = 0.25;
    private static final double DUMP_POSITION = 1.0;
    private ElapsedTime armTimer = new ElapsedTime();
    public enum ARM_STATES{
        ARM_INTAKE_POS, ARM_LOW_POS, ARM_DRIVE_POS, ARM_MIDDLE_POS, ARM_HIGH_POS, ARM_DUMP_POS
    }

    private ARM_STATES curARMState = null;
    private ARM_STATES nextARMState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        armServo = hwMap.get(Servo.class, "dump_servo");
        this.armServo.setDirection(Servo.Direction.FORWARD);
        curARMState = ARM_STATES.ARM_LOW_POS;
        nextARMState = ARM_STATES.ARM_LOW_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionIntake() {

        if (curARMState != ARM_STATES.ARM_INTAKE_POS) {
            armServo.setPosition(INTAKE_POSITION);
            armTimer.reset();
            stateDelayTime = 1.0;
            nextARMState = ARM_STATES.ARM_INTAKE_POS;
        }
    }

    // Method to move the arm to the intake position
    public void armPositionDrive() {

        armServo.setPosition(DRIVE_POSITION);
        curARMState = ARM_STATES.ARM_DRIVE_POS;
        nextARMState = ARM_STATES.ARM_DRIVE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionDump() {

        armServo.setPosition(DUMP_POSITION);
        curARMState = ARM_STATES.ARM_DUMP_POS;
        nextARMState = ARM_STATES.ARM_DUMP_POS;
    }

    public ARM_STATES getARMState() {
        if (nextARMState != curARMState) {
            if (armTimer.time() > stateDelayTime) {
                curARMState = nextARMState;
            }
        }
        return curARMState;
    }
}