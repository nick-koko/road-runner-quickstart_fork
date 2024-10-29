package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeArm {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo intakeArmServo;

    // Target positions for the servo arm
    private static final double INTAKE_POSITION = 0.25; //above group... will kill power
    private static final double DRIVE_POSITION = 0.8; //perpindicuar
    private static final double TRANSFER_POSITION = 0.88; //servo towards slides
    private static final double ABYSS_POSITION = 0.4; //servo towards slides

    private ElapsedTime armTimer = new ElapsedTime();
    public enum INTAKE_ARM_STATES{
        INTAKE_ARM_INTAKE_POS, INTAKE_ARM_DRIVE_POS, INTAKE_ARM_TRANSFER_POS, INTAKE_ARM_ABYSS_POS
    }

    private INTAKE_ARM_STATES curARMState = null;
    private INTAKE_ARM_STATES nextARMState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        intakeArmServo = hwMap.get(Servo.class, "intake_arm_servo");
        this.intakeArmServo.setDirection(Servo.Direction.FORWARD);
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
        nextARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionIntake() {
            intakeArmServo.setPosition(INTAKE_POSITION);
            curARMState = INTAKE_ARM_STATES.INTAKE_ARM_INTAKE_POS;
            nextARMState = INTAKE_ARM_STATES.INTAKE_ARM_INTAKE_POS;
    }

    public void armPositionAbyss() {
        intakeArmServo.setPosition(ABYSS_POSITION);
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_ABYSS_POS;
        nextARMState = INTAKE_ARM_STATES.INTAKE_ARM_ABYSS_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionDrive() {
        intakeArmServo.setPosition(DRIVE_POSITION);
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
        nextARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionTransfer() {
        intakeArmServo.setPosition(TRANSFER_POSITION);
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_TRANSFER_POS;
        nextARMState = INTAKE_ARM_STATES.INTAKE_ARM_TRANSFER_POS;
    }

    public INTAKE_ARM_STATES getARMState() {
        return curARMState;
    }
}