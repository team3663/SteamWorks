package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_2_G extends CommandGroup {

    public CG_AutoB_2_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_DriveTrainEncoderDrive(77, 4)); //110-40=70 competition adjustment, 77
        addSequential(new C_TimerWaitSec(.5));
        //addSequential(new C_GearClampSet(true));  
        //addSequential(new C_GearUpPistonSet(true));
        
    	addSequential(new CG_GearDropOff());
        addParallel(new C_ShooterHoldSpeed());
        addSequential(new C_DriveTrainEncoderDrive(-46, 3));
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(745));
        addSequential(new C_ShooterSetVelocity(1700));
        addSequential(new C_ShooterSetPiston(true));
        addSequential(new CG_ShooterFireAndLoad());
        
    }
}
