package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
public class ResistorsDrive extends OpMode{
    ResistorsHardware rHW = new ResistorsHardware();
    @Override public void start(){}
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){}
    double left = 0;
    double right = 0;
    int threshold = 5;
    public void standardDrive(){
        left= -gamepad1.left_stick_y;
        right= -gamepad1.right_stick_y;
        if (Math.abs(left) > threshold){
            rHW.leftDrive.setPower(left);
            rHW.leftDrive2.setPower(left);
        }
        if(Math.abs(right) > threshold){
            rHW.rightDrive.setPower(right);
            rHW.rightDrive2.setPower(right);
        }
        //This is the standard drive loop. It *almost* most definitely works, so when sh*t hits the fan we use this.
        //Btw, it does the cancer jerk around thing. So, don't use if you aren't a xbox god
    }
    double leftChange;
    double rightChange;
    double changeConstant = 1;
    public void reduceJerk(){
        if (Math.abs(-gamepad1.left_stick_y) > threshold)
            leftChange = -gamepad1.left_stick_y * changeConstant;
        if (Math.abs(-gamepad1.right_stick_y) > threshold)
            rightChange = -gamepad1.right_stick_y * changeConstant;
        left = left + leftChange;
        right = right + rightChange;
        if(gamepad1.left_bumper)
            left = 0;
        if(gamepad1.right_bumper)
            right = 0;
        rHW.leftDrive.setPower(left);
        rHW.leftDrive2.setPower(left);
        rHW.rightDrive.setPower(right);
        rHW.rightDrive2.setPower(right);
        //Instead of the joysticks directly controlling the power, we have the joysticks control the change in power
        //I assume this might be less intuitive to control?
    }
    int largeThreshold = 30;
    int friction = 1;
    public void frictionDrive(){
        if(Math.abs(-gamepad1.left_stick_y) > largeThreshold)
            left = -gamepad1.left_stick_y;
        else
            left = left - friction;
        if(Math.abs(gamepad1.right_stick_y) > largeThreshold)
            right = -gamepad1.right_stick_y;
        else
            right = right - friction;
        if(gamepad1.left_bumper)
            left = 0;
        if(gamepad1.right_bumper)
            right = 0;
        rHW.leftDrive.setPower(left);
        rHW.leftDrive2.setPower(left);
        rHW.rightDrive.setPower(right);
        rHW.rightDrive2.setPower(right);
        //This drive mechanism simlulates a sort of friction typy thing? idfk
    }
    @Override public void loop(){
        standardDrive();
    }
    @Override public void stop(){
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
    }
}