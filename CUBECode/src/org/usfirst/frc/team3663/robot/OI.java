package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetButterfly;
import org.usfirst.frc.team3663.robot.commands.C_DriveTrainSetGearShift;
import org.usfirst.frc.team3663.robot.commands.C_FuelPickupToggle;
import org.usfirst.frc.team3663.robot.commands.C_Gyro;
import org.usfirst.frc.team3663.robot.commands.C_LiftMoveUp;
import org.usfirst.frc.team3663.robot.commands.C_ShooterHoldSpeed;
import org.usfirst.frc.team3663.robot.commands.C_ShooterRotateToDegree;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This Sub System is fo all of the buttons see bottom for button layout.
 */
public class OI {
	public Joystick driveJoystick = new Joystick(0);
	public Button GyroStartNeg = new JoystickButton(driveJoystick,6);
	public Button GyroStartPos = new JoystickButton(driveJoystick,5);
	public OI(){
/***DRIVE TRAIN BUTTONS***/
		Button driveTrainToggleButterfly = new JoystickButton(driveJoystick, 1);
		Button driveTrainToggleGearShift = new JoystickButton(driveJoystick, 2);
		GyroStartNeg.whenPressed(new C_Gyro(-90));
		GyroStartPos.whenPressed(new C_Gyro(90));
		driveTrainToggleButterfly.whenReleased(new C_DriveTrainSetButterfly(Robot.ss_DriveTrainPneumatics.wheelsDown));
		driveTrainToggleGearShift.whenReleased(new C_DriveTrainSetGearShift(Robot.ss_DriveTrainPneumatics.lowGear));
		
/***FUEL PICKUP BUTTONS***/
		Button fuelPickupToggle = new JoystickButton(driveJoystick, 3);
		
		fuelPickupToggle.whenReleased(new C_FuelPickupToggle());
		
/***SHOOTER BUTTONS***/
		Button shooterPresetTest = new JoystickButton(driveJoystick, 4);
		
		shooterPresetTest.whenPressed(new C_ShooterHoldSpeed(1175));
		
/***LIFT BUTTONS***/
		Button liftFuelUp = new JoystickButton(driveJoystick,5);
		
		liftFuelUp.whileHeld(new C_LiftMoveUp());
		
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