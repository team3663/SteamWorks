package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
        addSequential(new C_DriveTrainEncoderDrive(46, 5));
        addSequential(new C_Gyro(-54));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(73, 7));
        addSequential(new C_GearClampSet(false));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_DriveTrainEncoderDrive(-50, 5));
        //addSequential(new C_GearUpPistonSet(true));
    }
}
