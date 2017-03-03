package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearAutoPickup extends CommandGroup {

    public CG_GearAutoPickup() {
        // Add Commands here:
        addSequential(new C_GearClampSet(false));
        addSequential(new C_GearUpPistonSet(false));
        addParallel(new C_GearRunMotor(-1));
        addSequential(new C_GearPickupLED(true));
        addSequential(new C_GearWaitForSensor());
        //addSequential(new C_TimerWaitMills(2));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_GearPickupLED(false));	
        addSequential(new C_GearRunMotor(0));
        
   
        //addSequential(new )
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
