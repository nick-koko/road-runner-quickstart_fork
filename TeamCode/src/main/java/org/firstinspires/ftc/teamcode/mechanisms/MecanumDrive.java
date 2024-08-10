package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {
    // Add motors here
    private DcMotor motorName;

    // Class variables
	private boolean useMotorEncoders = true;

    public void init(HardwareMap hwMap) {

        motorName = hwMap.get(DcMotor.class, "motor_name_from_hub");
        this.motorName.setDirection(DcMotor.Direction.FORWARD);
        this.motorName.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		if (useMotorEncoders) {
			this.motorName.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			this.motorName.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		}
		else {
			this.motorName.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);	
		}
    }

	// Drive chassis using power inputs
	public void driveUsingPowers(double frontLeftPower) {
        this.motorName.setPower(frontLeftPower);
		
	}

    // Method to stop moving
    public void stopMoving() {
        // Set the motor power to zero to stop the slide
        this.motorName.setPower(0.0);
    }

 }