package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_ShootBasedOnCam extends CommandGroup {

    public CG_ShootBasedOnCam() {
        addParallel(new C_ShooterMoveRotationAuto());
    }
}
