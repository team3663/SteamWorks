package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearLiftAuto extends CommandGroup {

    public CG_GearLiftAuto() {
    	addSequential(new C_DriveTrainSetButterfly(false));
        addSequential(new C_GearClampSet(false));
        addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_GearPickupLED(true));
        
        addSequential(new C_GearRunMotor(1));
        
        addSequential(new C_GearClampSet(true));
        addSequential(new C_GearPickupLED(false));	
        addSequential(new C_GearUpPistonSet(false));
    }
}
