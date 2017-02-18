package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ShooterMoveRotationTeleop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_ShooterRotation extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final int ROTATION_MOTOR_MAX = 1450;
	private final int ROTATION_MOTOR_MIN = 0;
	
	private CANTalon rotationMotor = new CANTalon(Robot.robotMap.shooterRotMotor);

	private DigitalInput zeroSwitch = new DigitalInput(Robot.robotMap.shooterZeroDIO);
	private DigitalInput turnLeftDIO = new DigitalInput(Robot.robotMap.shooterTurnLeftDIO);
	private DigitalInput turnRightDIO = new DigitalInput(Robot.robotMap.shooterTurnRightDIO);
	
	private Encoder encoder = new Encoder(Robot.robotMap.shooterRotEncOne, Robot.robotMap.shooterRotEncTwo);

	private int[] perviousEncPos = new int[5];
	private int arrayLoc = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//rotationMotor.enableBrakeMode(true);
        setDefaultCommand(new C_ShooterMoveRotationTeleop());
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
    
/***all of the code responsible for moving the rotation***/   
    private int encoderZero = 0;
    private int lastEncRun = 0;
    private double lastSpeed = 0;
    public boolean isZeroFound = false;
    public boolean safeToShoot = false;
    
    public void setSpeedRotationMotor(double pSpeed){
    	rotationMotor.set(pSpeed);
    }
    
    public void advRotResetEnc(){
    	encoder.reset();
    }
    
    public void setRotMotorBreak(boolean pBreak){
    	rotationMotor.enableBrakeMode(pBreak);
    }
    
    public int getEncLocation(){
    	return encoder.get();
    }
    
    public void advSetRotSpd(double pSpd){
    	int currentEncLocation = getEncLocation();
    	//System.out.println("Current Enc Loc : " + currentEncLocation);
    	if((currentEncLocation < ROTATION_MOTOR_MAX && pSpd > 0) && isZeroFound){
    		pSpd = advConvertSpeed(pSpd, currentEncLocation, ROTATION_MOTOR_MAX);
    		setSpeedRotationMotor(pSpd);
    	}
    	else if((currentEncLocation > ROTATION_MOTOR_MIN && pSpd < 0) && isZeroFound){
    		pSpd = advConvertSpeed(pSpd, currentEncLocation, ROTATION_MOTOR_MIN);
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
			speed = amount/10;
			if(Math.abs(speed) < 1000){
				lastSpeed = speed;				
			}
			if(Math.abs(speed) > 1000){
				if(pSpd > 0){
					speed = .1;
				}
				else{
					speed = -.1;
				}
			}
    	}
		if(Math.abs(pSpd) > Math.abs(speed) && pSpd*speed > 0){
			pSpd = speed;
		}
		System.out.println(lastEncRun + "   " + pEnc + "  " + pSpd + "   " + speed + "   " + lastSpeed);
    	return pSpd;
    }
    
    private void advRotorMoveToLoc(int encLocation){
    	int currEnc = -getEncLocation();
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
    		//System.out.println("****GOOD TO SHOOT****");
    		safeToShoot = true;
    	}
    	else if(right){
    		advSetRotSpd(1);    		
    		//System.out.println("MOVE : right");
    		safeToShoot = false;
    	}
    	else if(left){
    		advSetRotSpd(-1);
    		//System.out.println("MOVE : left");
    		safeToShoot = false;
    	}
    	else{
    		setSpeedRotationMotor(0);
    		//System.out.println("ERROR : no input");
    		safeToShoot = false;
    	}
    }
    
    public boolean zeroEncLimit(){
    	return zeroSwitch.get();
    }
}
