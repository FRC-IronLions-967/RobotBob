package frc.team967.robot.commands.auto.subMain;

import frc.team967.robot.commands.*;
import frc.team967.robot.commands.auto.AutoEndResets;
import frc.team967.robot.commands.auto.AutoStartUpResets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_RedCenter_RRR extends CommandGroup {

    public A_RedCenter_RRR() {

        addSequential(new AutoStartUpResets());

//        addSequential(new IntakeArms("closed"));

        addSequential(new DriveStright(390*2,.5));

        addSequential(new ZeroEncodersDriveBase());
//
        addSequential(new GyroPIDTurnToAngle(60));
//
        addSequential(new DriveStright(390*4, .5));
//
        addSequential(new ZeroEncodersDriveBase());
//
        addSequential(new GyroPIDTurnToAngle(0));
//
        addSequential(new DriveStright(390*6, .5));
//
        addSequential(new ZeroEncodersDriveBase());

        addSequential(new MoveWrist(.5));

        addSequential(new MoveLift(1));

        addSequential(new MoveWheels(4));


//
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
