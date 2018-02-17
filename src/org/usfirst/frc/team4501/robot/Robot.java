
package org.usfirst.frc.team4501.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4501.robot.commands.AutoCenterGroup;
import org.usfirst.frc.team4501.robot.commands.AutoLeftorRightGroup;
import org.usfirst.frc.team4501.robot.commands.DriveAutoTimed;
import org.usfirst.frc.team4501.robot.commands.VisionPID;
import org.usfirst.frc.team4501.robot.subsystems.Intake;
import org.usfirst.frc.team4501.robot.subsystems.Shooter;
import org.usfirst.frc.team4501.robot.subsystems.Conveyor;
import org.usfirst.frc.team4501.robot.subsystems.Drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Robot instance;
	RobotDrive myDrive = new RobotDrive(RobotMap.LEFT, RobotMap.RIGHT);

	public static final Drivetrain driveTrain = new Drivetrain();
	
	public static final Intake intake = new Intake();
	public static final Shooter shooter = new Shooter();
	public static final Conveyor conveyor = new Conveyor();
	
	public static OI oi;

	NetworkTable table;

		
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();



	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	
	@Override
	public void robotInit() {
		instance = this;
		oi = new OI();
		myDrive.setExpiration(0.1);
		NetworkTable.setIPAddress("10.95.1.55");
		table = NetworkTable.getTable("limelight");
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		chooser.addObject("Test Timed Auto", new DriveAutoTimed(1));
		chooser.addObject("Test VisionPID", new VisionPID());
		
		chooser.addObject("Center", new AutoCenterGroup());
		chooser.addObject("Left/Right", new AutoLeftorRightGroup());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		//SENSORS
		SmartDashboard.putNumber("Potentiometer", Robot.intake.potAngle());
		SmartDashboard.putNumber("Gyro", Robot.driveTrain.gyroData());
		
		//L I M E L I G H T
		double tx = table.getNumber("tx", 0);
		double ty = table.getNumber("ty", 0);
		double targetArea = table.getNumber("ta", 0);
		double targetSkew = table.getNumber("ts", 0);
		double targetView = table.getNumber("tv", 0);

		SmartDashboard.putNumber("targetView", targetView);
		SmartDashboard.putNumber("tx", tx);
		SmartDashboard.putNumber("ty", ty);
	}
	
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	
		}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Potentiometer", Robot.intake.potAngle());
		SmartDashboard.putNumber("Gyro", Robot.driveTrain.gyroData());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void setArcadeDrive(double move, double turn) {
		//TO DO: CHANGE 0 BACK TO TURN SO IT MOVES AND TURNS AT THE SAME TIME
		myDrive.arcadeDrive(move, turn);
	}
}