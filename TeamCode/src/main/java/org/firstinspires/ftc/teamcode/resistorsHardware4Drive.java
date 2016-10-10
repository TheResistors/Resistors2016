package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kevin on 09/25/16.
 */
public class resistorsHardware4Drive {
    public DcMotor leftDrive, leftDrive2,rightDrive,rightDrive2 = null;
    public Servo leftArm,leftArm2,rightArm,rightArm2= null;
    HardwareMap RhwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public resistorsHardware4Drive(){

    }
    public void init (HardwareMap ahwMap) {
        RhwMap = ahwMap;
        leftDrive = RhwMap.dcMotor.get("left_drive");
        rightDrive = RhwMap.dcMotor.get("right_drive");
        leftDrive2 = RhwMap.dcMotor.get("left_drive2");
        rightDrive2 = RhwMap.dcMotor.get("right_drive2");
        leftArm = RhwMap.servo.get("left_arm");
        rightArm = RhwMap.servo.get("right_arm");
        leftArm2 = RhwMap.servo.get("left_arm2");
        rightArm2 = RhwMap.servo.get("right_arm2");
    }
    public void waitForTick(long periodMs) throws InterruptedException{
        long remaining = periodMs - (long)period.milliseconds();
        if (remaining > 0)
            Thread.sleep(remaining);
        period.reset();
    }
    public void left(double power){
        leftDrive.setPower(power);
        leftDrive2.setPower(power);
    }
    public void right(double power){
        rightDrive.setPower(power);
        rightDrive2.setPower(power);
    }
}
