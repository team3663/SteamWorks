package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_ShooterTele extends CommandGroup {

    public CG_ShooterTele() {
    	addParallel(new C_ShooterMoveRotationTeleop());
    	addParallel(new C_ShooterSpeedSet());
    }
}
