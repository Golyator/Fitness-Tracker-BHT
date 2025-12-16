package model;

public enum ActivityType {
    RUNNING(8.3),
    WALKING(3.5),
    CYCLING(7.2);

    private final double met;

    ActivityType(double met) {
        this.met = met;
    }
    public double getMet() {
        return met;
    }

}



