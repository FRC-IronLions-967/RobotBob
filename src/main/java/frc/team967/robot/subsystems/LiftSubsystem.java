package frc.team967.robot.subsystems;

import frc.team967.robot.RobotConstraints;
import frc.team967.robot.RobotMap;
import frc.team967.robot.commands.LiftMove;
import frc.team967.robot.commands.T_ArcadeDriveLookUp;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
//	private final double kP = RobotConstraints.LiftSubsystem_kP;
//	private final double kI = RobotConstraints.LiftSubsystem_kI;
//	private final double kD = RobotConstraints.LiftSubsystem_kD;
//	private final double kToleranceDegrees = RobotConstraints.LiftSubsystem_kTolerance;
	
    public LiftSubsystem(){
    	liftLead = new WPI_TalonSRX(RobotMap.liftLead);
    	liftFollow = new WPI_TalonSRX(RobotMap.liftFollow);
    	
    	liftLead.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	liftLead.setSensorPhase(false);
    	
    	liftFollow.follow(liftLead);
    	liftFollow.setInverted(true);

    	limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTop);
    	limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottom);
    	
    	liftShift = new DoubleSolenoid(RobotMap.pcm, RobotMap.liftHigh,RobotMap.liftLow);
    	busterBrake = new DoubleSolenoid(RobotMap.pcm, RobotMap.busterBrakeIn, RobotMap.busterBrakeOut);
    }
        
    public boolean IsTop()    { return limitSwitchTop.get();    }
    public boolean IsBottom() { return limitSwitchBottom.get(); }
    
    public double Position() {
    	if (IsTop() == true) {
    		return 100;
    	}
    	else if (IsBottom() == true) {
    		liftLead.getSensorCollection().setQuadraturePosition(0, 0);
    		return 0;
    	}
    	else {
    		return liftLead.getSensorCollection().getQuadraturePosition();
    	}
    }
    
    public void moveLift(double power) {
    	liftLead.set(power);
//    	liftFollow.set(power);
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
    	SmartDashboard.putNumber("Lift position", liftLead.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("Lift Velocity", liftLead.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("LiftAmpsLead", liftLead.getOutputCurrent());
    	SmartDashboard.putNumber("LiftAmpsFollow", liftFollow.getOutputCurrent());
    	
    	SmartDashboard.putBoolean("limmit botom", IsBottom());
    }
}

