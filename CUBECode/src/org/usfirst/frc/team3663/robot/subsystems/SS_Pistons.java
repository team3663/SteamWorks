package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Pistons extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private DoubleSolenoid pistonOne = new DoubleSolenoid(Robot.robotMap.pistonOneLocOne, Robot.robotMap.pistonOneLocTwo);
	private DoubleSolenoid pistonTwo = new DoubleSolenoid(Robot.robotMap.pistonTwoLocOne, Robot.robotMap.pistonTwoLocTow);
	
	private boolean pistonOneFired = false;
	private boolean pistonTwoFired = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void triggerPistonOne(){
    	if(pistonOneFired){
    		pistonOne.set(DoubleSolenoid.Value.kForward);
    		pistonOneFired = false;
    	}else{
    		pistonOne.set(DoubleSolenoid.Value.kReverse);
    		pistonOneFired = true;
    	}
    }
    
    public void triggerPistonTwo(){
    	if(pistonTwoFired){
    		pistonTwo.set(DoubleSolenoid.Value.kForward);
    		pistonTwoFired = false;
    	}else{
    		pistonTwo.set(DoubleSolenoid.Value.kReverse);
    		pistonTwoFired = true;
    	}
    }
    
}

