package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterRotFindZero extends Command {

    public C_ShooterRotFindZero() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_ShooterRotation);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("sgjdfpiagjpiowesdgoaihjw");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("oiedfgjoihjwe");
    	Robot.ss_ShooterRotation.setSpeedRotationMotor(-.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ss_ShooterRotation.zeroEncLimit()){
    		Robot.ss_ShooterRotation.isZeroFound = true;
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_ShooterRotation.setSpeedRotationMotor(0);
    	Robot.ss_ShooterRotation.advRotResetEnc();
    	System.out.println("Found Shooter Rotation Zero");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
