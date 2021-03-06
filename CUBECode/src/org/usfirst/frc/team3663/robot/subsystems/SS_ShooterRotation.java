package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ShooterMoveRotationTeleop;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotDuelStick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_ShooterRotation extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final int ROTATION_MOTOR_MAX = 1445;
	private final int ROTATION_MOTOR_MIN = 0;
	
	
	private Relay spike = new Relay(Robot.robotMap.turretLed);
	
	private CANTalon rotationMotor = new CANTalon(Robot.robotMap.shooterRotMotor);

	private DigitalInput zeroSwitch = new DigitalInput(Robot.robotMap.shooterZeroDIO);
	//private DigitalInput directionDIO = new DigitalInput(Robot.robotMap.shooterTurnLeftDIO);
	
	private Encoder encoder = new Encoder(Robot.robotMap.shooterRotEncOne, Robot.robotMap.shooterRotEncTwo);

	private int[] perviousEncPos = new int[5];
	private int arrayLoc = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//rotationMotor.enableBrakeMode(true);
        setDefaultCommand(new C_ShooterRotDuelStick());
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
    private int rotDirection = 1;
    private double lastSpeed = 0;
    public boolean isZeroFound = false;
    public boolean safeToShoot = false;
    private int maxValueForPot = 4000;
    
    public void setSpeedRotationMotor(double pSpeed){
    	rotationMotor.set(-pSpeed);
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
    	double pickle = lastSpeed;
    	if(pSpd != 0 && (lastEncRun != pEnc || .04 > Math.abs(pickle))){
    		int encDisp = 0;
    		if(pSpd > 0){
    			encDisp = lastEncRun - pEnc;
    		}
    		else{
    			encDisp = pEnc - lastEncRun;
    		}
			int encToDest = pEnc - dest;
			double amount = ((double)encToDest/(double)encDisp);
			pickle = amount/10;
			if(Math.abs(pickle) < 1000){
				lastSpeed = pickle;				
			}
			//System.out.println(pickle);
			if(Math.abs(pickle) > 1000){
				if(pSpd > 0){
					pickle = .15;
				}
				else{
					pickle = -.15;
				}
			}
    	}
		if(Math.abs(pSpd) > Math.abs(pickle) && pSpd*pickle > 0){
			pSpd = pickle;
		}
		//System.out.println(lastEncRun + "   " + pEnc + "  " + pSpd + "   " + speed + "   " + lastSpeed);
    	return pSpd;
    }
    
    private void advRotorMoveToLoc(int encLocation){
    	int currEnc = -getEncLocation();
    	double pSpd = 0;
    	if(currEnc-encLocation > 0){
    		pSpd = -1;
    	}
    	else{
    		pSpd = 1;
    	}
    	advSetRotSpd(pSpd);    	
    }
    
    public boolean moveRotationToValue(int pEncoderLoc){
    	int currentLoc = getEncLocation();
    	double dist = ((double)currentLoc - (double)pEncoderLoc);
    	double spd = dist/60;
    	advSetRotSpd(-spd);
    	return Math.abs(spd) < .05;
    }
    
    public double targetTick = 0;
    public void convertToTicks(double x, double y){
		if(!Robot.ss_ShooterMainWheel.shooting){
			if(x>.7||x<-.7||y>.7||y<-.7){
				//targetTick = (((Math.atan2(x,y)/Math.PI)+1)/2)*ROTATION_MOTOR_MAX; 
				targetTick = giveUsingZeroLoc(-x,-y);
			}
			else{
				targetTick = getEncLocation();
				//System.out.println("targetTick : " + targetTick);
			}
		}
		else{
			setLight(false);
			if(y < -.7){
				targetTick++;
			}
			if(y > .7){
				targetTick--;
			}
		}
    }
    
    public double giveUsingZeroLoc(double x, double y){
    	double spinRot = Robot.ss_DriveTrain.returnShooterAngle();
    	double raidSpinVal = spinRot/360;
    	double targetMult = raidSpinVal+((Math.atan2(x,y)/Math.PI)+1)/2;
    	if(targetMult > 1)
    	{
    		targetMult-=1;
    	}
    	System.out.println("The Value : " + (targetMult*ROTATION_MOTOR_MAX));
    	return targetMult*ROTATION_MOTOR_MAX;
    }
    
    public boolean zeroEncLimit(){
    	return zeroSwitch.get();
    }

    public void setLight(boolean pValue){
    	if(pValue){
    		spike.set(Relay.Value.kForward);
    	}
    	else{
    		spike.set(Relay.Value.kReverse);
    	}
    }
}

