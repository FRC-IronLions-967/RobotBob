package frc.team967.robot.commands.auto.subMain;

import frc.team967.robot.commands.DriveStright;
import frc.team967.robot.commands.GyroPIDTurnToAngle;
import frc.team967.robot.commands.auto.AutoEndResets;
import frc.team967.robot.commands.auto.AutoStartUpResets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_BlueCenter_RLR extends CommandGroup {

    public A_BlueCenter_RLR() {
    	
    	addSequential(new AutoStartUpResets());
    	
    	addSequential(new DriveStright(105 * 4.5, .6));
    	addSequential(new GyroPIDTurnToAngle(90));
    	addSequential(new DriveStright(105 * 3.3, .6));
    	addSequential(new GyroPIDTurnToAngle(-90));
    	
//    	addSequential(new LiftMove());
    	
    	addSequential(new DriveStright(105 * 4, .6));
    	
    	addSequential(new AutoEndResets());
    	
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
