package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_ShooterFireAndLoad extends CommandGroup {

    public CG_ShooterFireAndLoad() {
    	addParallel(new C_ShooterHoldSpeed());
    	addParallel(new C_LiftMoveUp(1));
    }
}
