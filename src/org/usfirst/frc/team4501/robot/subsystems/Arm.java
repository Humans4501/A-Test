package org.usfirst.frc.team4501.robot.subsystems;

import org.usfirst.frc.team4501.robot.RobotMap;
import org.usfirst.frc.team4501.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon talon1, talon2;
	
	RobotDrive intakeSystem;

	AnalogPotentiometer pot;

	
	
	double potAngle;

	
	public Arm() {
		talon1 = new Talon(RobotMap.TALON5);
		talon2 = new Talon(RobotMap.TALON6);
		
		intakeSystem = new RobotDrive(talon1, talon2);
	
		
		pot = new AnalogPotentiometer(RobotMap.SENSORS.POTENTIOMETERA, RobotMap.SENSORS.POTENTIOMETERB, RobotMap.SENSORS.POTENTIOMETERC);	
	}

	public void intakeLeft() {	
		talon1.set(-1);
		}
	
	public void outtakeLeft() {
		talon1.set(1);
	}
	
	public void intakeRight() {
		talon2.set(-1);
	}
	
	public void outtakeRight() {
		talon2.set(1);
	}
	
	public void intakeStop() {
		intakeSystem.tankDrive(0, 0);
	}
		 
	
	public double potAngle() {
		potAngle = pot.get();
		return potAngle;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeStop());
    }



}

