package org.usfirst.frc.team4501.robot.subsystems;

import org.usfirst.frc.team4501.robot.RobotMap;
import org.usfirst.frc.team4501.robot.commands.IntakeGo;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon talon1, talon2;
	
	Solenoid intakeSol;
	
	RobotDrive intakeSystem;

	AnalogPotentiometer pot;

	
	
	double potAngle;

	
	public Intake() {
    	talon1 = new Talon(RobotMap.INTAKETALON_1);
    	talon2 = new Talon(RobotMap.INTAKETALON_2);
    	
    	intakeSol = new Solenoid(RobotMap.SOLENOIDS.INTAKESOL);
//		
		intakeSystem = new RobotDrive(talon1, talon2);
	
		
//		pot = new AnalogPotentiometer(RobotMap.SENSORS.POTENTIOMETERA, RobotMap.SENSORS.POTENTIOMETERB, RobotMap.SENSORS.POTENTIOMETERC);
		
	}
	
	
	public void intakeGo() {
		talon1.set(1);
		talon2.set(1);
	}
	
	public void intake(double leftValue, double rightValue) {
		intakeSystem.tankDrive(leftValue, rightValue);;
	}	 
	
	public void intakeOpen() {
		intakeSol.set(true);
	}
	
	public void intakeClose() {
		intakeSol.set(false);
	}
	
	
	public double potAngle() {
		potAngle = pot.get();
		return potAngle;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeGo());
    }



}

