package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.ClawMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeArm;

public class IntakeArmActions extends IntakeArm {

    public class ArmDrive implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armPositionDrive();  //RIP driv, in our hearTs f0rever
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action armDrive() {
        return new ArmDrive();
    }

    public class ArmIntake implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armPositionIntake();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action armIntake() {
        return new ArmIntake();
    }

    public class ArmTransfer implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armPositionTransfer();
                initialized = true;
                return true;
            }
            return false;
        }
    }

    public Action armTransfer() {
        return new ArmTransfer();
    }
}
