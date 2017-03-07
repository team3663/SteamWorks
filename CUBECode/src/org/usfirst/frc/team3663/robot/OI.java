package org.usfirst.frc.team3663.robot;


import org.usfirst.frc.team3663.robot.commands.CG_GearDropOff;
import org.usfirst.frc.team3663.robot.commands.CG_GearLift;
import org.usfirst.frc.team3663.robot.commands.CG_GearLiftUp;
import org.usfirst.frc.team3663.robot.commands.CG_GearLoadSet;
import org.usfirst.frc.team3663.robot.commands.CG_ShootBasedOnCam;
import org.usfirst.frc.team3663.robot.commands.CG_ShooterFireAndLoad;
import org.usfirst.frc.team3663.robot.commands.C_ClimberSetSpeed;
import org.usfirst.frc.team3663.robot.commands.C_DriveChangeDirectionToggle;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainEncoderDrive;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetButterfly;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetGearShift;
import org.usfirst.frc.team3663.robot.commands.C_FuelPickupToggle;
import org.usfirst.frc.team3663.robot.commands.C_Gyro;
import org.usfirst.frc.team3663.robot.commands.C_GearClampSet;
import org.usfirst.frc.team3663.robot.commands.C_GearRunMotor;
import org.usfirst.frc.team3663.robot.commands.C_GearUpPistonSet;
import org.usfirst.frc.team3663.robot.commands.C_LiftMoveUp;
import org.usfirst.frc.team3663.robot.commands.C_ShooterMoveRotationAuto;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotFindZero;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotateToDegree;
import org.usfirst.frc.team3663.robot.commands.C_ShooterSetPiston;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This Sub System is fo all of the buttons see bottom for button layout.
 */
