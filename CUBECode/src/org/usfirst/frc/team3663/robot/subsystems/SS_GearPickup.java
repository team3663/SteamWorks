package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
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
	private DoubleSolenoid gearUp = new DoubleSolenoid(Robot.robotMap.gearPickupUpOne, Robot.robotMap.gearPickupUpTwo);
	private DoubleSolenoid gearClamp = new DoubleSolenoid(Robot.robotMap.gearPickupCloseOne, Robot.robotMap.gearPickupCloseTwo);


	//private CANTalon gearMotor = new CANTalon(Robot.robotMap.gearPickupMotor);
	
	private DigitalInput gearSensor = new DigitalInput(Robot.robotMap.gearTrigger);
	
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
    
    public void setGearUp(boolean pState){
    	if(pState){
    		gearUp.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		gearUp.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void setGearClose(boolean pState){
    	if(pState){
    		gearClamp.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		gearClamp.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void setGearMotorSpeed(double pSpd){
    	gearMotor.set(pSpd);
    }
    
    public boolean getGearSensor(){
    	return gearSensor.get();
    }
}

