package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
/*TODO This year, let's make sure our robot doesn't jerk around n' sh*t,
* so let's code in some stuff to let the robot slow down, and then make a
* designated brake button or smt like that when we need it to stop.
*/
public class ResistorsDrive extends OpMode{
    double left;
    double right;
    ResistorsHardware rHW = new ResistorsHardware();
    @Override public void start(){

    }
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){

    }
    @Override public void loop(){
        left= -gamepad1.left_stick_y;
        right= -gamepad1.right_stick_y;
        rHW.leftDrive.setPower(left);
        rHW.leftDrive.setPower(left);
        rHW.rightDrive.setPower(right);
        rHW.rightDrive2.setPower(right);

    }
    @Override public void stop(){
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
    }
}