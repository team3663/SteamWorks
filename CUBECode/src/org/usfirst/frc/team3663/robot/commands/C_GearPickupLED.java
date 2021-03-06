package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GearPickupLED extends Command {
	
	private boolean state=false;

    public C_GearPickupLED(boolean pState) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	state =pState;
    	requires (Robot.ss_GearPickup);
   
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_GearPickup.setLight(state);
    	if(Robot.ss_GearPickup.live){
    		CameraServer.getInstance().startAutomaticCapture("cam0",0);
    		Robot.ss_GearPickup.live = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
