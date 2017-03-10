package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_AutoChoose extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private AnalogInput autoSelect = new AnalogInput(Robot.robotMap.autoAnalog);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public int getAnalogVal(){
    	return autoSelect.getAverageValue();
    }
    
    public void selectAuto(int pValue){
    	System.out.println(pValue);
    	if(pValue > 4 && pValue < 15){
    		
    	}
    	
    }
}

