package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeArm {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo intakeArmServo;

    // Target positions for the servo arm
    private static final double INTAKE_POSITION = 0.5; //above group... will kill power
    private static final double DRIVE_POSITION = 0.75; //perpindicuar
    private static final double DUMP_POSITION = 1.0; //servo towards slides
    private ElapsedTime armTimer = new ElapsedTime();
    public enum INTAKE_ARM_STATES{
        INTAKE_ARM_INTAKE_POS, INTAKE_ARM_DRIVE_POS, INTAKE_ARM_DUMP_POS
    }

    private ARM_STATES curARMState = null;
    private ARM_STATES nextARMState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        intakeArmServo = hwMap.get(Servo.class, "intake_arm_servo");
        this.intakeArmServo.setDirection(Servo.Direction.FORWARD);
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
        curARMState = INTAKE_ARM_STATES.INTAKE_ARM_DRIVE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionIntake() {
            intakeArmServo.setPosition(INTAKE_POSITION);
            curARMState = INTAKE_ARM_STATES.ARM_INTAKE_POS;
            nextARMState = INTAKE_ARM_STATES.ARM_INTAKE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionDrive() {
        intakeArmServo.setPosition(DRIVE_POSITION);
        curARMState = INTAKE_ARM_STATES.ARM_DRIVE_POS;
        nextARMState = INTAKE_ARM_STATES.ARM_DRIVE_POS;
    }

    // Method to move the arm to the intake position
    public void armPositionDump() {
        intakeArmServo.setPosition(DUMP_POSITION);
        curARMState = INTAKE_ARM_STATES.ARM_DUMP_POS;
        nextARMState = INTAKE_ARM_STATES.ARM_DUMP_POS;
    }

    public ARM_STATES getARMState() {
        return curARMState;
    }
}