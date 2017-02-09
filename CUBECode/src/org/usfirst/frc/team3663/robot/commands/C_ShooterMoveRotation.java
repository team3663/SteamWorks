package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ShooterMoveRotation extends Command {

    public C_ShooterMoveRotation() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ss_Shooter);
        Robot.ss_Shooter.advRotResetEnc();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_Shooter.setRotMotorBreak(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_Shooter.advSetRotSpd(Robot.oi.driveJoystick.getRawAxis(4));
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
