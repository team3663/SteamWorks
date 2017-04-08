package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_2_S extends CommandGroup {

    public CG_AutoR_2_S() {
        addSequential(new C_ShooterRotFindZero());
        addSequential(new C_ShooterGoToLocation(745));
        addSequential(new C_ShooterSetVelocity(1700));
        addSequential(new C_ShooterSetPiston(true));
        addParallel(new CG_ShooterFireAndLoad());
        addSequential(new C_TimerWaitSec(7));
        addSequential(new C_DriveTrainEncoderDrive(77, 4)); //110-40=70 competition adjustment, 77
        addSequential(new C_TimerWaitSec(.5));
    	addSequential(new CG_GearDropOff());
        addParallel(new C_ShooterHoldSpeed());
        addSequential(new C_DriveTrainEncoderDrive(-46, 3));
    }
}
