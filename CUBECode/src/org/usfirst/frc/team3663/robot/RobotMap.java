package org.usfirst.frc.team3663.robot;

/**
 * If the motor number is 19 that means that it will not be assigned to any thing
 */
public class RobotMap {
	
/***DRIVETRAIN PORTS***/	
	//motors
	public static int driveMotorLeftOne = 0;
	public static int driveMotorLeftTwo = 1;
	public static int driveMotorRightOne = 2;
	public static int driveMotorRightTwo = 3;
	//pneumatics
	public static int driveTrainMain = 0;
	public static int driveTrainButterflyOne = 6;
	public static int driveTrainButterflyTwo = 7;
	public static int driveTrainGearShiftOne = 4;
	public static int driveTrainGearShiftTwo = 5;
	//encoders
	public static int driveTrainEncoderLeftOne 	= 2;
	public static int driveTrainEncoderLeftTwo 	= 3;
	public static int driveTrainEncoderRightOne = 0;
	public static int driveTrainEncoderRightTwo = 1;
	
/***PICKUP PORTS***/	
	//motors
	public static int fuelPickupMotor = 10;
	
/***SHOOTER PORTS***/
	//motors
	public static int shooterRotMotor  = 7;	
	public static int shooterMainMotor = 12;		//has encoder attached
	public static int shooterMainMotor2= 6;
	//digtial Input
	public static int shooterZeroDIO = 7;
	public static int shooterTurnLeftDIO = 9;
	public static int shooterTurnRightDIO = 8;
	//Encoder
	public static int shooterRotEncOne = 4;
	public static int shooterRotEncTwo = 5;
	//pneumatic
	public static int shooterMain = 1;
	public static int shooterPistonOne = 0;
	public static int shooterPistonTwo = 1;
	
/***LIFT PORTS***/
	//motor
	public static int liftFeedMotor = 8;
	public static int liftYellowMotor = 9;
	
/***CLIMBER PORTS***/
	//motor
	public static int climberMotor = 4;
	public static int climberMotor2 = 5;
	
/***GearPickup***/
	//motors
	public static int gearPickupMotor = 11;
	//pneumatics
	public static int gearMain = 0;
	public static int gearPickupUpOne = 2;
	public static int gearPickupUpTwo = 3;
	public static int gearPickupCloseOne = 0;
	public static int gearPickupCloseTwo = 1;
	//DIO
	public static int gearTrigger = 20;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
