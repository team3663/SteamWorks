package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearDropOff extends CommandGroup {

    public CG_GearDropOff() {
        addSequential(new C_GearClampSet(false));
        addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_GearPickupLED(false));	
        addSequential(new C_TimerWaitSec(.3));
        addSequential(new C_GearUpPistonSet(false));
        //commented out lines flick the pegs
    }
}
