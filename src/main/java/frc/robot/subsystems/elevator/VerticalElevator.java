package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VerticalElevator extends SubsystemBase {
    private ElevatorIO io;
    
    public enum State {
        MANUAL
    };
    
    public VerticalElevator(ElevatorIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Elevator", inputs);
    }

    @Override
    public void tuningInit() {
        io.tuningInitIO();
    }

    @Override
    public void tuningPeriodic() {
        io.tuningPeriodicIO();
    }

    @Override
    public void displayShuffleboard() {
        io.displayShuffleboardIO();
    }

    public void manualControl(double speed) {
        io.setSpeed(speed);
    }
}
