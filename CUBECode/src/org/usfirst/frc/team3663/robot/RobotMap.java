package org.usfirst.frc.team3663.robot;

/**
 * If the motor number is 19 that means that it will not be assigned to any thing
 */
public class RobotMap {
	
/***DRIVETRAIN PORTS***/	
	//motors
	public static int driveMotorLeftOne = 7;
	public static int driveMotorLeftTwo = 11;
	public static int driveMotorRightOne = 4;
	public static int driveMotorRightTwo = 20;
	//pneumatics
	public static int driveTrainButterflyOne = 2;
	public static int driveTrainButterflyTwo = 3;
	public static int driveTrainGearShiftOne = 6;
	public static int driveTrainGearShiftTwo = 7;
	//encoders
	public static int driveTrainEncoderLeftOne 	= 4;
	public static int driveTrainEncoderLeftTwo 	= 5;
	public static int driveTrainEncoderRightOne = 6;
	public static int driveTrainEncoderRightTwo = 7;
	
/***PICKUP PORTS***/	
	//motors
	public static int fuelPickupMotor = 6;
	
/***SHOOTER PORTS***/
	//motors
	public static int shooterRotMotor  = 0;	
	public static int shooterMainMotor = 1;		//has encoder attached
	public static int shooterMainMotor2= 4;
	//digtial Input
	public static int shooterZeroDIO = 2;
	public static int shooterTurnLeftDIO = 20;
	public static int shooterTurnRightDIO = 25;
	//Encoder
	public static int shooterRotEncOne = 0;
	public static int shooterRotEncTwo = 1;
	
/***LIFT PORTS***/
	//motor
	public static int liftFeedMotor = 5;
	public static int liftYellowMotor = 3;
	
/***CLIMBER PORTS***/
	//motor
	public static int climberMotor = 19;
	public static int climberMotor2 = 19;
	
/***GearPickup***/
	//motors
	public static int gearPickupMotor = 2;
	//pneumatics
	public static int gearPickupUpOne = 4;
	public static int gearPickupUpTwo = 5;
	public static int gearPickupCloseOne = 0;
	public static int gearPickupCloseTwo = 1;
	//DIO
	public static int gearTrigger = 3;
	//Relay
	public static int pickupLED= 1;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