public class OI {
	public Joystick driveJoystick = new Joystick(0);
	public Joystick speedJoystick = new Joystick(1);
	public Joystick gearJoystick = new Joystick(2);

	
	public OI(){
/***DRIVE TRAIN BUTTONS***/
		Button driveTrainToggleButterfly = new JoystickButton(driveJoystick, 8);
		Button driveTrainToggleGearShift = new JoystickButton(driveJoystick, 9);
		Button driveTrainToggleDir = new JoystickButton(driveJoystick, 4);
		//Button GyroStartNeg = new JoystickButton(SpeedJoystick,6);
		//Button GyroStartPos = new JoystickButton(SpeedJoystick,5);
		
		driveTrainToggleButterfly.whenPressed(new C_DriveTrainSetButterfly());
		driveTrainToggleGearShift.whenReleased(new C_DriveTrainSetGearShift(Robot.ss_DriveTrainPneumatics.lowGear));

		//driveToEncLoc.whenPressed(new C_DriveTrainEncoderDrive(200000));

		//GyroStartNeg.whenPressed(new C_Gyro(-35));
		//GyroStartPos.whenPressed(new C_Gyro(35));		
		driveTrainToggleGearShift.whenReleased(new C_DriveTrainSetGearShift(Robot.ss_DriveTrainPneumatics.lowGear));
		driveTrainToggleDir.whenPressed(new C_DriveChangeDirectionToggle());
		
/***FUEL PICKUP BUTTONS***/
		
		Button fuelPickupToggle = new JoystickButton(driveJoystick, 2);
		
		fuelPickupToggle.whenReleased(new C_FuelPickupToggle());
		
/***SHOOTER BUTTONS***/
		//Button shooterUseDIO = new JoystickButton(driveJoystick, 3);
	    //Button shooterPresetTest = new JoystickButton(driveJoystick, 10);
		Button shooterFireAndLoad = new JoystickButton(speedJoystick, 1);
		Button shooterZeroRotation = new JoystickButton(speedJoystick, 7);
		//Button shooterAutoFire = new JoystickButton(driveJoystick, 4);
		//Button shooterTest = new JoystickButton(driveJoystick, 1);
		
		//shooterUseDIO.whenPressed(new C_ShooterMoveRotationAuto());
		//shooterPresetTest.whenPressed(new C_ShooterHoldSpeed(1100));
		shooterFireAndLoad.whileHeld(new CG_ShooterFireAndLoad());
		shooterZeroRotation.whenPressed(new C_ShooterRotFindZero());
		//shooterAutoFire.whileHeld(new CG_ShootBasedOnCam());
		//shooterTest.whenPressed(new C_ShooterSetPiston(Robot.ss_ShooterMainWheel.hoodUp));
		
/***LIFT BUTTONS***/
		//Button liftFuelUp = new JoystickButton(driveJoystick,1);
		
		//liftFuelUp.whileHeld(new C_LiftMoveUp(1));
		
/***GEAR PICKUP BUTTONS***/
		//Button gearUp = new JoystickButton(driveJoystick, 5);
		Button gearOpen = new JoystickButton(gearJoystick, 5);//3
		Button gearClose = new JoystickButton(gearJoystick, 6);//3
		Button gearPickup = new JoystickButton(driveJoystick, 3);
		Button gearOPpickup = new JoystickButton(gearJoystick, 3);
		Button gearLift = new JoystickButton(gearJoystick, 1);
		Button gearMotorReverse= new JoystickButton(gearJoystick, 4); //Reverse	
		Button gearSetGear= new JoystickButton(gearJoystick, 8);
		
		gearPickup.whenPressed(new CG_GearLift());
		gearOPpickup.whenPressed(new CG_GearLift());
		//gearSetGear.whenPressed(new CG_SetGear());
		gearLift.whenPressed(new C_GearUpPistonSet(false));
		gearLift.whenReleased(new C_GearUpPistonSet(true));
		gearOpen.whenPressed(new C_GearClampSet(false));
		gearClose.whenReleased(new C_GearClampSet(true));
		gearMotorReverse.whenPressed(new C_GearRunMotor(1)); //Reverse
		gearMotorReverse.whenReleased(new C_GearRunMotor(0)); //Reverse
		//Button gearOpen = new JoystickButton(driveJoystick, 5);//3
		//Button gearClose = new JoystickButton(driveJoystick, 6);//3
		Button gearAutoPickup = new JoystickButton(driveJoystick, 5);
		Button gearRelease = new JoystickButton(driveJoystick, 6);
		//Button gearLift = new JoystickButton(driveJoystick, 1);
		//Button gearMotorReverse= new JoystickButton(driveJoystick, 2); //Reverse	
		
		gearAutoPickup.whenPressed(new CG_GearLift());
		gearRelease.whenPressed(new CG_GearDropOff());
		gearRelease.whenReleased(new CG_GearLiftUp());
		//Button gearMotorRun = new JoystickButton(driveJoystick, 3);
		//Button gearLift = new JoystickButton(driveJoystick, 1);
		//Button gearMotorReverse= new JoystickButton(driveJoystick, 2); //Reverse	
		//gearLift.whenPressed(new C_GearUpPistonSet(true));
		//gearLift.whenReleased(new C_GearUpPistonSet(false));
		//gearUp.whenPressed(new C_GearUpPistonSet(true));
		//gearUp.whenReleased(new C_GearUpPistonSet(false));
		//gearOpen.whenPressed(new C_GearClampSet(true));
		//gearClose.whenReleased(new C_GearClampSet(false));
		//gearMotorReverse.whenPressed(new C_GearRunMotor(1)); //Reverse
		//gearMotorReverse.whenReleased(new C_GearRunMotor(0)); //Reverse


		//Button gearAutoLift = new JoystickButton(driveJoystick, 6);
		
		//gearAutoLift.whenPressed(new CG_GearLoadSet());
		//gearAutoLift.whenReleased(new CG_GearLift());
		
/***CLIMBER MOTORS***/
		Button climb = new JoystickButton(speedJoystick, 3);
		Button climbRelease = new JoystickButton(speedJoystick, 7);
		
		climb.whileHeld(new C_ClimberSetSpeed(1));
		climbRelease.whileHeld(new C_ClimberSetSpeed(-.2));
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