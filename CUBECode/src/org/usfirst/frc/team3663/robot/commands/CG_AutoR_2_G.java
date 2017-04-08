package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_2_G extends CommandGroup {

    public CG_AutoR_2_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_DriveTrainEncoderDrive(77, 3)); // 77
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_GearClampSet(true));  
        //addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_ShooterSetVelocity(1550));
        addParallel(new C_ShooterHoldSpeed());
        addSequential(new C_DriveTrainEncoderDrive(-24, 2)); // 77
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(145));
        addSequential(new C_ShooterSetPiston(true));
        addSequential(new CG_ShooterFireAndLoad());
        }
}
