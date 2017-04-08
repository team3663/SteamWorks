package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
    	addParallel(new C_ShooterRotFindZero());
    	addSequential(new C_DriveTrainEncoderDrive(61, 3));
        addSequential(new C_Gyro(-55));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(70, 4));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_TimerWaitSec(.5));
        addParallel(new C_ShooterHoldSpeed());
        addSequential(new C_DriveTrainEncoderDrive(-54, 3));
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(530));
        addSequential(new C_ShooterSetVelocity(1400));
        addSequential(new C_ShooterSetPiston(false));
        addSequential(new CG_ShooterFireAndLoad());
    }
}
