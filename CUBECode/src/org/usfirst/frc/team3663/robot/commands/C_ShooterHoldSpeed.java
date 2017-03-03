package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterHoldSpeed extends Command {

	private int velocity;
    public C_ShooterHoldSpeed(int pVel) {
        // Use requires() here to declare subsystem dependencies
    	velocity = pVel;
    	requires(Robot.ss_ShooterMainWheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_ShooterMainWheel.resetMainMotorEncoder(velocity);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_ShooterMainWheel.mainMotorStayAtVel(velocity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_ShooterMainWheel.setSpeedMainMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
