package frc.team967.robot.commands;

import frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_TankDrive extends Command {

    public T_TankDrive() {
    	requires(Robot.kDriveBaseSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kDriveBaseSubsystem.tankDrive(Robot.m_oi.getXbox0().getRawAxis(0), Robot.m_oi.getXbox0().getRawAxis(1));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
