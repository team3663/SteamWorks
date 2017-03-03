package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class SS_GearLED extends Subsystem {
	private Relay spike = new Relay(Robot.robotMap.pickupLED);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setLight(boolean pValue){ //True is on
    	if(pValue){
    		spike.set(Relay.Value.kForward);
    	}
    	else{
    		spike.set(Relay.Value.kReverse);
    	}
    }
}

