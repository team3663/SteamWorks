package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_2_G extends CommandGroup {

    public CG_AutoB_2_G() {
        addSequential(new C_DriveTrainEncoderDrive(76));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_GearClampSet(false));
        //addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_DriveTrainEncoderDrive(-46));
        addSequential(new C_DriveTrainEncoderDrive(46));
        
    }
}
