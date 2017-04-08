package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_FuelPickup extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private CANTalon pickupMotor = new CANTalon(Robot.robotMap.fuelPickupMotor);
	private boolean motorToggled = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPickupMotorSpeed(double pSpeed){
    	pickupMotor.set(-pSpeed);
    }
    
    public void togglePickupMotor(){
    	if(motorToggled){
    		motorToggled = false;
    		setPickupMotorSpeed(0);
    	}
    	else{
    		motorToggled = true;
    		setPickupMotorSpeed(.5);
    	}
    }
}

