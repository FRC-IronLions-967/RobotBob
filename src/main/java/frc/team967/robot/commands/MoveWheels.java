package frc.team967.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.team967.robot.Robot;

public class MoveWheels extends TimedCommand {

    public MoveWheels(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.kIntakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.kIntakeSubsystem.intakePower(-.5);
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
