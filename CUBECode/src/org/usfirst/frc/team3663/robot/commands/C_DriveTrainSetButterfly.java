package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_DriveTrainSetButterfly extends Command {

    public C_DriveTrainSetButterfly() {
    	
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrainPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.ss_DriveTrainPneumatics.wheelsDown==false){
        	Robot.ss_DriveTrainPneumatics.setButterfly(true);
        	Robot.ss_DriveTrainPneumatics.wheelsDown= true;
        	}
    	else{
            Robot.ss_DriveTrainPneumatics.setButterfly(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
