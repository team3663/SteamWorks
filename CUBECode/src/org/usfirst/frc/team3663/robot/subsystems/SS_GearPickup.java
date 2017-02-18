package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_GearPickup extends Subsystem {
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon gearMotor = new CANTalon(Robot.robotMap.GearInTake);
	//private DoubleSolenoid GearPickUp = new DoubleSolenoid(Robot.robotMap.GearPickUpOne, Robot.robotMap.dGearPickUpTwo);
	private boolean motorToggled = false;
	private double pSpeed = .5;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public boolean PickUp() {
    	
    	
    	
    	
		return true;
    }
    
    public boolean Close(){
    	
    	
    	
    	
    	
    	return true;
    }
    public boolean Intake(){
    	if(motorToggled)
    	{
    		motorToggled = false;
    		gearMotor.set(0);
    	}
    	else
    	{
    		motorToggled = true;
    		gearMotor.set(pSpeed);
    	}
    	return motorToggled; 
    }
}

