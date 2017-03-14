package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearRunMotor extends Command {
	
	private int speed;
    public C_GearRunMotor(int pSp) {
    	speed = pSp;
        requires(Robot.ss_GearPickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_GearPickup.setGearMotorSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.ss_GearPickup.getGearSensor();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_GearPickup.setGearMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
