package org.usfirst.frc.team3663.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;


/**
 *Matthew fialied
 *
 */

public class SS_Gyro extends Subsystem {
	AHRS ahrs;
	public double currentHeading;
	public double MaxSpeed = 0;
	public double speed = 0;
	public double BaseSpeed = .45;                // this value is for .35 for turret;
	public double angle;
	public double offSet;
	double lastValue = 0;
	public boolean test = false; 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	@Override
	protected void initDefaultCommand() {
    	try 
    	{
            ahrs = new AHRS(SerialPort.Port.kUSB1); 
        } catch (RuntimeException ex ) 
    	{
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
	}
		
	
    public void SetUpGyro()
    {
    	ahrs.resetDisplacement();
    	//ahrs.reset();
    	offSet = ahrs.getAngle();
    	while(offSet == 0){
	    	if(ahrs.isConnected())
	    	{
	    		
	    		offSet = ahrs.getAngle();
	    		System.out.println("Zero: " + offSet);
	    	}
	    ahrs.setAngleAdjustment(offSet);
	    System.out.println("Zero: " + offSet);
    	}
    }
    
    
    public boolean TurnByGyro(double degree)
    {
    	//double accelZ;
    	//try{
    	System.out.println("Current Position: " + currentHeading + " Zero: " + offSet + " Speed:" + speed);
    	if(ahrs.isConnected())
    	{
    		//angle = ahrs.getAngle();
    		currentHeading = ahrs.getAngle() - offSet;
    		//accelZ = ahrs.getRawAccelZ();
    	}
    	System.out.println("Cal Angle: " + currentHeading);
    	//}
    	//catch (RuntimeException ex ) {
        //    DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        //    return false;
       // }
//    	if (currentHeading < -180) {
//    		currentHeading = currentHeading + 180;
//    	}
//    	if (currentHeading > 180) {
//    		currentHeading = currentHeading - 180;
//    	}
    	if (Math.abs(degree) > 15)
    	{
    		MaxSpeed = .5;
    	}
    	else
    	{
    		MaxSpeed = .25;
    	}
    	// System.out.println("angle: " + angle + " Current Position: " + currentHeading + " Zero: " + zero);
    	if(ahrs.isConnected() && test == false)
    	{
    		double AbsDegree = Math.abs(degree);
    		speed = ((((Math.abs(Math.abs(currentHeading)-AbsDegree)/AbsDegree))*MaxSpeed)/2)+ BaseSpeed;
    		
    		if(degree < 0 )
    		{
    			speed = -1*speed;
    		}
    		Robot.ss_DriveTrain.driveRobot(0, speed);
	    	if(Math.abs(currentHeading-degree) <= .25)
	    	{
	    		speed = 0;
	    		Robot.ss_DriveTrain.driveRobot(speed, speed);
	    		Robot.ss_DriveTrain.enableBrakeMode(true);
	    		System.out.println("Angled Reached" + currentHeading);
	    		return true;
	    	}
    	}
    	if (Math.abs(currentHeading) > 180)
    	{
    		Robot.ss_DriveTrain.enableBrakeMode(true);
    		System.out.println("Angled Reached" + currentHeading);
    		return true;
    	}
    	if (Math.abs(speed) > 1)
    	{
    		System.out.println("ERROR Too Fast");
    		return true;
    	}
    	if (Math.abs(currentHeading) > Math.abs(degree)){
    		System.out.println("ERROR Too Far");
    		return true;
    	}
//    	if (lastValue == currentHeading && speed > 0 && currentHeading != 0)
//    	{
//    		System.out.println("ERROR Not reading Gyro");
//    		return true;
//    	}
//    	lastValue = currentHeading;
//		try {
//			Thread.sleep(20);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return false;
    }
    	
    	
    	

}





