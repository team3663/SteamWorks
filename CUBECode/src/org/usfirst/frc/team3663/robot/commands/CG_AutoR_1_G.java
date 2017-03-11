package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoR_1_G extends CommandGroup {

    public CG_AutoR_1_G() {
        // Add Commands here
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        //addSequential(new C_DriveTrainEncoderDrive(12));
        addSequential(new C_Gyro(60));
        //addSequential(new C_DriveTrainEncoderDrive(12));
        addSequential(new C_GearClampSet(false));
        addSequential(new C_TimerWaitSec(.7));
        //addSequential(new C_DriveTrainEncoderDrive(-12));
        //addSequential(new C_TimerWaitSec(.2));
        addSequential(new C_GearUpPistonSet(true));
    }
}
