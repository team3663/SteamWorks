package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
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
	private double TurnGyroValue = 2;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_Drive());
        drive.setSafetyEnabled(false);
        
        //Code for starting Gyro 3/10/2017
        try 
    	{
            ahrs = new AHRS(SerialPort.Port.kUSB1); 
            //ahrs = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex ) 
    	{
        	int test = 1;
        	while(test == 1){
        		System.out.println("sdafjsdfjao'sidjgoiarsdgasop");
        	}
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
    	//System.out.println(getLeftEncoder() + ",  " + getRightEncoder() + ",  " + ahrs.getAngle() + ",  " + Robot.ss_AutoChoose.getAnalogVal());
    	drive.arcadeDrive(dir*pAxisX, pAxisY);
    }
    
/*****Drive Encoders Code******/
    private int endEncLocLeft = 0;
    double TimeStart;
    private int endEncLocRight = 0;
    private int lastEncRunLeft = 0;
    private int lastEncRunRight = 0;
    private int EncDir = 0;
    private double lastSpeed = 0;
    private int oinkOinkMagic = 25;
    private double maxTime = 0;
    // Gyro Variables
    AHRS ahrs;
	public double currentHeading;
	public double GyroMaxSpeed = 0;
	public double Gyrospeed = 0;
	public double BaseSpeed = .5;                // this value is for .35 for turret;
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
    	return leftEncoder.get();
    }
    
    public int getRightEncoder(){
    	return -rightEncoder.get();
    }
    
    public void setEndingRightLocation(int pEndingLoc){
    	endEncLocRight = getRightEncoder() + pEndingLoc;
    }
    
    public void setEndingLeftLocation(int pEndingLoc){
    	endEncLocLeft = getLeftEncoder() + pEndingLoc;
    }
    
    public void advStartEncDrive(int pEndLoc, double pWaitTime){
    	double conversion = 83;
    	maxTime = Timer.getFPGATimestamp() + pWaitTime;
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
        TimeStart = Timer.getFPGATimestamp(); // Get the current CPU clock time before the code drives by encoder
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
    	//System.out.println(rightEnc + ",  " + forwardSpeedR);
    	driveRobot(EncDir*forwardSpeedR, (strightByGyro())*forwardSpeedR); // Drive By Gyro
    }
    
    public boolean advDriveOverLocOrTime(){
    	return EncDir*getRightEncoder() > endEncLocRight || Timer.getFPGATimestamp() > maxTime;
    }
    
    public void enableBrakeMode(boolean state){
    	leftDriveMotorOne.enableBrakeMode(state);
    	rightDriveMotorOne.enableBrakeMode(state);
    	leftDriveMotorTwo.enableBrakeMode(state);
    	rightDriveMotorTwo.enableBrakeMode(state);
    }    
    private double startNumber = 0;
    
    public double getAngle(){
    	return ahrs.getAngle() - startNumber;
    }
    
    public void gryoReset(){
    	startNumber = ahrs.getAngle();
    }
    
    public double turnByGyro(double pLoc){
    	double tract = .75; //feild = .8
    	double speed = (pLoc-getAngle())/60;
    	
    	System.out.println(getAngle());
    	if(speed < tract && speed > 0){
    		speed = tract;
    	}if(speed > -tract && speed < 0){
    		speed = -tract;
    	}
    	return speed;
    }
    
    public boolean driveByGyroTwo(double pLoc){
    	double spd = turnByGyro(pLoc);
    	//System.out.println("  Speed : " + spd + "   Current : " + getAngle() + "  Dest : " + pLoc);
    	drive.arcadeDrive(0, spd);
    	//System.out.println("oiaseWR" + (Math.abs(pLoc) > Math.abs(getAngle())));
    	return Math.abs(pLoc) < Math.abs(getAngle());
    }
    public double strightByGyro(){
    	double TurnSpeed = 0;
    	TurnSpeed = -1*(getAngle()/TurnGyroValue)/2;
		return TurnSpeed;
    }
    
    public double driveStrait(){
    	return getAngle()/360;
    }
    
    public double returnShooterAngle(){
    	double angle = -ahrs.getAngle();
    	while(angle > 360){
    		angle-=360;
    	}
    	while(angle < 0){
    		angle+=360;
    	}
    	//System.out.print("  Angle : " + angle + "  ");
    	return angle;
    }
}

