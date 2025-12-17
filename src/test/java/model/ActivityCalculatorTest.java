package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class ActivityCalculatorTest {

    private void assertCalories(ActivityType type, int durationMinutes, double weight) {
        UserProfile user = new UserProfile(weight, 180, 47, "Männlich");

        ActivityRecord record = new ActivityRecord(durationMinutes, "medium", new Date(), type);

        double calories = ActivityCalculator.calculate(record, user.getWeight());
        double expected = record.getActivity().getMet() * user.getWeight() * (record.getDurationMinutes() / 60.0);

        assertEquals(expected, calories, 0.01);
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void testCalculateCaloriesRunning() throws Exception {
        UserProfile user = new UserProfile(80, 180, 47, "Männlich");
        Date date = sdf.parse("12.12.2025");

        ActivityRecord record = new ActivityRecord(45, "medium", date, ActivityType.RUNNING);

        double calories = ActivityCalculator.calculate(record, user.getWeight());
        double expected = record.getActivity().getMet() * user.getWeight() * (record.getDurationMinutes() / 60.0);
        assertEquals(expected, calories, 0.01);
    }

    @Test
    public void testCalculateCaloriesWalking() throws Exception {
        UserProfile user = new UserProfile(80, 180, 47, "Männlich");
        Date date = sdf.parse("12.12.2025");

        ActivityRecord record = new ActivityRecord(15, "medium", date, ActivityType.WALKING);

        double calories = ActivityCalculator.calculate(record, user.getWeight());
        double expected = record.getActivity().getMet() * user.getWeight() * (record.getDurationMinutes() / 60.0);
        assertEquals(expected, calories, 0.01);
    }

    @Test
    void testCalculateCaloriesCycling() {
        assertCalories(ActivityType.CYCLING, 35, 70.0);
    }

    @Test
    void testCalculateCaloriesRunning2() {
        assertCalories(ActivityType.RUNNING, 48, 100.0);
    }
}