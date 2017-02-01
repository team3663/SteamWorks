package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon liftMotor = new CANTalon(Robot.robotMap.liftMoveMotor);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeedLiftMotor(double pSpeed){
    	liftMotor.set(pSpeed);
    }
}

