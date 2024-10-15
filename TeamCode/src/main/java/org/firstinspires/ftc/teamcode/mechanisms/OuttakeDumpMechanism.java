package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class OuttakeDumpMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo dumperServo;

    // Target positions for the servo arm
    private static final double DOWN_POSITION = 0.25;
    private static final double DUMP_POSITION = 1.0;
    private ElapsedTime dumperTimer = new ElapsedTime();
    public enum DUMPER_STATES{
        DUMPER_DUMP_POS, DUMPER_DOWN_POS
    }

    private DUMPER_STATES curDUMPERState = null;
    private DUMPER_STATES nextDUMPERState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        dumperServo = hwMap.get(Servo.class, "dump_servo");
        this.dumperServo.setDirection(Servo.Direction.FORWARD);
        curDUMPERState = DUMPER_STATES.DUMPER_DOWN_POS;
        nextDUMPERState = DUMPER_STATES.DUMPER_DOWN_POS;
    }

    // Method to move the dumper to the down position
    public void DumperPositionDown() {

        if (curDUMPERState != DUMPER_STATES.DUMPER_DOWN_POS) {
            dumperServo.setPosition(DOWN_POSITION);
            dumperTimer.reset();
            stateDelayTime = 1.0;
            nextDUMPERState = DUMPER_STATES.DUMPER_DOWN_POS;
        }
    }

    // Method to move the dumper to the dump position
    public void DumperPositionDump() {

        dumperServo.setPosition(DUMP_POSITION);
        curDUMPERState = DUMPER_STATES.DUMPER_DUMP_POS;
        nextDUMPERState = DUMPER_STATES.DUMPER_DUMP_POS;
    }
    public DUMPER_STATES getDumperState() {
        if (nextDUMPERState != curDUMPERState) {
            if (dumperTimer.time() > stateDelayTime) {
                curDUMPERState = nextDUMPERState;
            }
        }
        return curDUMPERState;
    }
}