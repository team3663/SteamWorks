package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_ShooterMainWheel extends Subsystem {

	private final int SHOOTER_EST_MAX_SPEED= 2000;
	
	private CANTalon mainMotor = new CANTalon(Robot.robotMap.shooterMainMotor);
	private CANTalon mainMotor2 = new CANTalon(Robot.robotMap.shooterMainMotor2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeedMainMotor(double pSpeed){
    	mainMotor.set(pSpeed);
    	mainMotor2.set(pSpeed);
    }
    
    public boolean safeToShoot(){
    	System.out.println("Shooter safe : " + Robot.ss_ShooterRotation.safeToShoot);
    	return Robot.ss_ShooterRotation.safeToShoot;
    	//return false;
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
		    	setSpeedMainMotor(currentSpeed);
		    	System.out.println("Velocity : " + vel + "ticks/ms);  CEncPos : " + currentEncVal + "  Current Speed : " + currentSpeed);
	    	}
	    	lastEncVal = currentEncVal;
	    } 
}
}

