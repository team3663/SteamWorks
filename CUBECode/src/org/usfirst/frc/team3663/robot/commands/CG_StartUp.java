package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_StartUp extends CommandGroup {

    public CG_StartUp() {
        addSequential(new C_DriveTrainSetButterfly(false));
        addSequential(new C_DriveTrainSetGearShift(false));

    }
}
