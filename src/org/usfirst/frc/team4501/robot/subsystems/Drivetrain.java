package org.usfirst.frc.team4501.robot.subsystems;

import org.usfirst.frc.team4501.robot.Robot;
import org.usfirst.frc.team4501.robot.RobotMap;
import org.usfirst.frc.team4501.robot.commands.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon talon1, talon2, talon3, talon4;
	RobotDrive drive;
	
	Solenoid sol1;
	
	ADXRS450_Gyro gyro;
		 	
	double gyroData;
	
	double targetOffsetAngle_Horizontal;
	double targetOffsetAngle_Vertical;
	double targetArea;
	double targetSkew;
	
	public Drivetrain() {
		talon1 = new Talon(RobotMap.TALON1);
		talon2 = new Talon(RobotMap.TALON2);
		talon3 = new Talon(RobotMap.TALON3);
		talon4 = new Talon(RobotMap.TALON4);
		
		drive = new RobotDrive(talon1, talon2, talon3, talon4);
		
		sol1 = new Solenoid(RobotMap.SOLENOIDS.HIGHGEAR);
		gyro = new ADXRS450_Gyro();
		
	}
	
	public void driveTime(double forward, double rotate) {
		drive.arcadeDrive(-forward, -rotate);
		drive.setSafetyEnabled(false);
	}
	
	public void shiftHigh() {
		sol1.set(true);
	}
	
	public void shiftLow() {
		sol1.set(false);
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double gyroData() {
		gyroData = gyro.getAngle();
		return gyroData;
	}
	
	public void autonomous() {
		if(gyroData > 90 && gyroData < 91) {
			drive.tankDrive(0,0);
		}else if (gyroData < 90){
			drive.tankDrive(0.5, 1);
		}else if (gyroData > 91) {
			drive.tankDrive(0.2, 0.4);
		}
	

	}
		

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
}