package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearLift extends CommandGroup {

    public CG_GearLift() {
        // Add Commands here:
    	addSequential(new C_DriveTrainSetButterflyOff());
        addSequential(new C_GearClampSet(false));
        addSequential(new C_GearUpPistonSet(true));
        addParallel(new C_GearRunMotor(-1));
        addSequential(new C_GearPickupLED(true));
        addSequential(new C_GearWaitForSensor());
        //addSequential(new C_TimerWaitMills(2));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_GearPickupLED(false));	
        addSequential(new C_GearUpPistonSet(false));
        addParallel(new C_GearRunMotor(0));
        
   
        //addSequential(new )
    }
}
