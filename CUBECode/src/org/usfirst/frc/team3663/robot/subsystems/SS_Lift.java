package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon liftFeedMotor = new CANTalon(Robot.robotMap.liftFeedMotor);
	CANTalon liftYellowMotor = new CANTalon(Robot.robotMap.liftYellowMotor);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeedLiftFeedMotor(double pSpd){//`1650, 735
    	liftFeedMotor.set(-pSpd);
    }
    
    public void setSpeedLiftYellowMotor(double pSpd){
    	liftYellowMotor.set(-pSpd);				//MC changed from 1 to 10 for new wheel
    }
    
    public void setTotalLiftSpeed(double pSpd){
    	if(Robot.ss_ShooterMainWheel.atSpeed){
        	setSpeedLiftFeedMotor(pSpd);
        	setSpeedLiftYellowMotor(-pSpd/2);    		
    	}
    	else{
        	setSpeedLiftFeedMotor(0);
        	setSpeedLiftYellowMotor(0);       		
    	}
    }
}

