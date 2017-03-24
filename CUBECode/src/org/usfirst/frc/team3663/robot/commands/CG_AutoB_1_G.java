package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoB_1_G extends CommandGroup {

    public CG_AutoB_1_G() {
        addSequential(new C_DriveTrainEncoderDrive(68, 6)); //86-20=68 ||20 is the middle of the robot
        addSequential(new C_Gyro(-50)); 					//56
        
        addSequential(new C_TimerWaitSec(1));
        
        addSequential(new C_DriveTrainEncoderDrive(46, 4)); //81-40=41+5=46
        addSequential(new C_GearClampSet(true));
        
        addSequential(new C_TimerWaitSec(1));
        
        addSequential(new C_DriveTrainEncoderDrive(-50, 5));
        }
}
