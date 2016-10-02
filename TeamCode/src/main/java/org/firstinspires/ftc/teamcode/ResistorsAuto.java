package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//TODO import AI lmao
//TODO Check if we have encoders, cause this class is very dependant on it. If we don't then I waste effort :(
public class ResistorsAuto extends OpMode{
    float pi = 3.14159265323f;
    float diameter = 0.05f; //Let's keep this number metric ok? tyty <3 ( ˘ ³˘)♥ Meters is prefered
    float robotWidth = 0.45f;
    float speed = 0.5f;
    float turningSpeed = 0.5f;
    float leftTune = 1;
    float rightTune = 1;
    boolean right = true;
    boolean left = false;
    ResistorsHardware rHW = new ResistorsHardware();
    private void forward(float meters){
        while(meters > rHW.leftDrive.getCurrentPosition()*diameter * pi/360 ){
            rHW.leftDrive.setPower(speed * leftTune);
            rHW.leftDrive2.setPower(speed* leftTune);
            rHW.rightDrive.setPower(speed* rightTune);
            rHW.rightDrive2.setPower(speed* rightTune);
        }
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
        //TODO Try to turn this into a gradual thing?
    }
    private void turnRad(float rad,boolean direction){//Right is true, left is false
        float distance = (robotWidth/2) * rad;
        while(distance > rHW.leftDrive.getCurrentPosition() *diameter * pi/360){
            rHW.leftDrive.setPower(turningSpeed * leftTune * (direction ? 1 : -1) );
            rHW.leftDrive2.setPower(turningSpeed * leftTune * (direction ? 1 : -1) );
            rHW.rightDrive.setPower(-turningSpeed * rightTune * (direction ? 1 : -1));
            rHW.rightDrive2.setPower(-turningSpeed * rightTune * (direction ? 1 : -1));
        }
        rHW.leftDrive.setPower(0);
        rHW.leftDrive2.setPower(0);
        rHW.rightDrive.setPower(0);
        rHW.rightDrive2.setPower(0);
        //TODO Try to turn this into a gradual thing?
    }
    @Override public void start(){}
    @Override public void init(){
        rHW.init(hardwareMap);
    }
    @Override public void init_loop(){}
    private void justDrive(){
        for(int i= 0; i < 4; i++){
            forward(1);
            turnRad(pi/2,right);
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
