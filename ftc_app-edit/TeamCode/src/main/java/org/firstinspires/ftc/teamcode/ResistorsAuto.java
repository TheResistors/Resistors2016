package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//TODO import AI lmao
public class ResistorsAuto extends OpMode{
    float pi = 3.14159265323f;
    float diameter = 0.05f; //Let's keep this number metric ok? tyty <3 ( ˘ ³˘)♥ Meters is prefered
    float robotWidth = 0.45f;
    ResistorsHardware rHW = new ResistorsHardware();
    private void forward(float meters){

    }
    private void turnRad(float rad){

    }

    @Override public void start(){

    }
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){

    }
    private void justDrive(){

        //Just for testing stuff
    }
    @Override public void loop(){
        justDrive();
    }
    @Override public void stop(){

    }
}