package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearDropOff extends CommandGroup {

    public CG_GearDropOff() {
        addSequential(new C_GearClampSet(true));
        //addSequential(new C_GearPickupLED(true));	
        //addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_GearUpSet(true));
        addSequential(new C_TimerWaitSec(.2));
        addSequential(new C_GearUpSet(false));
        //commented out lines flick the pegs
    }
}
