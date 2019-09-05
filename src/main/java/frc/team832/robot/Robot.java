/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team832.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team832.lib.driverstation.controllers.POV;
import frc.team832.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	POVButton upButton = new POVButton(RobotContainer.stick, 0);

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		CommandBase onCenter = new CenterCommand(RobotContainer.mTestSubsystem);
		RobotContainer.stick.getPOVButton(POV.Position.Up).whenPressed(new UpCommand(RobotContainer.mTestSubsystem)).whenReleased(onCenter);
		RobotContainer.stick.getPOVButton(POV.Position.Down).whenPressed(new DownCommand(RobotContainer.mTestSubsystem)).whenReleased(onCenter);
	}

	POV.Position lastPov = POV.Position.Center;

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		POV.Position curPov = RobotContainer.stick.POV.getPosition();
		if (curPov != lastPov) {
			SmartDashboard.putString("POV Position", curPov.toString());
		}
		lastPov = curPov;
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}

}
