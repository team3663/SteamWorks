package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
        addSequential(new C_DriveTrainEncoderDrive(96));
        addSequential(new C_Gyro(-58));
        addSequential(new C_DriveTrainEncoderDrive(46));
        addSequential(new C_GearClampSet(false));
        addSequential(new C_TimerWaitSec(.7));
        addSequential(new C_DriveTrainEncoderDrive(-46));
        //addSequential(new C_GearUpPistonSet(true));
    }
}
