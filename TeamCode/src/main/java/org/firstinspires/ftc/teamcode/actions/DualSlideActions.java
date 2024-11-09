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
            packet.put("LiftEncoder", getSlideLMotorPos());
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

    public class StopSlide implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                stopSlide();
                initialized = true;
            }
            return false;


        }
    }
    public Action stopOuttakeSlide() {
        return new StopSlide();       //todone
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


public class ExtendAction implements Action {
    private boolean initialized = false;

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        if (!initialized) {
            extendSlide();
            initialized = true;
            return true;
        }
        return false;
    }
}
    public Action extendAction() {
        return new ExtendAction();
    }

    public class EndAutonPosAction implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionAny(865,865);
                initialized = true;
                return true;
            }
            return false;
        }
    }
    public Action endAutonPos() {
        return new EndAutonPosAction();
    }

    public class ResetEncoders implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                resetSlide();
                initialized = true;
                return true;
            }
            return false;
        }
    }
    public Action resetEncoders() {
        return new ResetEncoders();
    }

}


//todone emoji glasses cool peace sign peace sign