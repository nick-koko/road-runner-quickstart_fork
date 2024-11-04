package org.firstinspires.ftc.teamcode.actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.mechanisms.DualSlideMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.IntakeSlide;

public class IntakeSlideAction extends IntakeSlide {

    public class Transfer implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionTransfer();
                initialized = true;
            }

            SLIDE_STATES state = getSlideState();
            packet.put("IntakeSlidestate", state);
            if (state == SLIDE_STATES.SLIDE_TRANSFER_POS) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Action transfer() {
        return new Transfer();
    }

    public class AutonAction implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidePositionAuton();
                initialized = true;
            }

            //SLIDE_STATES state = getSlideState();
            int slidePos = getSlideMotorPos();

            if (Math.abs(slidePos - AUTON_POSITION) < (15)) {
                packet.put("IntakeSlidestate", SLIDE_STATES.SLIDE_AUTON_POS);
                return false;
            } else {
                packet.put("IntakeSlidestate", getSlideState());
                return true;
            }
        }
    }
    public Action autonAction() {
        return new AutonAction();
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
    public Action stopIntakeSlide() {
        return new StopSlide();       //todone
    }

      public Action extendIntakeSlide() {
          return new ExtendSlide();
      }
    public class ExtendSlide implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extendSlide(0.25);
                initialized = true;
            }
            return false;

        }
    }

}

//todone emoji glasses cool peace sign peace sign