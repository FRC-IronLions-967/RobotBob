package frc.team967.robot.commands;

import frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroPIDTurnToAngle extends Command {

	private double Angle;
	
    public GyroPIDTurnToAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kDriveBaseSubsystem);
    	Angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveBaseSubsystem.pidSetPoint(Angle);
    	Robot.kDriveBaseSubsystem.pidChangeState("Enable");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kDriveBaseSubsystem.move(Robot.kDriveBaseSubsystem.PIDOutput *.85, -Robot.kDriveBaseSubsystem.PIDOutput *.85);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveBaseSubsystem.pidDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kDriveBaseSubsystem.pidChangeState("Enable");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
