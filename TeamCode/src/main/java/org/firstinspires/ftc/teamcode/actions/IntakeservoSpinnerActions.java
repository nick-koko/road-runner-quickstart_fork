package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.IntakeServoSpinner;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;

public class IntakeservoSpinnerActions extends IntakeServoSpinner {

    public class IntakePosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                Intake();  //RIP driv, in our hearTs f0rever
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action intakePosition() {
        return new IntakePosition();
    }

    public class OuttakePosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                Outtake();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action outtakePosition() {
        return new OuttakePosition();
    }
    public class StopPosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                Stop();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action stopPosition() { //
        return new StopPosition();
    }
}
