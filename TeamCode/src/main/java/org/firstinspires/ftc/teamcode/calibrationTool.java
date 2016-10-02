package org.firstinspires .ftc.teamcode;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
//TODO Check if we have encoders, cause this class is very dependant on it. If we don't then I waste effort :(
public class calibrationTool extends OpMode{
    float tune;
    boolean right = true;
    boolean left = false; //Just to make things easier
    boolean slowSide = right;
    ResistorsHardware rHW = new ResistorsHardware();
    private ElapsedTime time = new ElapsedTime();
    public void setDrive(boolean side,double tune){
        if(side){rHW.rightDrive.setPower(tune);rHW.rightDrive2.setPower(tune);
        }else{rHW.leftDrive.setPower(tune);rHW.leftDrive2.setPower(tune);}
    }
    @Override public void start(){}
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){
        setDrive(slowSide,1);
        setDrive(!slowSide,1);
    }
    float t1 = (float)time.milliseconds()/1000;
    float t2 = (float)time.milliseconds()/1000;
    float deltaTime = 0.5f; //Beginning wait to see
    @Override public void loop(){
        while (t2 < t1 + deltaTime) t2= (float)time.milliseconds()/1000;
        if(slowSide) tune = rHW.rightDrive.getCurrentPosition()/rHW.leftDrive.getCurrentPosition();
        else tune = rHW.leftDrive.getCurrentPosition()/rHW.rightDrive.getCurrentPosition();
        setDrive(!slowSide,tune);
        DbgLog.msg("Tune:" + tune);
        while (t2 < t1 + 2) t2= (float)time.milliseconds()/1000;//Let it drive for a bit to see how straight it is now
    }
    @Override public void stop(){
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
        //Emergency Protocol! Stop everything!
    }
}