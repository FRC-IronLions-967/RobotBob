package frc.team967.robot.commands.auto;

import frc.team967.lib.util.MatchData;
import frc.team967.robot.commands.DriveStright;
import frc.team967.robot.commands.GyroPIDTurnToAngle;
import frc.team967.robot.commands.auto.subMain.A_BlueCenter_LLL;
import frc.team967.robot.commands.auto.subMain.A_BlueCenter_LRL;
import frc.team967.robot.commands.auto.subMain.A_BlueCenter_RLR;
import frc.team967.robot.commands.auto.subMain.A_BlueCenter_RRR;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_BlueCenter extends CommandGroup {

    public A_BlueCenter() {
    	
    	MatchData.OwnedSide positionOne = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
    	MatchData.OwnedSide positionTwo = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
    	MatchData.OwnedSide positionThree = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
    	
//    	SmartDashboard.putBoolean("is in blue Center", true);

		addSequential(new GyroPIDTurnToAngle(40));
		addSequential(new DriveStright(1100,.5));

//    	if (positionOne == MatchData.OwnedSide.LEFT && positionTwo == MatchData.OwnedSide.LEFT && positionThree == MatchData.OwnedSide.LEFT) {
//        	addSequential(new A_BlueCenter_LLL());
//        }
//        else if (positionOne == MatchData.OwnedSide.RIGHT && positionTwo == MatchData.OwnedSide.LEFT && positionOne == MatchData.OwnedSide.RIGHT) {
//        	addSequential(new A_BlueCenter_RLR());
//        }
//        else if (positionOne == MatchData.OwnedSide.LEFT && positionTwo == MatchData.OwnedSide.RIGHT && positionThree == MatchData.OwnedSide.LEFT) {
//        	addSequential(new A_BlueCenter_LRL());
//        }
//        else if (positionOne == MatchData.OwnedSide.RIGHT && positionTwo == MatchData.OwnedSide.RIGHT && positionThree == MatchData.OwnedSide.RIGHT) {
//        	addSequential(new A_BlueCenter_RRR());
//        }
    }
}
