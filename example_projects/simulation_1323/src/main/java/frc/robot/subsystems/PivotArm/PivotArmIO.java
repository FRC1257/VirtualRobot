public interface PivotArmIO {
    public enum State {
        MANUAL;
    }

    public default void setPosition(double setpoint) {}

    public default void manualControl(double newSpeed) {}

    public default void updateIO() {}

    public default void displayShuffleboardIO() {}

    public default void tuningInitIO() {}

    public default void tuningPeriodicIO() {}

    public default State getState() { return State.MANUAL; }

    public default double getArmAngle() { return 0; }

}

