package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_ShooterFireAndLoad extends CommandGroup {

    public CG_ShooterFireAndLoad() {
    	addSequential(new C_TurretLight(false));
    	addParallel(new C_ShooterHoldSpeed());
    	addSequential(new C_TimerWaitSec(.5));
    	addSequential(new C_LiftMoveUp(.75));
    }
}
