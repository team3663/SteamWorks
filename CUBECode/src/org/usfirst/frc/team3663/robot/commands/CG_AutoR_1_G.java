package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
    	addParallel(new C_ShooterRotFindZero());
    	addSequential(new C_DriveTrainEncoderDrive(73, 7));
        addSequential(new C_Gyro(-61));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(58, 5));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(-50, 5));
    }
}
