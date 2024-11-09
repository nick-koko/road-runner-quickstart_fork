/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 😎👌👌👌
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeServoSpinner {

    // Define class members
    CRServo intakeServo;
    double  power   = 0.0;


    public enum INTAKE_SPINNER_STATES{
        SPINNER_INTAKING, SPINNER_OUTTAKING, SPINNER_STOP
    }

    private INTAKE_SPINNER_STATES curIntakeState = null;

    public void init(HardwareMap hwMap) {
        intakeServo = hwMap.get(CRServo.class, "intake_servo");   //TODONE 👌👌😎👌👌kirbyrules.
        intakeServo.setDirection(DcMotorSimple.Direction.REVERSE);
        curIntakeState = INTAKE_SPINNER_STATES.SPINNER_STOP;
    }

    public void Intake() {
        power = 0.5 ;

        // Set the motor to the new power
        intakeServo.setPower(power);

        curIntakeState = INTAKE_SPINNER_STATES.SPINNER_INTAKING;

    }

    public void Outtake() {

        power = -0.4 ;

        // Set the motor to the new power
        intakeServo.setPower(power);

        curIntakeState = INTAKE_SPINNER_STATES.SPINNER_OUTTAKING;

    }

    public void Stop() {

        // Set power to zero
        power = 0.0 ;

        // Set the motor to the new power;
        intakeServo.setPower(power);

        curIntakeState = INTAKE_SPINNER_STATES.SPINNER_STOP;

    }
    public INTAKE_SPINNER_STATES getIntakeState() {
        return curIntakeState;
    }

}
