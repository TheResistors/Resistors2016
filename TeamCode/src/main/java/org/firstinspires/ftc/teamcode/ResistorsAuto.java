package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//TODO import AI lmao
//TODO Check if we have encoders, cause this class is very dependant on it. If we don't then I waste effort :(
public class ResistorsAuto extends OpMode{
    float pi = 3.14159265323f;
    float diameter = 0.05f; //Let's keep this number metric ok? tyty <3 ( ˘ ³˘)♥ Meters is prefered
    float circumference = diameter * pi;
    float robotWidth = 0.45f;
    float speed = 0.5f;
    float angularSpeed = 0.5f;
    ResistorsHardware rHW = new ResistorsHardware();
    private void forward(float meters){
        if(meters > rHW.leftDrive.getCurrentPosition() * circumference / 360){
            rHW.leftDrive.setPower(speed);
            rHW.leftDrive2.setPower(speed);
            rHW.rightDrive.setPower(speed);
            rHW.rightDrive2.setPower(speed);
        }else{
            rHW.leftDrive.setPower(0);
            rHW.leftDrive2.setPower(0);
            rHW.rightDrive.setPower(0);
            rHW.rightDrive2.setPower(0);
        }
        //TODO Try to turn this into a gradual thing?
    }
    private void turnRad(float rad,boolean direction){//Right is true, left is false
        float distance = (robotWidth/2) * rad;
        if (direction){
            if(distance > rHW.leftDrive.getCurrentPosition() *robotWidth*pi/360){
                rHW.leftDrive.setPower(angularSpeed);
                rHW.leftDrive2.setPower(angularSpeed);
                rHW.rightDrive.setPower(-angularSpeed);
                rHW.leftDrive2.setPower(-angularSpeed);
            }
        }else{
            if(distance > rHW.leftDrive.getCurrentPosition() *robotWidth*pi/360){
                rHW.leftDrive.setPower(-angularSpeed);
                rHW.leftDrive2.setPower(-angularSpeed);
                rHW.rightDrive.setPower(angularSpeed);
                rHW.leftDrive2.setPower(angularSpeed);
            }
        }

    }
    @Override public void start(){}
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){}
    private void justDrive(){
        for(int i= 0; i < 4; i++){
            forward(1);
            turnRad(pi/2,true);
        }
        //Just for testing stuff
        //This should go into a meter square that turns right and it should stop at the same place.
    }
    @Override public void loop(){
        justDrive();
    }
    @Override public void stop(){
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
        //Emergency Protocol! Stop everything!
    }
}