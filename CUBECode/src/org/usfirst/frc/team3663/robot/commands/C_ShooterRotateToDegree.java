package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterRotateToDegree extends Command {	
    public C_ShooterRotateToDegree() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.ss_Shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Shooter.resetMainMotorEncoder(-1400);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_Shooter.mainMotorStayAtVel(-1400);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
