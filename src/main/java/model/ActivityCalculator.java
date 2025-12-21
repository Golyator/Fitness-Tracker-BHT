package model;

public class ActivityCalculator {

    public static double calculate(ActivityRecord record, UserProfile user) {
        if (record == null || record.getActivity() == null) {
            throw new IllegalArgumentException("ActivityRecord und ActivityType dürfen nicht null sein.");
        }
        if (user == null) {
            throw new IllegalArgumentException("UserProfile darf nicht null sein.");
        }

        double weightKg = user.getWeight();
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Gewicht im UserProfile muss > 0 sein.");
        }

        double met = record.getActivity().getMet();
        double durationHours = record.getDurationMinutes() / 60.0;

        String intensity = record.getIntensity() != null ? record.getIntensity() : "";
        double intensityFactor = switch (intensity.toLowerCase()) {
            case "leicht" -> 0.8;
            case "mittel", "medium" -> 1.0;
            case "hoch" -> 1.2;
            default -> 1.0;
        };

        return met * intensityFactor * weightKg * durationHours;
    }
}