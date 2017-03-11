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
    private int EncDir = 0;
    private double lastSpeed = 0;
    private int oinkOinkMagic = 25;
    
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
    		oinkOinkMagic = 25;
    	}
    	else{
    		oinkOinkMagic = 50;
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
    
}

