package org.usfirst.frc.team3663.robot;



import org.usfirst.frc.team3663.robot.commands.CG_GearLiftAuto;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_GearDropOff;
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
import org.usfirst.frc.team3663.robot.commands.C_GearUpPistonSet;
import org.usfirst.frc.team3663.robot.commands.C_LiftMoveUp;
import org.usfirst.frc.team3663.robot.commands.C_ShooterMoveRotationAuto;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotFindZero;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotateToDegree;
import org.usfirst.frc.team3663.robot.commands.C_ShooterSetPiston;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**jj
 * This Sub System is fo all of the buttons see bottom for button layout.
 */
public class OI {
	public Joystick driveJoystick = new Joystick(0);
	public Joystick OPJoystick = new Joystick(1);

	
	public OI(){
/***DRIVE TRAIN BUTTONS***/
		Button driveTrainToggleButterfly = new JoystickButton(driveJoystick, 1);
		Button driveTrainToggleGearShift = new JoystickButton(driveJoystick, 9);
		Button driveTrainToggleDir = new JoystickButton(driveJoystick, 4);
		//Button GyroStartNeg = new JoystickButton( SpeedJoystick,6);
		//Button GyroStartPos = new JoystickButton(SpeedJoystick,5);
		
		driveTrainToggleButterfly.whenReleased(new C_DriveTrainSetButterfly(Robot.ss_DriveTrainPneumatics.wheelsDown));
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
		Button shooterFireAndLoad = new JoystickButton(OPJoystick, 1);
		//Button shooterZeroRotation = new JoystickButton(OPJoystick, 7);
		//Button shooterAutoFire = new JoystickButton(driveJoystick, 4);
		//Button shooterTest = new JoystickButton(driveJoystick, 1);
		
		//shooterUseDIO.whenPressed(new C_ShooterMoveRotationAuto());
		//shooterPresetTest.whenPressed(new C_ShooterHoldSpeed(1100));
		shooterFireAndLoad.whileHeld(new CG_ShooterFireAndLoad());
		//shooterZeroRotation.whenPressed(new C_ShooterRotFindZero());
		//shooterAutoFire.whileHeld(new CG_ShootBasedOnCam());
		//shooterTest.whenPressed(new C_ShooterSetPiston(Robot.ss_ShooterMainWheel.hoodUp));
		
/***LIFT BUTTONS***/
		//Button liftFuelUp = new JoystickButton(driveJoystick,1);
		
		//liftFuelUp.whileHeld(new C_LiftMoveUp(1));
		
/***GEAR PICKUP BUTTONS***/
		Button gearPickup = new JoystickButton(driveJoystick, 3); //Driver Pickup
		Button gearDrop= new JoystickButton(driveJoystick, 5); //Driver Drop
		
		Button gearOPpickup = new JoystickButton(OPJoystick, 6);
		Button gearOPdrop= new JoystickButton(OPJoystick, 5);
		Button gearOpen = new JoystickButton(OPJoystick, 7);
		Button gearClose = new JoystickButton(OPJoystick, 8);
		
		Button gearLift = new JoystickButton(OPJoystick, 3);
//*********************************************************************
		gearPickup.whenPressed(new CG_GearLiftAuto()); //Driver Pickup
		gearDrop.whenPressed(new CG_GearDropOff()); //Driver Drop
		
		gearOPpickup.whenPressed(new CG_GearLiftAuto());
		gearOPdrop.whenPressed(new CG_GearDropOff());
		gearOpen.whenPressed(new C_GearClampSet(false));
		gearClose.whenPressed(new C_GearClampSet(true));

		Button autogear = new JoystickButton(OPJoystick, 1);
		autogear.whenPressed(new CG_AutoR_1_G());
		gearLift.whenPressed(new C_GearUpPistonSet(true));
		
		
/***CLIMBER MOTORS***/
		/**Button climb = new JoystickButton(OPJoystick, 4);
		Button climbRelease = new JoystickButton(OPJoystick, 2);
		
		climb.whileHeld(new C_ClimberSetSpeed(1));
		climbRelease.whileHeld(new C_ClimberSetSpeed(-.2));**/
		Button GyroTest = new JoystickButton(OPJoystick, 2);
		GyroTest.whenPressed(new C_Gyro(180));
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