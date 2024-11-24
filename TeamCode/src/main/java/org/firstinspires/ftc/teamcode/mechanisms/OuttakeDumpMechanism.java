package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
public class OuttakeDumpMechanism {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo dumperServo;

    // Target positions for the servo arm
    public static double DOWN_POSITION = 0.11;  //Axon was 0.23, switched to .11 with the DSSERVO
    public static double DUMP_POSITION = 0.96;
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

        dumperServo.setPosition(DOWN_POSITION);

        if (curDUMPERState != DUMPER_STATES.DUMPER_DOWN_POS) {
            dumperTimer.reset();
            stateDelayTime = 0.5;
            nextDUMPERState = DUMPER_STATES.DUMPER_DOWN_POS;
        }
    }

    // Method to move the dumper to the dump position
    public void DumperPositionDump() {

        dumperServo.setPosition(DUMP_POSITION);
        if (curDUMPERState != DUMPER_STATES.DUMPER_DUMP_POS) {
            dumperTimer.reset();
            stateDelayTime = 0.5;
            nextDUMPERState = DUMPER_STATES.DUMPER_DUMP_POS;
        }
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