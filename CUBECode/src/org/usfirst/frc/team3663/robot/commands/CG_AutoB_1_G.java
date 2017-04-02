package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_1_G extends CommandGroup {

    public CG_AutoB_1_G() {
    	addParallel(new C_ShooterRotFindZero());
    	addSequential(new C_DriveTrainEncoderDrive(58, 7));
        addSequential(new C_Gyro(55));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(64, 5));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(-50, 5));
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(340));
        addSequential(new C_ShooterSetVelocity(1430));
        addSequential(new C_ShooterSetPiston(false));
        addSequential(new CG_ShooterFireAndLoad());
    }
}
