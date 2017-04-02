package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterHoldSpeed extends Command {

    public C_ShooterHoldSpeed() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_ShooterMainWheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_ShooterMainWheel.shooting = true;
     	Robot.ss_ShooterMainWheel.currentSpeed = (((double)Robot.ss_ShooterMainWheel.targetValue/(double)Robot.ss_ShooterMainWheel.SHOOTER_EST_MAX_SPEED)-.2);
    	Robot.ss_ShooterMainWheel.resetMainMotorEncoder(Robot.ss_ShooterMainWheel.targetValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_ShooterMainWheel.mainMotorStayAtVel(Robot.ss_ShooterMainWheel.targetValue);
    	Robot.ss_ShooterMainWheel.setTargetVal(Robot.oi.OPJoystick.getPOV());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_ShooterMainWheel.setSpeedMainMotor(0);
    	Robot.ss_ShooterMainWheel.shooting = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
