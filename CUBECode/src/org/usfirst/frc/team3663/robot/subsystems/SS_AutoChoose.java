package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_2_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoB_3_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_1_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_2_G;
import org.usfirst.frc.team3663.robot.commands.CG_AutoR_3_G;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_AutoChoose extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private AnalogInput autoSelect = new AnalogInput(Robot.robotMap.autoAnalog);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public int getAnalogVal(){
    	//System.out.println(autoSelect.getAverageValue());
    	return autoSelect.getAverageValue();
    }
    
    public void selectAuto(int pValue){
    	System.out.println(pValue);
    	DriverStation.Alliance side = DriverStation.getInstance().getAlliance();
    	CommandGroup auto = null;
    	int value = getAnalogVal();
    	if(value > 0 && value < 500){
        	if(side == DriverStation.Alliance.Blue){
        		System.out.println("Blue Side");
        		auto = new CG_AutoB_1_G();
        		//auto = new CG_AutoB_2_G();
           	}
        	else{
        		auto = new CG_AutoR_1_G();
        		//auto = new CG_AutoR_2_G();
        		System.out.println("Red Side");
        	}    		
    	}
    	if(value > 500 && value < 1500){
    		if(side == DriverStation.Alliance.Blue){
        		System.out.println("Blue Center");
        		//auto = new CG_AutoB_1_G();
        		auto = new CG_AutoB_2_G();
           	}
        	else{
        		//auto = new CG_AutoR_1_G();
        		auto = new CG_AutoR_2_G();
        		System.out.println("Red Center");
        	}
    	}
    	if(value > 1500 && value < 3000){
    		if(side == DriverStation.Alliance.Blue){
        		System.out.println("Blue Why");
        		//auto = new CG_AutoB_1_G();
        		auto = new CG_AutoB_3_G();
           	}
        	else{
        		//auto = new CG_AutoR_1_G();
        		auto = new CG_AutoR_3_G();
        		System.out.println("Red Why");
        	}
    	}
    	System.out.println("Starting" + value);
    	if(auto != null){
    		auto.start();
    	}
    	
    }
}

