package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Gyro extends Command {
	private int turnValue;

    public C_Gyro(int pdes)
    {
    	requires(Robot.gyro);
    	requires(Robot.ss_DriveTrain);
    	turnValue = pdes;
    }
    protected void initialize()
    {
    	Robot.gyro.SetUpGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	Robot.gyro.TurnByGyro(turnValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
    	return Robot.gyro.TurnByGyro(turnValue);
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	Robot.ss_DriveTrain.driveRobot(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
