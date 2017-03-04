package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterSpeedSet extends Command {

    public C_ShooterSpeedSet() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ss_ShooterMainWheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }
    private boolean press = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.speedJoystick.getPOV()==0 && Robot.ss_ShooterMainWheel.SHOOTER_EST_MAX_SPEED > Robot.ss_ShooterMainWheel.targetvalue && press==false){
    		Robot.ss_ShooterMainWheel.targetvalue += 50;
    		press=true;
    	}
    	if (Robot.oi.speedJoystick.getPOV()==180 && Robot.ss_ShooterMainWheel.targetvalue >0 && press==false){
    		Robot.ss_ShooterMainWheel.targetvalue -= 50;
    		press=true;
    	}
    	System.out.println(Robot.ss_ShooterMainWheel.targetvalue);
    	if(Robot.oi.speedJoystick.getPOV()==-1){
    		press=false;
    	}
    	
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
