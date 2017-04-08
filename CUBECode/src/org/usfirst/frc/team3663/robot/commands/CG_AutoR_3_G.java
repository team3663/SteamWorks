package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_3_G extends CommandGroup {

    public CG_AutoR_3_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_ShooterSetVelocity(1430));
    	addSequential(new C_DriveTrainEncoderDrive(67, 3));
        addSequential(new C_Gyro(55));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(67, 3));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(-50, 3));
    }
}
