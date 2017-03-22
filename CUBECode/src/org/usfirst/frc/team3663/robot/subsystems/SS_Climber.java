package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon climberMotor = new CANTalon(Robot.robotMap.climberMotor);
	CANTalon climberMotor2 = new CANTalon(Robot.robotMap.climberMotor2);
	private boolean driverPressedButton = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setButtonPessed(){
    	driverPressedButton = true;
    }
    
    public void setClimberSpeed(double pSpd){
    	if(driverPressedButton){
	    	climberMotor.set(pSpd);
	    	climberMotor2.set(pSpd);
    	}
    }
}

