package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_3_B extends CommandGroup {

    public CG_AutoB_3_B() {
        // Add Commands here:
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(700));
        addSequential(new C_ShooterSetVelocity(1400));
    	addParallel(new C_ShooterHoldSpeed());
    	addSequential(new C_TimerWaitSec(.5));
    	addParallel(new C_LiftMoveUp(.75));
        addSequential(new C_TimerWaitSec(5));
        addSequential(new C_DriveTrainEncoderDrive(73, 7));
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
