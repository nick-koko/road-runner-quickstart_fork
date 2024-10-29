package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;
import org.firstinspires.ftc.teamcode.mechanisms.OuttakeDumpMechanism;

public class OuttakeDumpActions extends OuttakeDumpMechanism {

    public class DumpPosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                DumperPositionDump();  //RIP driv, in our hearTs f0rever
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action dumpPosition() {
        return new DumpPosition();
    }

    public class DownPosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                DumperPositionDown();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action downPosition() {
        return new DownPosition();
    }

}
