package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_GearMotorTest extends CommandGroup {

    public CG_GearMotorTest() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new C_DriveTrainSetButterflyOff());
    	addParallel(new C_GearRunMotor(-1));
    	//addSequential(new C_GearClampSet(false));
        //addSequential(new C_GearUpPistonSet(true));
        //addSequential(new C_GearPickupLED(true));
        //addSequential(new C_GearWaitForSensor());

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
