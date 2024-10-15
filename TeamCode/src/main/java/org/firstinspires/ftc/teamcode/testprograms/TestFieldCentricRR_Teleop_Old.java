package org.firstinspires.ftc.teamcode.testprograms;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name="TestFieldCentricRR_Teleop_old")
// @Disabled
public class TestFieldCentricRR_Teleop_Old extends OpMode{

    public MecanumDrive robot = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //Mecanum
        double driving = -gamepad1.right_stick_y;
        double strafe = -gamepad1.right_stick_x;
        double rotate = -gamepad1.left_stick_x;

        boolean fieldCentric = false;

        double botHeading = Math.toDegrees(robot.pose.heading.toDouble());

        double rotX = strafe * Math.cos(-botHeading) - driving * Math.sin(-botHeading);
        double rotY = strafe * Math.sin(-botHeading) + driving * Math.cos(-botHeading);

        if (gamepad1.y) {
            fieldCentric = true;
        } else if (gamepad1.a) {
            fieldCentric = false;
        }

        if (fieldCentric) {
            strafe = rotX;
            driving = rotY;
        }
        robot.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        driving,
                        strafe
                ),
                    rotate
                ));

            robot.updatePoseEstimate();

        telemetry.addData("x", robot.pose.position.x);
        telemetry.addData("y", robot.pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(robot.pose.heading.toDouble()));
        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        packet.fieldOverlay().setStroke("#3F51B5");
        Drawing.drawRobot(packet.fieldOverlay(), robot.pose);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
