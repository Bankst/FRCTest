package frc.team832.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team832.robot.subsystems.TestSubsystem;

public class CenterCommand extends CommandBase {
    public CenterCommand(TestSubsystem subsystem) {
        addRequirements(subsystem);
    }

    @Override
    public boolean runsWhenDisabled() { return true; }

    @Override
    public void initialize() {
        System.out.println("center");
    }
}