package model;

public class ActivityCalculator {

    public static double calculate(ActivityRecord record, UserProfile user) {
        return record.getActivity().getMet() * user.getWeight() * (record.getDurationMinutes() / 60.0);
    }
}
