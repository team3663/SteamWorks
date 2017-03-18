package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearLiftAuto extends CommandGroup {

    public CG_GearLiftAuto() {
    	addSequential(new C_DriveTrainSetButterfly(true)); 
        addSequential(new C_GearUpSet(true));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_GearPickupLED(false));
        
        addSequential(new C_GearRunMotor(-1)); //wait for sensor is embedded in gear run motor
        
        addSequential(new C_GearUpSet(false));
        addSequential(new C_GearPickupLED(true));	
        addSequential(new C_GearClampSet(false)); 
    }
}
