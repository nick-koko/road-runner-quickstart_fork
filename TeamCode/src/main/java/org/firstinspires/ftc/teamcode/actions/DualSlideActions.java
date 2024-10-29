package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;

public class DualSlideActions extends DualSlideMechanism {

    public class Low implements Action {
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
    public Action low() {
        return new Low();
    }

    public class SpecimenGrab implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionSpecimenGrab();
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
    public Action specimenGrab() {
        return new SpecimenGrab();       //todone
    }
    public class SpecimenDrop implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionSpecimenDrop();
                initialized = true;
            }

            SLIDE_STATES state = getSlideState();
            packet.put("liftstate", state);
            if (state == SLIDE_STATES.SLIDE_SPECIMENDROP_POS) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Action specimenDrop() {
        return new SpecimenDrop();
    }

    public class High implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionHigh();
                initialized = true;
            }

            SLIDE_STATES state = getSlideState();
            packet.put("liftstate", state);
            if (state == SLIDE_STATES.SLIDE_HIGH_POS) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Action high() {
        return new High();
    }

}

//todone emoji glasses cool peace sign peace sign