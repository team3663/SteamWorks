package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ShooterMoveRotation;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final int ROTATION_MOTOR_MAX = 200000;
	private final int SHOOTER_EST_MAX_SPEED= 2000;
	
	private CANTalon rotationMotor = new CANTalon(Robot.robotMap.shooterRotMotor);
	private CANTalon mainMotor = new CANTalon(Robot.robotMap.shooterMainMotor);
	private CANTalon mainMotor2 = new CANTalon(Robot.robotMap.shooterMainMotor2);
	
	private DigitalInput zeroSwitch = new DigitalInput(Robot.robotMap.shooterZeroDIO);
	private DigitalInput turnLeftDIO = new DigitalInput(Robot.robotMap.shooterTurnLeftDIO);
	private DigitalInput turnRightDIO = new DigitalInput(Robot.robotMap.shooterTurnRightDIO);
	
	private int[] perviousEncPos = new int[40];
	private int arrayLoc = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//rotationMotor.enableBrakeMode(true);
        setDefaultCommand(new C_ShooterMoveRotation());
    }
    
    public void setSpeedMainMotor(double pSpeed){
    	mainMotor.set(pSpeed);
    	mainMotor2.set(-pSpeed);
    }
    
    public int getEncoderRotationMotor(){
    	perviousEncPos[arrayLoc++] = rotationMotor.getEncPosition();
    	if(arrayLoc >= perviousEncPos.length){
    		arrayLoc = 0;
    	}
    	long temp = 0;
    	for(int i = 0; i < perviousEncPos.length; i++){
    		temp += perviousEncPos[i];
    	}
    	//System.out.println("EncoderAvrage : " + temp/perviousEncPos.length);
    	return (int) (temp/perviousEncPos.length);
    }

    public void resetMainMotorEncoder(int targetSpeed){
    	if(targetSpeed > 0){
    		currentSpeed = (double)targetSpeed/(double)SHOOTER_EST_MAX_SPEED; 
    	}
    	else{
    		currentSpeed = -(double)targetSpeed/(double)SHOOTER_EST_MAX_SPEED;
    	}
    	System.out.println("Current Speed : " + currentSpeed);
    	lastEncVal = mainMotor.getEncPosition()+1;
    }
 
    private double currentSpeed = 0;
    private int lastEncVal = 0;
    public void mainMotorStayAtVel(int pVal){
    	int currentEncVal = mainMotor.getEncPosition();
    	if(currentEncVal != lastEncVal){
	    	double vel = (currentEncVal - lastEncVal)/20;
	    	if(Math.abs(vel) < 5000){
		    	currentSpeed -= ((vel - pVal)/Math.abs(pVal))/25;
		    	setSpeedMainMotor(-currentSpeed);
		    	System.out.println("Velocity : " + vel + "ticks/ms);  CEncPos : " + currentEncVal + "  Current Speed : " + currentSpeed);
	    	}
	    	lastEncVal = currentEncVal;
	    } 
    }
    
/***all of the code responsible for moving the rotation***/   
    private int encoderZero = 0;
    private int lastEncRun = 0;
    private double lastSpeed = 0;
    
    public void setSpeedRotationMotor(double pSpeed){
    	rotationMotor.set(pSpeed);
    }
    
    public void advRotResetEnc(){
    	rotationMotor.setPosition(0);
    }
    
    public void setRotMotorBreak(boolean pBreak){
    	rotationMotor.enableBrakeMode(pBreak);
    }
    
    public void advSetRotSpd(double pSpd){
    	int currentEncLocation = -rotationMotor.getEncPosition();
    	if((currentEncLocation < ROTATION_MOTOR_MAX && pSpd > 0)){
    		pSpd = advConvertSpeed(pSpd, currentEncLocation, ROTATION_MOTOR_MAX);
    		setSpeedRotationMotor(pSpd);
    	}
    	else if((currentEncLocation > 0 && pSpd < 0)){
    		pSpd = advConvertSpeed(pSpd, currentEncLocation, 0);
    		setSpeedRotationMotor(pSpd);
    	}
    	else{
    		setSpeedRotationMotor(0);
    	}
        lastEncRun = currentEncLocation; 
    }
    
    private double advConvertSpeed(double pSpd, int pEnc, int dest){
    	double speed = lastSpeed;
    	if(pSpd != 0 && (lastEncRun != pEnc || .04 > Math.abs(speed))){
    		int encDisp = 0;
    		if(pSpd > 0){
    			encDisp = lastEncRun - pEnc;
    		}
    		else{
    			encDisp = pEnc - lastEncRun;
    		}
			int encToDest = pEnc - dest;
			double amount = ((double)encToDest/(double)encDisp);
			speed = amount/20;
			if(Math.abs(speed) < 1000){
				lastSpeed = speed;				
			}
			if(Math.abs(speed) > 1000){
				if(pSpd > 0){
					speed = .06;
				}
				else{
					speed = -.06;
				}
			}
    	}
		if(Math.abs(pSpd) > Math.abs(speed) && pSpd*speed > 0){
			pSpd = speed;
		}
		//System.out.println(lastEncRun + "   " + pEnc + "  " + pSpd + "   " + speed + "   " + lastSpeed);
    	return pSpd;
    }
    
    private void advRotorMoveToLoc(int encLocation){
    	int currEnc = -rotationMotor.getEncPosition();
    	double pSpd = 0;
    	if(currEnc-encLocation > 0){
    		pSpd = 1;
    	}
    	else{
    		pSpd = -1;
    	}
    	advSetRotSpd(pSpd);    	
    }
    
    public void advMoveRotOffDIO(){
    	boolean left = turnLeftDIO.get();
    	boolean right = turnRightDIO.get();
    	if(left && right){
    		advSetRotSpd(0);
    		System.out.println("****GOOD TO SHOOT****");
    	}
    	else if(right){
    		advSetRotSpd(1);    		
    		System.out.println("MOVE : right");
    	}
    	else if(left){
    		advSetRotSpd(-1);
    		System.out.println("MOVE : left");
    	}
    	else{
    		setSpeedRotationMotor(0);
    		System.out.println("ERROR : no input");
    	}
    }
}

