
package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.CG_AutoB_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_2_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_2_G;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainEncoderDrive;
import org.usfirst.frc.team3663.robot.subsystems.SS_AutoChoose;
import org.usfirst.frc.team3663.robot.subsystems.SS_Climber;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrainPneumatics;
import org.usfirst.frc.team3663.robot.subsystems.SS_FuelPickup;
import org.usfirst.frc.team3663.robot.subsystems.SS_GearPickup;
import org.usfirst.frc.team3663.robot.subsystems.SS_Lift;
import org.usfirst.frc.team3663.robot.subsystems.SS_ShooterMainWheel;
import org.usfirst.frc.team3663.robot.subsystems.SS_ShooterRotation;
import org.usfirst.frc.team3663.robot.subsystems.SS_Timer;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static RobotMap robotMap;
	public static SS_DriveTrain ss_DriveTrain;
	public static SS_FuelPickup ss_FuelPickup;
	public static SS_DriveTrainPneumatics ss_DriveTrainPneumatics;
	public static SS_ShooterRotation ss_ShooterRotation;
	public static SS_ShooterMainWheel ss_ShooterMainWheel;
	public static SS_Lift ss_Lift;
	public static SS_AutoChoose ss_AutoChoose;
	public static SS_Timer ss_Timer;
	public static SS_Climber ss_Climber;
	public static SS_GearPickup ss_GearPickup;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		robotMap = new RobotMap();
		ss_FuelPickup = new SS_FuelPickup();
		ss_DriveTrainPneumatics = new SS_DriveTrainPneumatics();
		ss_DriveTrain = new SS_DriveTrain();
		ss_ShooterRotation = new SS_ShooterRotation();
		ss_ShooterMainWheel = new SS_ShooterMainWheel();
		ss_Lift = new SS_Lift();
		ss_Timer = new SS_Timer();
		ss_Climber = new SS_Climber();
		ss_GearPickup = new SS_GearPickup();
		ss_AutoChoose = new SS_AutoChoose();
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//C_DriveTrainEncoderDrive test = new C_DriveTrainEncoderDrive(62, 13);
		CG_AutoB_2_G test= new CG_AutoB_2_G();
		test.start();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//ss_ShooterRotation.isZeroFound = false;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
