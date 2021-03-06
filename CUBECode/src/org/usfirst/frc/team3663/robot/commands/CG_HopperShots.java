package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_HopperShots extends CommandGroup {

    public CG_HopperShots() {
        // Add Commands here:
    	if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue){
    		addSequential(new C_ShooterGoToLocation(20));
        	addSequential(new C_ShooterSetVelocity(1480));
    	}
    	else{
    		addSequential(new C_ShooterGoToLocation(700));
        	addSequential(new C_ShooterSetVelocity(1480));
    	}
        addSequential(new CG_ShooterFireAndLoad());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
