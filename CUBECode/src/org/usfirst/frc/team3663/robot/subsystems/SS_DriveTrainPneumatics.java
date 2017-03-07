package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_DriveTrainPneumatics extends Subsystem {


	private DoubleSolenoid dropButterfly = new DoubleSolenoid(Robot.robotMap.driveTrainMain, Robot.robotMap.driveTrainButterflyOne, Robot.robotMap.driveTrainButterflyTwo);
	private DoubleSolenoid gearShift = new DoubleSolenoid(Robot.robotMap.driveTrainMain, Robot.robotMap.driveTrainGearShiftOne, Robot.robotMap.driveTrainGearShiftTwo);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean lowGear = false;
    public void toggleGearShift(boolean pState){
    	if(lowGear){
    		lowGear = false;
    		gearShift.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		lowGear = true;
    		gearShift.set(DoubleSolenoid.Value.kReverse);
    	}
    }

    public boolean wheelsDown = false;
    public void toggleButterfly(boolean pWheels){
    	if(pWheels){
    		//wheelsDown = false;
    		dropButterfly.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		//wheelsDown = true;
    		dropButterfly.set(DoubleSolenoid.Value.kReverse);
    	}
    }   
}

