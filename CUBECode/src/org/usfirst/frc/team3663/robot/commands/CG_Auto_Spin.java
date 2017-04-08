package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto_Spin extends CommandGroup {

    public CG_Auto_Spin() {
        // USE ONLY FOR EXTREME CHALLANGE, DO NOT ATTEMPT FOR A NORMAL MATCH
    	addSequential(new C_DriveTrainEncoderDrive(50, 3));
        addSequential(new C_Gyro(1800));
    }
}
