package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kevin on 09/25/16.
 */
public class ResistorsHardware {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor leftDrive2 = null;
    public DcMotor rightDrive2 = null;
    public Servo arm = null;
    HardwareMap RhwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public ResistorsHardware(){

    }
    public void init (HardwareMap ahwMap) {
        RhwMap = ahwMap;
        leftDrive = RhwMap.dcMotor.get("left_drive");
        rightDrive = RhwMap.dcMotor.get("right_drive");
        leftDrive2 = RhwMap.dcMotor.get("left_drive2");
        rightDrive2 = RhwMap.dcMotor.get("right_drive2");

    }
    public void waitForTick(long periodMs) throws InterruptedException{
        long remaining = periodMs - (long)period.milliseconds();
        if (remaining > 0)
            Thread.sleep(remaining);
        period.reset();
    }
}
