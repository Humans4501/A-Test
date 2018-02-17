package org.usfirst.frc.team4501.robot;

import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//DRIVETRAIN
	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	
	//INTAKE
	public static final int INTAKETALON_1 = 2;
	public static final int INTAKETALON_2 = 3;
	
	//CONVEYOR
	public static final int CONVEYORTALON_1 = 4;
	public static final int CONVEYORTALON_2 = 5;
	
	//SHOOTER
	public static final int SHOOTERTALON_1 = 6;
	public static final int SHOOTERTALON_2 = 7;
	
	
	//SOLENOID
	public static class SOLENOIDS {
		public static final int HIGHGEAR = 0; 
		public static final int INTAKESOL = 1;
	}
	
	public static class SENSORS {
		
		//POTENTIOMETER
		public static final int POTENTIOMETERA = 0;
		public static final int POTENTIOMETERB = 270;
		public static final int POTENTIOMETERC = 0;
		
		//GYRO
		public static final int GYRO = 1;
	}
		
}
		

