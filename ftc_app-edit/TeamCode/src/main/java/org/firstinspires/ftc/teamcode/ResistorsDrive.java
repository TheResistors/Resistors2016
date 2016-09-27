package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
public class ResistorsDrive extends OpMode{
    ResistorsHardware rHW = new ResistorsHardware();
    @Override public void start(){}
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){}
    private void brakeCheck(){
        if(gamepad1.left_bumper)
            left = 0;
        if(gamepad1.right_bumper)
            right = 0;
    }
    float left = 0;
    float right = 0;
    float threshold = 0.05f;
    float leftThrottle;//Change in power of left motor
    float rightThrottle;//Change in power of right motor
    float throttleConstant = 0.5f;//Max change in motor power vs change in time. Two seconds of full throttle gets you up to max power. Seems reasonable?
    float friction = -0.5f;//This makes the robot slow down if you don't touch the joysticks
    float deltaSeconds = android.os.SystemClock.elapsedRealtime()/1000;//At this moment, this is initSeconds, but ignore the name of the variable for now
    private void standardDrive(float left,float right){
        if (Math.abs(left) > threshold){
            rHW.leftDrive.setPower(left);
            rHW.leftDrive2.setPower(left);
        } else{
            rHW.leftDrive.setPower(0);
            rHW.leftDrive2.setPower(0);
        }
        if(Math.abs(right) > threshold){
            rHW.rightDrive.setPower(right);
            rHW.rightDrive2.setPower(right);
        }else{
            rHW.rightDrive.setPower(0);
            rHW.rightDrive2.setPower(0);
        }
        //This is the standard drive loop. It *almost* most definitely works, so when sh*t hits the fan we use this.
        //Btw, it does the cancer jerk around thing. So, don't use if you aren't a xbox god
    }
    private void throttleDrive(){
        deltaSeconds = android.os.SystemClock.elapsedRealtime()/1000 - deltaSeconds;
        if (Math.abs(-gamepad1.left_stick_y) > threshold)
            leftThrottle = -gamepad1.left_stick_y * throttleConstant * deltaSeconds;
        else if (left > 0) leftThrottle = friction * deltaSeconds;
        else if (left < 0) leftThrottle = -friction* deltaSeconds;
        if (Math.abs(-gamepad1.right_stick_y) > threshold)
            rightThrottle = -gamepad1.right_stick_y * throttleConstant * deltaSeconds;
        else if (right > 0) rightThrottle = friction * deltaSeconds;
        else if (right < 0) rightThrottle = -friction* deltaSeconds;
        //If you are within the threshold, your 
        left = left + leftThrottle;
        if (left > 1) left = 1; //Cap it at 1
        right = right + rightThrottle;
        if (right > 1) right = 1;//Kappa at 1
        standardDrive(left,right);
        brakeCheck();
        //Instead of the joysticks directly controlling the power, it controls the change in power, or throttle. Feels more like a car
        //No fkin clue if this'll work, I (Zheng) just pulled this out of my arse
    }
    @Override public void loop(){
        standardDrive(-gamepad1.left_stick_y,-gamepad1.right_stick_y);
    }
    @Override public void stop(){
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
        //Emergency Protocal! Stop everything!
    }
}