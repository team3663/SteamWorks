package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_DriveTrainEncoderDrive extends Command {

	public int encFinal = 0;
	
    public C_DriveTrainEncoderDrive(int pEncLoc) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_DriveTrain);
        encFinal = pEncLoc;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_DriveTrain.resetBothEnc();
    	Robot.ss_DriveTrain.advStartEncDrive(encFinal);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_DriveTrain.advDriveToLoc();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ss_DriveTrain.advDriveOverLoc();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
