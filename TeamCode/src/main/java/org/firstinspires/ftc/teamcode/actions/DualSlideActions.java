package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;

public class DualSlideActions extends DualSlideMechanism {

    public class LiftUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionHigh();
                initialized = true;
            }

            SLIDE_STATES state = getSlideState();
            packet.put("liftstate", state);
            if (state == SLIDE_STATES.SLIDE_SPECIMENGRAB_POS) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Action liftUp() {
        return new LiftUp();
    }

    public class LiftDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionLow();
                initialized = true;
            }

            SLIDE_STATES state = getSlideState();
            packet.put("liftstate", state);
            if (state == SLIDE_STATES.SLIDE_LOW_POS) {
                return false;
            } else {
                return true;
            }
       }
    }
    public Action liftDown() {
        return new LiftDown();
    }


}