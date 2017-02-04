package org.usfirst.frc.team3546.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import org.usfirst.frc.team3546.robot.RobotMap;

/**
 * Created by User on 2/3/2017.
 */
public class Shooting extends Subsystem {

    public static final double REAR_SHOOTING_PID_P = .1;
    public static final double REAR_SHOOTING_PID_I = 0;
    public static final double REAR_SHOOTING_PID_D = 0;

    //VOLTS PER SECOND MAXIMUM
    //HOW LONG IT TAKES TO SPEED UP TO FULL SPEED
    //MAX VOLTS IS 12
    //4 WOULD TAKE ~ 3 SECONDS TO SPIN UP
    public static final double REAR_SHOOTING_PID_MAX_RATE = 4;

    public static final double FRONT_SHOOTING_PID_P = .1;
    public static final double FRONT_SHOOTING_PID_I = 0;
    public static final double FRONT_SHOOTING_PID_D = 0;

    //VOLTS PER SECOND MAXIMUM
    //HOW LONG IT TAKES TO SPEED UP TO FULL SPEED
    //MAX VOLTS IS 12
    //4 WOULD TAKE ~ 3 SECONDS TO SPIN UP
    public static final double FRONT_SHOOTING_PID_MAX_RATE = 4;

    public static final double HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_P = .1;
    public static final double HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_I = 0;
    public static final double HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_D = 0;


    public static final double HIGH_DEGREE_VALUE = 1000;
    public static final double LOW_DEGREE_VALUE = 1040;

    public static final double FRONT_MOTOR_SHOOTING_RPM = 100;
    public static final double REAR_MOTOR_SHOOTING_RPM = 100;


    @Override
    protected void initDefaultCommand() {}

    private static Potentiometer highorLowShootingGuidePotentiometer = RobotMap.highorLowShootingGuidePotentiometer;
    public double getHighorLowShootingGuidePotentiometerAngle() {return highorLowShootingGuidePotentiometer.get();}

    private CANTalon rearShootingandIntakeMotor = RobotMap.rearShootingandIntakeMotor;
    private CANTalon frontShootingMotor = RobotMap.frontShootingMotor;
    private SpeedController highorLowShootingGuideMotor = RobotMap.highorLowShootingGuideMotor;

    private PIDController highorLowShootingGuidePIDController;

    public Shooting(){
        rearShootingandIntakeMotor.setP(REAR_SHOOTING_PID_P);
        rearShootingandIntakeMotor.setI(REAR_SHOOTING_PID_I);
        rearShootingandIntakeMotor.setD(REAR_SHOOTING_PID_D);
        rearShootingandIntakeMotor.setVoltageRampRate(REAR_SHOOTING_PID_MAX_RATE);
        rearShootingandIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        rearShootingandIntakeMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

        frontShootingMotor.setP(FRONT_SHOOTING_PID_P);
        frontShootingMotor.setI(FRONT_SHOOTING_PID_I);
        frontShootingMotor.setD(FRONT_SHOOTING_PID_D);
        frontShootingMotor.setVoltageRampRate(FRONT_SHOOTING_PID_MAX_RATE);
        frontShootingMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        frontShootingMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

        highorLowShootingGuidePIDController = new PIDController(
                HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_P,
                HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_I,
                HIGH_OR_LOW_SHOOTING_GUIDE_MOTOR_PID_D,
                highorLowShootingGuidePotentiometer,
                highorLowShootingGuideMotor);
    }

    public void setFrontShootingSpeed (double speed){}

    public void setRearShootingSpeed (double speed){}

    public void setSpinUpShooters (){
        setFrontShootingSpeed(FRONT_MOTOR_SHOOTING_RPM);
        setRearShootingSpeed(REAR_MOTOR_SHOOTING_RPM);

    }

    public void stopShooterMotors (){
        frontShootingMotor.set(0);
        rearShootingandIntakeMotor.set(0);
    }

    private void setHighOrLowMotorPosition (double position){
        highorLowShootingGuidePIDController.setSetpoint(position);
        highorLowShootingGuidePIDController.enable();
    }

    public void setShootHigh(){
        setHighOrLowMotorPosition(HIGH_DEGREE_VALUE);
    }
    public void setShootLow(){
        setHighOrLowMotorPosition(LOW_DEGREE_VALUE);
    }
}