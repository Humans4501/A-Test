package org.usfirst.frc.team4501.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4501.robot.commands.IntakeClose;
import org.usfirst.frc.team4501.robot.commands.IntakeGo;
import org.usfirst.frc.team4501.robot.commands.IntakeOpen;
import org.usfirst.frc.team4501.robot.commands.ShiftHigh;
import org.usfirst.frc.team4501.robot.commands.ShiftLow;
import org.usfirst.frc.team4501.robot.commands.Shoot;
import org.usfirst.frc.team4501.robot.subsystems.Intake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;
	
	XboxController controller = new XboxController(0);
	Joystick stick = new Joystick(1);
	
	//Shifting stuff
	Button shiftHigh = new JoystickButton(controller, controller.BUTTON_A);
	Button shiftLow = new JoystickButton(controller, controller.BUTTON_B);
	Button angles = new JoystickButton(controller, controller.BUTTON_X);
	
	//Shooty Stuff
	Button shoot = new JoystickButton(stick, TRIGGER);
	Button intake = new JoystickButton(stick, BUTTON_3);
	
	Button intakeOpen = new JoystickButton(stick, BUTTON_4);
	Button intakeClose = new JoystickButton(stick, BUTTON_5);
	
	public OI() {
		
		//Shifting
		shiftHigh.whenPressed(new ShiftHigh());
		shiftLow.whenPressed(new ShiftLow());
		
		//Shooting Stuff
		shoot.whenPressed(new Shoot());
		intake.whenPressed(new IntakeGo());
		
		//Intake Open and Close
		intakeOpen.whenPressed(new IntakeOpen());
		intakeClose.whenPressed(new IntakeClose());
		
		
	}
	
	public double getTriggers() {
		return controller.getRawAxis(XboxController.TRIGGER_R) - controller.getRawAxis(XboxController.TRIGGER_L);
	}
	

	public double getLeftXboxX() {
		return controller.getRawAxis(0);
	}
	
	public double getRightXboxY() {
		return controller.getRawAxis(5);
	}
	
	public double getStickY() {
		return -stick.getY();
	}
	

	
	}

