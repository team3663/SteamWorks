package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Timer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private double endTime = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setEndTime(double pMillSec){
    	endTime = Timer.getFPGATimestamp() + pMillSec;
    }
    
    public boolean hitTime(double pMillSec){
    	return (Timer.getFPGATimestamp() > endTime);
    }
}

