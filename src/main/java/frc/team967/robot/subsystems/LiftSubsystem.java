package frc.team967.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team967.robot.RobotConstraints;
import frc.team967.robot.RobotMap;
import frc.team967.robot.commands.LiftMove;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	private WPI_TalonSRX liftLead;
	private WPI_TalonSRX liftFollow;
	
	private DigitalInput limitSwitchTop;
	private DigitalInput limitSwitchBottom;
	
	private DoubleSolenoid liftShift;
	private DoubleSolenoid busterBrake;

	private static final double deadBand = RobotConstraints.DriveSubsystem_deadBand;

    public LiftSubsystem(){
    	liftLead = new WPI_TalonSRX(RobotMap.liftLead);
    	liftFollow = new WPI_TalonSRX(RobotMap.liftFollow);
    	
    	liftLead.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	liftLead.setSensorPhase(true);
    	
    	liftFollow.follow(liftLead);
    	liftFollow.setInverted(true);

    	limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTop);
    	limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottom);
    	
    	liftShift = new DoubleSolenoid(RobotMap.pcm, RobotMap.liftHigh,RobotMap.liftLow);
    	busterBrake = new DoubleSolenoid(RobotMap.pcm, RobotMap.busterBrakeIn, RobotMap.busterBrakeOut);
    }
        
    public boolean IsTop()    { return limitSwitchTop.get();    }
    public boolean IsBottom() { return limitSwitchBottom.get(); }

    Boolean pos;

    public double Position() {
    	if (limitSwitchBottom.get()) {
//    		liftLead.getSensorCollection().setQuadraturePosition(0, 5);
    		return liftLead.getSensorCollection().getQuadraturePosition();
    	}
    	else {
           return liftLead.getSensorCollection().getQuadraturePosition();
    	}
    }

    public void moveLift(double power) {
        // if (!Robot.kLiftSubsystem.IsBottom()){
        //     liftLead.set(power);
        // }
        // // else if (power > 0) {
        //     // liftLead.set(0);
        // }
        // else if (power < 0) {
			// if (power > +.05 && power < -.05){
			// 	liftLead.set(power);
			// } 
		if((power< deadBand) && (power > -deadBand)){ power=0;}
		liftLead.set(power); 
			// }
    }
    
    public void shiftLift(String position) {
    	if (position == "high") {
    		liftShift.set(DoubleSolenoid.Value.kForward);
    	} else if (position == "low") {
    		liftShift.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void enableBrake() {
    	busterBrake.set(DoubleSolenoid.Value.kForward);
    }
    
    public void removeBrake() {
	   busterBrake.set(DoubleSolenoid.Value.kReverse);
   }
    
	public void initDefaultCommand() {
		setDefaultCommand(new LiftMove());
	}
    
    public void log() {
    	SmartDashboard.putNumber("Lift position", Position());
    	SmartDashboard.putNumber("Lift Velocity", liftLead.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("LiftAmpsLead", liftLead.getOutputCurrent());
    	SmartDashboard.putNumber("LiftAmpsFollow", liftFollow.getOutputCurrent());
    }
}

