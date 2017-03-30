package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_ShooterMainWheel extends Subsystem {

	public final int SHOOTER_EST_MAX_SPEED= 1900;
	
	private CANTalon mainMotor = new CANTalon(Robot.robotMap.shooterMainMotor);
	private CANTalon mainMotor2 = new CANTalon(Robot.robotMap.shooterMainMotor2);
	
	private DoubleSolenoid hoodHeight = new DoubleSolenoid(Robot.robotMap.shooterMain, Robot.robotMap.shooterPistonOne, Robot.robotMap.shooterPistonTwo);

	public int targetValue=1450;
	public boolean hoodUp = false;
	
	public boolean shooting = false;
	public boolean atSpeed = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new C_ShooterTriggerButton());
    }
    
    public void setSpeedMainMotor(double pSpeed){
    	mainMotor.set(pSpeed);
    	mainMotor2.set(-pSpeed);
    }
    
    public boolean safeToShoot(){
    	//System.out.println("Shooter safe : " + Robot.ss_ShooterRotation.safeToShoot);
    	return Robot.ss_ShooterRotation.safeToShoot;
    	//return false;
    }
    
    public int getEncoder(){
    	//System.out.println(""+ mainMotor.getEncPosition() + ", " + mainMotor2.getEncPosition());
    	return mainMotor.getEncPosition();
    	
    }
	    
	public void resetMainMotorEncoder(int targetSpeed){
		if(targetSpeed > 0){
			currentSpeed = (double)targetSpeed/(double)SHOOTER_EST_MAX_SPEED; 
		}
		else{
			currentSpeed = -(double)targetSpeed/(double)SHOOTER_EST_MAX_SPEED;
		}
		//System.out.println("Current Speed : " + currentSpeed);
		lastEncVal = mainMotor.getEncPosition()+1;
	}
		
	public double currentSpeed = 0;
	private int lastEncVal = 0;
	public void mainMotorStayAtVel(int pVal){
		int currentEncVal = getEncoder();
		//System.out.println(currentEncVal);
		if(currentEncVal != lastEncVal){
	    	double vel = -(currentEncVal - lastEncVal)/20;
			System.out.println("Run " + vel + "  NEEDED : " + pVal +"   Boolean " + (Math.abs(vel) < 5000));
	    	if(Math.abs(vel) < 5000){
	    		double moveAmount = ((vel - pVal)/Math.abs(pVal))/5;
		    	currentSpeed -= (moveAmount);
		    	if(currentSpeed > ((double)(pVal+100)/SHOOTER_EST_MAX_SPEED)){
		    		currentSpeed =(double)(pVal+100)/SHOOTER_EST_MAX_SPEED;
		    	}
		    	else if(currentSpeed < ((double)(pVal-100)/SHOOTER_EST_MAX_SPEED)){
		    		currentSpeed =(double)(pVal-100)/SHOOTER_EST_MAX_SPEED;
		    	}
		    	//System.out.print("moveAmount : " + moveAmount);
		    	if(vel < pVal+20 && vel > pVal-20){
		    		atSpeed = true;
		    	}
		    	else{
		    		atSpeed = false;
		    	}
		    	setSpeedMainMotor(-currentSpeed);
		   }
	    	lastEncVal = currentEncVal;
	    } 
	}
	
	public int getMotorVel(){
		int currentEncVal = getEncoder();
		double vel= 0;
		if(currentEncVal != lastEncVal){
	    	vel = (currentEncVal - lastEncVal)/20;
		}
		return (int)vel;
	}
	
	public void setPistonValue(boolean pState){
		if(hoodUp){
			hoodHeight.set(DoubleSolenoid.Value.kForward);
			hoodUp = false;
		}
		else{
			hoodHeight.set(DoubleSolenoid.Value.kReverse);
			hoodUp = true;
		}
	}
	
	public void setTargetVal(int dp){
		if(dp == 0){
			targetValue += 5;
			//currentSpeed = ((double)targetValue/(double)(SHOOTER_EST_MAX_SPEED-200))-.2;
		}
		if(dp == 180){
			targetValue-=5;
			//currentSpeed = ((double)targetValue/(double)(SHOOTER_EST_MAX_SPEED-200))-.2;
		}
	}
}

