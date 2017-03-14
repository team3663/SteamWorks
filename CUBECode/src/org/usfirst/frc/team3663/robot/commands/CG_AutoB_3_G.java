package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_3_G extends CommandGroup {

    public CG_AutoB_3_G() {
    	   addSequential(new C_DriveTrainEncoderDrive(70, 7));
           addSequential(new C_Gyro(54));
           addSequential(new C_TimerWaitSec(1));
           addSequential(new C_DriveTrainEncoderDrive(58, 5));
           addSequential(new C_GearClampSet(true));
           addSequential(new C_TimerWaitSec(1));
           addSequential(new C_DriveTrainEncoderDrive(-50, 5));
    }
}