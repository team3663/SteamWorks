package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.CG_GearAutoPickup;
import org.usfirst.frc.team3663.robot.commands.CG_ShootBasedOnCam;
import org.usfirst.frc.team3663.robot.commands.CG_ShooterFireAndLoad;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainEncoderDrive;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetButterfly;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetGearShift;
import org.usfirst.frc.team3663.robot.commands.C_FuelPickupToggle;
import org.usfirst.frc.team3663.robot.commands.C_GearClampSet;
import org.usfirst.frc.team3663.robot.commands.C_GearRunMotor;
import org.usfirst.frc.team3663.robot.commands.C_GearUpPistonSet;
import org.usfirst.frc.team3663.robot.commands.C_LiftMoveUp;
import org.usfirst.frc.team3663.robot.commands.C_ShooterHoldSpeed;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotFindZero;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotateToDegree;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This Sub System is fo all of the buttons see bottom for button layout.
 */
public class OI {
	public Joystick driveJoystick = new Joystick(0);
	
	public OI(){
/***DRIVE TRAIN BUTTONS***/
		Button driveTrainToggleButterfly = new JoystickButton(driveJoystick, 8);
		Button driveTrainToggleGearShift = new JoystickButton(driveJoystick, 9);//1
		//Button driveToEncLoc = new JoystickButton(driveJoystick, 3);
		
		driveTrainToggleButterfly.whenReleased(new C_DriveTrainSetButterfly(Robot.ss_DriveTrainPneumatics.wheelsDown));
		driveTrainToggleGearShift.whenReleased(new C_DriveTrainSetGearShift(Robot.ss_DriveTrainPneumatics.lowGear));
		//driveToEncLoc.whenPressed(new C_DriveTrainEncoderDrive(200000));
		
/***FUEL PICKUP BUTTONS***/
		Button fuelPickupToggle = new JoystickButton(driveJoystick, 2);
		
		fuelPickupToggle.whenReleased(new C_FuelPickupToggle());
		
/***SHOOTER BUTTONS***/
		//Button shooterPresetTest = new JoystickButton(driveJoystick, 10);
		//Button shooterFireAndLoad = new JoystickButton(driveJoystick, 1);
		Button shooterZeroRotation = new JoystickButton(driveJoystick, 7);
		//Button shooterAutoFire = new JoystickButton(driveJoystick, 4);
		
		//shooterPresetTest.whenPressed(new C_ShooterHoldSpeed(1175));
		//shooterFireAndLoad.whileHeld(new CG_ShooterFireAndLoad());
		shooterZeroRotation.whenPressed(new C_ShooterRotFindZero());
		//shooterAutoFire.whileHeld(new CG_ShootBasedOnCam());
		
/***LIFT BUTTONS***/
		Button liftFuelUp = new JoystickButton(driveJoystick,1);
		
		liftFuelUp.whileHeld(new C_LiftMoveUp(1));
		
/***GEAR PICKUP BUTTONS***/
		Button gearUp = new JoystickButton(driveJoystick, 5);
		Button gearClose = new JoystickButton(driveJoystick, 6);//3
		Button gearMotorRun = new JoystickButton(driveJoystick, 4);
		Button gearMotorReverse= new JoystickButton(driveJoystick, 3);
		
		gearMotorReverse.whenPressed(new C_GearRunMotor(1));
		gearMotorReverse.whenReleased(new C_GearRunMotor(0));
		gearMotorRun.whenPressed(new CG_GearAutoPickup());
		gearUp.whenPressed(new C_GearUpPistonSet(true));
		gearUp.whenReleased(new C_GearUpPistonSet(false));
		gearClose.whenPressed(new C_GearClampSet(true));
		gearClose.whenReleased(new C_GearClampSet(false));
		
	}
}

/****BUTTON LAYOUT FOR XBOX****//*
 * 1 = A
 * 2 = B
 * 3 = X
 * 4 = Y
 * 5 = LEFT-BUMPER
 * 6 = RIGHT-BUMPER
 * 7 = BACK
 * 8 = START
 * 9 = LEFT-STICK		as a side note please reframe from using 9-10 
 * 10 = RIGHT-STICK		because the can cause issues with commands using
 * 						the stick axis	
 * AXIS ARE AS FOLLOWS
 * 0 = LEFT-X-AXIS
 * 1 = LEFT-Y-AXIS
 * 2 = LEFT-TRIGGER
 * 3 = RIGHT-TRIGGER
 * 4 = RIGHT-X-AXIS
 * 5 = RIGHT-Y-AXIS
 */