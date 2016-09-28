package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    float thresholdJoyStick = 0.05f;
    float powerThreshold = 0.05f;
    float leftThrottle;//Change in power of left motor
    float rightThrottle;//Change in power of right motor
    float throttleConstant = 0.5f;//Max change in motor power vs change in time. Two seconds of full throttle gets you up to max power. Seems reasonable?
    float friction = -0.5f;//This makes the robot slow down if you don't touch the joysticks
    private ElapsedTime time = new ElapsedTime();
    float time1 = (float)time.milliseconds()/1000;
    float time2;
    float deltaSeconds;
    private void standardDrive(float left,float right){
        if (Math.abs(left) > thresholdJoyStick){
            rHW.leftDrive.setPower(left);
            rHW.leftDrive2.setPower(left);
        } else{
            rHW.leftDrive.setPower(0);
            rHW.leftDrive2.setPower(0);
        }
        if(Math.abs(right) > thresholdJoyStick){
            rHW.rightDrive.setPower(right);
            rHW.rightDrive2.setPower(right);
        }else{
            rHW.rightDrive.setPower(0);
            rHW.rightDrive2.setPower(0);
        }
        //This is the standard drive loop. It *almost* most definitely works, so when sh*t hits the fan we use this.
        //Btw, it does the cancer jerk around thing. So, don't use if you aren't a xbox god
    }
    private void tunedDrive(float left, float right, float leftTune, float rightTune){
        if (Math.abs(left) > thresholdJoyStick){
            rHW.leftDrive.setPower(left * leftTune);
            rHW.leftDrive2.setPower(left * leftTune);
        } else{
            rHW.leftDrive.setPower(0);
            rHW.leftDrive2.setPower(0);
        }
        if(Math.abs(right) > thresholdJoyStick){
            rHW.rightDrive.setPower(right * rightTune);
            rHW.rightDrive2.setPower(right * rightTune);
        }else{
            rHW.rightDrive.setPower(0);
            rHW.rightDrive2.setPower(0);
        }
        //Just in case the Mechanics can't balance the robot right, and it starts turning just tune the thingy
        //I guess for now, just try random tuning numbers to get it relatively straight
    }
    private void throttleDrive(){
        time2 = (float)time.milliseconds()/1000;
        deltaSeconds = time2 - time1;
        if (Math.abs(-gamepad1.left_stick_y) > thresholdJoyStick)
            leftThrottle = -gamepad1.left_stick_y * throttleConstant * deltaSeconds;
        else if (left > powerThreshold) leftThrottle = friction * deltaSeconds;
        else if (left < -powerThreshold) leftThrottle = -friction* deltaSeconds;
        else leftThrottle = 0;
        if (Math.abs(-gamepad1.right_stick_y) > thresholdJoyStick)
            rightThrottle = -gamepad1.right_stick_y * throttleConstant * deltaSeconds;
        else if (right > powerThreshold) rightThrottle = friction * deltaSeconds;
        else if (right < -powerThreshold) rightThrottle = -friction* deltaSeconds;
        else rightThrottle = 0;
        //If you are within the threshold, you will slow down, if not your throttle will increase  til 1.
        left = left + leftThrottle;
        if (left > 1) left = 1; //Cap it at 1
        right = right + rightThrottle;
        if (right > 1) right = 1;//Kappa at 1
        standardDrive(left,right);
        brakeCheck();
        time1 = time2;
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