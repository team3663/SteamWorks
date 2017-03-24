package org.usfirst.frc.team3663.robot;



import org.usfirst.frc.team3663.robot.commands.CG_GearLiftAuto;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_2_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_3_G;
import org.usfirst.frc.team3663.robot.commands.CG_GearDropOff;
import org.usfirst.frc.team3663.robot.commands.CG_ShooterFireAndLoad;

import org.usfirst.frc.team3663.robot.commands.C_AutoSelect;

import org.usfirst.frc.team3663.robot.commands.C_ClimberDriverButton;
import org.usfirst.frc.team3663.robot.commands.C_ClimberSetSpeed;
import org.usfirst.frc.team3663.robot.commands.C_DriveChangeDirectionToggle;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetGearShift;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainToggleButterfly;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainToggleGearShift;
import org.usfirst.frc.team3663.robot.commands.C_FuelPickupToggle;
import org.usfirst.frc.team3663.robot.commands.C_Gyro;
import org.usfirst.frc.team3663.robot.commands.C_ShooterHoldSpeed;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotFindZero;
import org.usfirst.frc.team3663.robot.commands.C_ShooterSetPiston;
import org.usfirst.frc.team3663.robot.commands.C_TurretLight;
import org.usfirst.frc.team3663.robot.commands.C_GearUpSet;
import org.usfirst.frc.team3663.robot.commands.C_GearClampSet;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;


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
		
		driveTrainToggleButterfly.whileHeld(new C_DriveTrainToggleButterfly());
		driveTrainToggleGearShift.whileHeld(new C_DriveTrainToggleGearShift());

		//driveToEncLoc.whenPressed(new C_DriveTrainEncoderDrive(200000));
	
	
/***FUEL PICKUP BUTTONS***/
		
		Button fuelPickupToggle = new JoystickButton(driveJoystick, 2);
		
		fuelPickupToggle.whenReleased(new C_FuelPickupToggle());
		
/***SHOOTER BUTTONS***/
		//Button shooterUseDIO = new JoystickButton(driveJoystick, 3);
	    //Button shooterPresetTest = new JoystickButton(driveJoystick, 10);
		Button shooterZeroRotation = new JoystickButton(OPJoystick, 7);
		Button ShooterLightSet= new JoystickButton(OPJoystick, 2);
		Button shooterFireAndLoad = new JoystickButton(OPJoystick, 6);
		//Button shooterTest = new JoystickButton(driveJoystick, 1);
		Button shooterSetHood = new JoystickButton(OPJoystick, 10); // 2 mc
		//Button shooterGoToLoc = new JoystickButton(OPJoystick, 1);
		
		//shooterUseDIO.whenPressed(new C_ShooterMoveRotationAuto());
		//shooterPresetTest.whenPressed(new C_ShooterHoldSpeed(1100));
		shooterZeroRotation.whenPressed(new C_ShooterRotFindZero());
		shooterFireAndLoad.whileHeld(new CG_ShooterFireAndLoad());
		//shooterAutoFire.whileHeld(new CG_ShootBasedOnCam());
		//shooterTest.whenPressed(new C_ShooterSetPiston(Robot.ss_ShooterMainWheel.hoodUp));
		shooterSetHood.whenPressed(new C_ShooterSetPiston(Robot.ss_ShooterMainWheel.hoodUp));
		//shooterGoToLoc.whileHeld(new CG_AutoB_3_B());
		ShooterLightSet.whenPressed(new C_TurretLight(true));
		
		
/***LIFT BUTTONS***/
		//Button liftFuelUp = new JoystickButton(driveJoystick,1);
		
		//liftFuelUp.whileHeld(new C_LiftMoveUp(1));
		
/***GEAR PICKUP BUTTONS***/
		Button gearPickup = new JoystickButton(driveJoystick, 3); //Driver Pickup
		Button gearDrop= new JoystickButton(driveJoystick, 5); //Driver Drop
		
		gearPickup.whenPressed(new CG_GearLiftAuto()); //Driver Pickup
		gearDrop.whenPressed(new CG_GearDropOff()); //Driver Drop
		

		
// Start 
		Button getAnalogVal = new JoystickButton(OPJoystick, 8);
		getAnalogVal.whenPressed(new C_AutoSelect());
		
/***CLIMBER MOTORS***/
		Button climb = new JoystickButton(OPJoystick, 4);
		Button climbSafeButton = new JoystickButton(driveJoystick, 7);
		//Button climbRelease = new JoystickButton(OPJoystick, 2);
		
		climb.whileHeld(new C_ClimberSetSpeed(1));
		climbSafeButton.whenPressed(new C_ClimberDriverButton());
		//climbRelease.whileHeld(new C_ClimberSetSpeed(-.2));
		
		
		//Button GyroTest = new JoystickButton(OPJoystick, 2);

		//GyroTest.whenPressed(new C_Gyro(175));

/***Gyro Test***/
		//Button gyroTest = new JoystickButton(OPJoystick, 1);
		//gyroTest.whenPressed(new C_Gyro(90));
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
 * 9 = LEFT-STICK		as a side note please refrain from using 9-10 
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