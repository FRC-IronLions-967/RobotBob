package frc.team967.robot;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team967.robot.commands.auto.A_BlueCenter;
import frc.team967.robot.commands.auto.A_BlueLeft;
import frc.team967.robot.commands.auto.A_BlueRight;
import frc.team967.robot.commands.auto.A_DriveForward;
import frc.team967.robot.commands.auto.A_RedCenter;
import frc.team967.robot.commands.auto.A_RedLeft;
import frc.team967.robot.commands.auto.A_RedRight;
import frc.team967.robot.subsystems.DriveBaseSubsystem;
import frc.team967.robot.subsystems.IntakeSubsystem;
import frc.team967.robot.subsystems.LiftSubsystem;
import frc.team967.robot.subsystems.WristSubsystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static RobotMap robotMap;
	public static RobotConstraints robotConstraints;
	public static final DriveBaseSubsystem kDriveBaseSubsystem = new DriveBaseSubsystem();
	public static final IntakeSubsystem kIntakeSubsystem = new IntakeSubsystem();
	public static final LiftSubsystem kLiftSubsystem = new LiftSubsystem();
    public static final WristSubsystem kWristsubsystem = new WristSubsystem();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		robotMap = new RobotMap();
		robotConstraints = new RobotConstraints();
		m_oi = new OI();
		
		m_chooser.addDefault("Default Auto", new A_DriveForward());
		m_chooser.addObject("Blue Left", new A_BlueLeft());
		m_chooser.addObject("Blue Center", new A_BlueCenter());
		m_chooser.addObject("Blue Right", new A_BlueRight());
		m_chooser.addObject("Red Left", new A_RedLeft());
		m_chooser.addObject("Red Center", new A_RedCenter());
		m_chooser.addObject("Red Right", new A_RedRight());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.kDriveBaseSubsystem.pidChangeState("Disable");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
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
	    kDriveBaseSubsystem.gyroZero();

		String command = SmartDashboard.getString("autonomous/selected", "Default Auto");
		switch(command) {
			case "Blue Left":
				m_autonomousCommand = new A_BlueLeft();
				break;
			case "Blue Center":
				m_autonomousCommand = new A_BlueCenter();
				break;
			case "Blue Right":
				m_autonomousCommand = new A_BlueRight();
				break;
			case "Red Left":
				m_autonomousCommand = new A_RedLeft();
				break;
			case "Red Center":
				m_autonomousCommand = new A_RedCenter();
				break;
			case "Red Right":
				m_autonomousCommand = new A_RedRight();
				break;
			case "Drive Forward":
			default:
				m_autonomousCommand = new A_DriveForward();
		}
		
		
//		m_autonomousCommand = m_chooser.getSelected();
		
//		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	
	public void log() {
		kDriveBaseSubsystem.log();
		kLiftSubsystem.log();
		SmartDashboard.putNumber("Voltage", RobotController.getBatteryVoltage());
		SmartDashboard.putNumber("timer", DriverStation.getInstance().getMatchTime());
	}
	
}
