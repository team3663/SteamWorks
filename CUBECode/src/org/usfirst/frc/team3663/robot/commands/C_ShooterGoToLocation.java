package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterGoToLocation extends Command {

	private int target = 0;
	
    public C_ShooterGoToLocation(int pTarget) {
    	target = pTarget;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_ShooterRotation);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_ShooterRotation.safeToShoot = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ss_ShooterRotation.moveRotationToValue(target);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_ShooterRotation.safeToShoot = true;
    	Robot.ss_ShooterRotation.advSetRotSpd(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
