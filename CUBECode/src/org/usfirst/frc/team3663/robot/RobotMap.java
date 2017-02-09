package org.usfirst.frc.team3663.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
/***DRIVETRAIN PORTS***/	
	public static int driveMotorLeftOne = 0;
	public static int driveMotorLeftTwo = 1;
	public static int driveMotorRightOne = 2;
	public static int driveMotorRightTwo = 3;

	public static int driveTrainButterflyOne = 0;
	public static int driveTrainButterflyTwo = 1;
	public static int driveTrainGearShiftOne = 2;
	public static int driveTrainGearShiftTwo = 3;
	
/***PICKUP PORTS***/	
	public static int fuelPickupMotor = 10;
	
/***SHOOTER PORTS***/
	public static int shooterRotMotor  = 20;
	public static int shooterMainMotor = 6;
	public static int shooterMainMotor2= 5;

	public static int shooterZeroDIO = 0;
	
/***LIFT PORTS***/
	public static int liftMoveMotor = 8;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
