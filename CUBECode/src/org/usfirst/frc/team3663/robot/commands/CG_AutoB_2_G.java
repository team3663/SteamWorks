package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_2_G extends CommandGroup {

    public CG_AutoB_2_G() {
        addSequential(new C_DriveTrainEncoderDrive(70));
        addSequential(new C_Gyro(-57));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(70));
        addSequential(new C_GearClampSet(false));
        addSequential(new C_TimerWaitSec(1));
        addSequential(new C_DriveTrainEncoderDrive(-50));
        //addSequential(new C_GearUpPistonSet(true));
    }
}
