package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_3_G extends CommandGroup {

    public CG_AutoB_3_G() {
    	addParallel(new C_ShooterRotFindZero());
    	System.out.println("alsdjkf: 1");
    	addSequential(new C_DriveTrainEncoderDrive(61, 3));
    	System.out.println("alsdjkf: 2");
        addSequential(new C_Gyro(-55));
    	System.out.println("alsdjkf: 3");
        addSequential(new C_TimerWaitSec(.5));
    	System.out.println("alsdjkf: 4");
        addSequential(new C_DriveTrainEncoderDrive(70, 4));
    	System.out.println("alsdjkf: 5");
        addSequential(new C_GearClampSet(true));
    	System.out.println("alsdjkf: 6");
        addSequential(new C_TimerWaitSec(.5));
    	System.out.println("alsdjkf: 7");
        addSequential(new C_DriveTrainEncoderDrive(-54, 3));
    	System.out.println("alsdjkf: 8");
    }
}