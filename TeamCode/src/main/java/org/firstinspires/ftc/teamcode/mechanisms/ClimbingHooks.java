package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class ClimbingHooks {
    // Assuming some motor control library is used, e.g., FTC SDK, but this can be customized
    private Servo climbingServo;

    // Target positions for the servo arm
    public static double DOWN_POSITION = 0.067; //above group... will kill power .245
    public static double HIGH_POSITION = 0.615; //above group... will kill power .245

    private ElapsedTime armTimer = new ElapsedTime();
    public enum CLIMBING_HOOK_STATE{
    CLIMBING_HOOKS_HIGH_POS, CLIMBING_HOOKS_DOWN_POSITION
    }

    private CLIMBING_HOOK_STATE currClimbHookState = null;
    private CLIMBING_HOOK_STATE nextClimbHookState = null;

    double stateDelayTime = 0;

    public void init(HardwareMap hwMap) {

        climbingServo = hwMap.get(Servo.class, "climbing_hook_servo");
        this.climbingServo.setDirection(Servo.Direction.FORWARD);
        currClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_DOWN_POSITION;
        nextClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_HIGH_POS;
    }

    // Method to move the hook to high position
    public void hookPositionHigh() {
            climbingServo.setPosition(HIGH_POSITION);
            currClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_HIGH_POS;
            nextClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_HIGH_POS;
    }

    // Method to move the hook to down position
    public void hookPositionDown() {
        climbingServo.setPosition(DOWN_POSITION);
        currClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_DOWN_POSITION;
        nextClimbHookState = CLIMBING_HOOK_STATE.CLIMBING_HOOKS_DOWN_POSITION;
    }

    public CLIMBING_HOOK_STATE getCurrClimbHookState() {
        return currClimbHookState;
    }
}