package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
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
    private double lastSpeed = 0;
    
    public void resetRightEnc(){
    	rightEncoder.reset();
    }
    
    public void resetLeftEnc(){
    	leftEncoder.reset();
    }
    
    public void resetBothEnc(){
    	resetRightEnc();
    	resetLeftEnc();
    	endEncLocLeft = 0;
    	endEncLocRight = 0;
    	lastEncRunLeft = 0;
    	lastEncRunRight = 0;
    	
    }
    
    public int getLeftEncoder(){
    	return leftEncoder.get();
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
    	setEndingRightLocation((int)(pEndLoc*118.88));
    	setEndingLeftLocation(pEndLoc);
    	if(pEndLoc > 0){
    		lastSpeed = -1;
    	}
    	else{
    		lastSpeed = 1;
    	}
    }
    
    public void advDriveToLoc(){
    	leftDriveMotorOne.enableBrakeMode(true);
    	rightDriveMotorOne.enableBrakeMode(true);
    	int oinkOinkMagic = 1000;
    	int rightEnc = getRightEncoder();
    	int leftEnc = getLeftEncoder();
    	int encToDestR = endEncLocRight - rightEnc;
    	int encDispR = rightEnc - lastEncRunRight;
    	int encDispL = leftEnc - lastEncRunLeft;
    	int encToDestL = endEncLocLeft - leftEnc;
    	lastEncRunRight = rightEnc;
    	lastEncRunLeft = leftEnc;
    	double forwardSpeedR = ((double)encToDestR/(double)encDispR)/oinkOinkMagic;
    	double forwardSpeedL = ((double)encToDestL/(double)encDispL)/oinkOinkMagic;
    	double turnSpeed = 0;
    	if(forwardSpeedR < 0x00ffffff){
    		turnSpeed = (double)((double)forwardSpeedL - (double)forwardSpeedR)/1000;
    	}
    	//System.out.println(rightEnc + ",  " + leftEnc + ",  " + turnSpeed + ",  " + forwardSpeedR + ",  " + forwardSpeedL);
    	driveRobot(forwardSpeedR, turnSpeed);
    }
    
    public boolean advDriveOverLoc(){
    	return getRightEncoder() > endEncLocRight;
    }
    public void EnableBrakeMode(boolean state){
    	leftDriveMotorOne.enableBrakeMode(state);
    	rightDriveMotorOne.enableBrakeMode(state);
    }
    
}

