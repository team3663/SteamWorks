package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_2_G extends CommandGroup {

    public CG_AutoB_2_G() {
        addSequential(new C_DriveTrainEncoderDrive(70, 7)); //110-40=70
        addSequential(new C_TimerWaitSec(.5));
        addSequential(new C_GearClampSet(true));
        //addSequential(new C_GearUpPistonSet(true));
        addSequential(new C_DriveTrainEncoderDrive(-46, 5));
        
    }
}
