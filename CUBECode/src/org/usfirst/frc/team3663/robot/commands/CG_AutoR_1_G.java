package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
    	   addSequential(new C_DriveTrainEncoderDrive(70, 7));
           addSequential(new C_Gyro(-54));
           addSequential(new C_TimerWaitSec(1));
           addSequential(new C_DriveTrainEncoderDrive(58, 5));
           addSequential(new C_GearUpSet(false));
           addSequential(new C_TimerWaitSec(1));
           addSequential(new C_DriveTrainEncoderDrive(-50, 5));
    }
}
