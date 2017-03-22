package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_1_G extends CommandGroup {

    public CG_AutoB_1_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_DriveTrainEncoderDrive(68, 6));
        addSequential(new C_Gyro(-50));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(46, 5));
        addSequential(new C_GearClampSet(true));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(-50, 5));
    }
}
