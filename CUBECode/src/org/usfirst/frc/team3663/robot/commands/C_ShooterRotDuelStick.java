package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterRotDuelStick extends Command {

    public C_ShooterRotDuelStick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_ShooterRotation);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_ShooterRotation.convertToTicks(Robot.oi.OPJoystick.getRawAxis(1), Robot.oi.OPJoystick.getRawAxis(0));
    	Robot.ss_ShooterRotation.moveRotationToValue((int)Robot.ss_ShooterRotation.targetTick);
    	Robot.ss_ShooterRotation.giveUsingZeroLoc(Robot.oi.OPJoystick.getRawAxis(1), Robot.oi.OPJoystick.getRawAxis(0));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_ShooterRotation.advSetRotSpd(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
