package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	RobotDrive myRobot = new RobotDrive(0, 1); // class that handles basic drive
												// operations
	Joystick leftStick = new Joystick(0); // set to ID 1 in DriverStation

	public Robot() {
		myRobot.setSafetyEnabled(false);
	}

	/**
	 * Runs the motors with tank steering.
	 */
	@Override
	public void operatorControl() {
			myRobot.tankDrive(leftStick.getRawAxis(0), leftStick.getRawAxis(1));
	}
}
