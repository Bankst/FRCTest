package frc.team832.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.team832.robot.subsystems.TestSubsystem;

public class DownCommand extends CommandBase {

    public DownCommand(TestSubsystem subsystem) {
        addRequirements(subsystem);
    }

    @Override
    public boolean runsWhenDisabled() { return true; }
    
    @Override
    public void initialize() {
        System.out.println("down");
    }
}