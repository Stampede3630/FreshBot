/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class FreshBot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    Joystick leftStick;
    Joystick rightStick;
    RobotDrive drive;
    Compressor compressor;
    Solenoid grab;
    Solenoid release;
    Talon shoot;
    
    
    public void robotInit() {
        leftStick = new Joystick(1); //joystick port on computer
        rightStick = new Joystick(2); //joystick port on computer
        drive = new RobotDrive(1, 2, 3, 4);//PWM Numbers (lf, lr, rf, rr)
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        grab = new Solenoid(2);
        release = new Solenoid(1);
        compressor = new Compressor(1,1);
//        shoot = new Talon(5);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive.arcadeDrive(leftStick.getY(), rightStick.getX());
        //drive.mecanumDrive_Cartesian(leftStick.getY(), leftStick.getX(), rightStick.getX(), 0.0); (mechanum drive)

//        if(leftStick.getRawButton(4)){
//            shoot.set(3333 /*input speed*/);
//        }
        
        if(leftStick.getRawButton(3)){ //release object if button 3 on left stick is pressed
            
            release.set(true);
            grab.set(false);
            
        }
        
        if(leftStick.getRawButton(2)){ //grab object if button 2 on left stick is pressed
            
            release.set(false);
            grab.set(true);
        
        }
    }
    public void teleopInit(){
        compressor.start();
    }
    public void disabledPeriodic() {
        drive.stopMotor();
    }

    public void disabledInit() {
        compressor.stop();

    }

   
}
