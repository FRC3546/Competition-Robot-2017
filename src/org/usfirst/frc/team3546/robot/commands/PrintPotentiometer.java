package org.usfirst.frc.team3546.robot.commands;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import org.usfirst.frc.team3546.robot.Robot;

/**
 * Created by User on 1/28/2017.
 */
public class PrintPotentiometer extends Command {

    public PrintPotentiometer(){}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Test potentiometer Angle: " + Robot.sensors.getShootingorStorageGuidePotentiometerAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}