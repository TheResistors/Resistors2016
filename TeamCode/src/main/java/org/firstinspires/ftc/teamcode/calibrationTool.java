package org.firstinspires .ftc.teamcode;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//TODO Check if we have encoders, cause this class is very dependant on it. If we don't then I waste effort :(
@Autonomous (name = "Tool: Calibration",group = "Tool")
@Disabled
public class calibrationTool extends OpMode{
    float tune;
    boolean right = true;
    boolean left = false; //Just to make things easier
    boolean slowSide = right;
    resistorsHardware4Drive rHW = new resistorsHardware4Drive();
    private ElapsedTime time = new ElapsedTime();
    public void setDrive(boolean side,double tune){
        if(side){rHW.right(tune);
        }else{rHW.left(tune);}
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
        rHW.right(0);
        rHW.left(0);
        //Emergency Protocol! Stop everything!
    }
}