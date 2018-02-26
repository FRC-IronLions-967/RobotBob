package frc.team967.robot.commands.auto;

import frc.team967.robot.commands.GyroPIDChangeState;
import frc.team967.robot.commands.ZeroEncodersDriveBase;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team967.robot.commands.ZeroEncodersDriveBase;

/**
 *
 */
public class AutoStartUpResets extends CommandGroup {

    public AutoStartUpResets() {
    	
    	addSequential(new GyroPIDChangeState("Enable"));
    	addSequential(new ZeroEncodersDriveBase());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
