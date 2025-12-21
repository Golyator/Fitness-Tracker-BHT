package model;

public enum ActivityType {
    RUNNING(8.3),
    WALKING(3.5),
    CYCLING(7.2),
    SWIMMING(6.0),
    STRENGTH_TRAINING(6.0),
    YOGA(2.5),
    HIIT(10.0),
    DANCING(5.0),
    HIKING(6.0),
    HOUSEWORK(3.3);

    private final double met;

    //MET = Metabolic Equivalent of Task - wie viel Energie eine Aktivität im Vergleich zum Ruheumsatz verbraucht.

    ActivityType(double met) {
        this.met = met;
    }
    public double getMet() {
        return met;
    }

}



