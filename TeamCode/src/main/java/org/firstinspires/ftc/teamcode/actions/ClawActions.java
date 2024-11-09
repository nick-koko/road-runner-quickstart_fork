package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;

public class ClawActions extends ClawMechanism {

    public class Open implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                clawOpen();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action open() {
        return new Open();
    }

    public class Close implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                clawClose();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action close() {
        return new Close();
    }

    public class DropPosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                clawDropPosition();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action dropPosition() {
        return new DropPosition();
    }

    public class Off implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                clawOff();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action off() {
        return new Off();

    }
}
