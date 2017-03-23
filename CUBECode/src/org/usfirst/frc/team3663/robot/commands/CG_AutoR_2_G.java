package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_2_G extends CommandGroup {

    public CG_AutoR_2_G() {
    	addParallel(new C_ShooterRotFindZero());
        addSequential(new C_DriveTrainEncoderDrive(73, 7));
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_GearUpSet(false));
        //addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_DriveTrainEncoderDrive(-46, 5));
        }
}
