package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_2_G extends CommandGroup {

    public CG_AutoB_2_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_DriveTrainEncoderDrive(200, 8)); // 77
        addSequential(new C_TimerWaitSec(.5));
        //addSequential(new C_GearClampSet(true));  mc commented out
        //addSequential(new C_GearUpPistonSet(true));
        //addSequential(new C_DriveTrainEncoderDrive(-46, 5));   mc commented out
        
    }
}
