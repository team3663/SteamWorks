package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon leftDriveMotorOne = new CANTalon(Robot.robotMap.driveMotorLeftOne);
	private CANTalon leftDriveMotorTwo = new CANTalon(Robot.robotMap.driveMotorLeftTwo);
	private CANTalon rightDriveMotorOne = new CANTalon(Robot.robotMap.driveMotorRightOne);
	private CANTalon rightDriveMotorTwo = new CANTalon(Robot.robotMap.driveMotorRightTwo);
	
	private Encoder leftEncoder = new Encoder(Robot.robotMap.driveTrainEncoderLeftOne, Robot.robotMap.driveTrainEncoderLeftTwo);
	private Encoder rightEncoder = new Encoder(Robot.robotMap.driveTrainEncoderRightOne, Robot.robotMap.driveTrainEncoderRightTwo);
				
	private RobotDrive drive = new RobotDrive(leftDriveMotorOne, leftDriveMotorTwo, rightDriveMotorOne, rightDriveMotorTwo);

	public int dir = 1;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_Drive());
        drive.setSafetyEnabled(false);
        
        //Code for starting Gyro 3/10/2017
        try 
    	{
            ahrs = new AHRS(SerialPort.Port.kUSB1); 
        } catch (RuntimeException ex ) 
    	{
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    }
    
    public void moveMotor(double speed){
    	leftDriveMotorOne.set(speed);
    	leftDriveMotorTwo.set(speed);
    }
    
    public void setDirection(int pDir){
    	dir = pDir;
    }
    
    public void driveRobot(double pAxisX, double pAxisY){
    	System.out.println(getLeftEncoder() + ",  " + getRightEncoder());
    	drive.arcadeDrive(dir*pAxisX, pAxisY);
    }
    
/*****Drive Encoders Code******/
    private int endEncLocLeft = 0;
    private int endEncLocRight = 0;
    private int lastEncRunLeft = 0;
    private int lastEncRunRight = 0;
    private int EncDir = 0;
    private double lastSpeed = 0;
    private int oinkOinkMagic = 25;
    // Gyro Variables
    AHRS ahrs;
	public double currentHeading;
	public double GyroMaxSpeed = 0;
	public double Gyrospeed = 0;
	public double BaseSpeed = .45;                // this value is for .35 for turret;
	public double angle;
	public double offSet;
	double lastValue = 0;
	public boolean test = false; 
    
    public void resetRightEnc(){
    	rightEncoder.reset();
    }
    
    public void resetLeftEnc(){
    	leftEncoder.reset();
    }
    
    //1772
    
    public void resetBothEnc(){
    	enableBrakeMode(true);
    	resetRightEnc();
    	resetLeftEnc();
    	endEncLocLeft = 0;
    	endEncLocRight = 0;
    	lastEncRunLeft = 0;
    	lastEncRunRight = 0;
    	lastSpeed = 1;
    	
    }
    
    public int getLeftEncoder(){
    	return -leftEncoder.get();
    }
    
    public int getRightEncoder(){
    	return rightEncoder.get();
    }
    
    public void setEndingRightLocation(int pEndingLoc){
    	endEncLocRight = getRightEncoder() + pEndingLoc;
    }
    
    public void setEndingLeftLocation(int pEndingLoc){
    	endEncLocLeft = getLeftEncoder() + pEndingLoc;
    }
    
    public void advStartEncDrive(int pEndLoc){
    	double conversion = 125;
    	if(Robot.ss_DriveTrainPneumatics.lowGear){
    		oinkOinkMagic = 50;
    	}
    	else{
    		oinkOinkMagic = 25;
    	}
    	if(pEndLoc > 0){
    		EncDir = 1;
    	}
    	else{
    		EncDir = -1;
    	}
    	setEndingRightLocation((int)(EncDir*pEndLoc*conversion));
    	setEndingLeftLocation((int)(EncDir*pEndLoc*conversion));
    }
    
    public void advDriveToLoc(){
    	int rightEnc = EncDir*getRightEncoder();
    	int encToDestR = endEncLocRight - rightEnc;
    	int encDispR = rightEnc - lastEncRunRight;
    	lastEncRunRight = rightEnc;
    	double forwardSpeedR = ((double)encToDestR/(double)encDispR)/oinkOinkMagic;
    	forwardSpeedR = Math.abs(forwardSpeedR);
    	if(forwardSpeedR > .6){
    		forwardSpeedR = .6;
    	}
    	//System.out.println(rightEnc + ",  " + leftEnc + ",  " + turnSpeed + ",  " + forwardSpeedR + ",  " + forwardSpeedL);
    	driveRobot(EncDir*forwardSpeedR, 0);
    }
    
    public boolean advDriveOverLoc(){
    	return EncDir*getRightEncoder() > endEncLocRight;
    }
    public void enableBrakeMode(boolean state){
    	leftDriveMotorOne.enableBrakeMode(state);
    	rightDriveMotorOne.enableBrakeMode(state);
    	leftDriveMotorTwo.enableBrakeMode(state);
    	rightDriveMotorTwo.enableBrakeMode(state);
    }
    
    
    
/////////////////// Code for Gyro /////////////////////////////
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
    	System.out.println("Current Position: " + currentHeading + " Zero: " + offSet + " Speed:" + Gyrospeed);
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
    		GyroMaxSpeed = .5;
    	}
    	else
    	{
    		GyroMaxSpeed = .25;
    	}
    	// System.out.println("angle: " + angle + " Current Position: " + currentHeading + " Zero: " + zero);
    	if(ahrs.isConnected() && test == false)
    	{
    		double AbsDegree = Math.abs(degree);
    		Gyrospeed = ((((Math.abs(Math.abs(currentHeading)-AbsDegree)/AbsDegree))*GyroMaxSpeed)/2)+ BaseSpeed;
    		
    		if(degree < 0 )
    		{
    			Gyrospeed = -1*Gyrospeed;
    		}
    		Robot.ss_DriveTrain.driveRobot(0, Gyrospeed);
	    	if(Math.abs(currentHeading-degree) <= .25)
	    	{
	    		Gyrospeed = 0;
	    		driveRobot(Gyrospeed, Gyrospeed);
	    		enableBrakeMode(true);
	    		System.out.println("Angled Reached" + currentHeading);
	    		return true;
	    	}
    	}
    	if (Math.abs(currentHeading) > 180)
    	{
    		enableBrakeMode(true);
    		System.out.println("Angled Reached" + currentHeading);
    		return true;
    	}
    	if (Math.abs(Gyrospeed) > 1)
    	{
    		System.out.println("ERROR Too Fast");
    		return true;
    	}
    	if (Math.abs(currentHeading) > Math.abs(degree)){
    		System.out.println("ERROR Too Far");
    		return true;
    	}
		return false;
    }
    public double DriveByGyro(){
    	double turnRate = 0;
    	SetUpGyro();					//reset gyro 
    	currentHeading = ahrs.getAngle() - offSet;
    	turnRate = currentHeading/10; 
    	return turnRate;
    }
    
}

