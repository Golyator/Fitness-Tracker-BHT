package model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class DailySummaryTest {

    @Test
    void constructorInitializesWithDate() {
        Date date = new Date();
        DailySummary summary = new DailySummary(date);

        assertEquals(date, summary.getDate());
        assertEquals(0.0, summary.getBmr(), 0.0001);
        assertEquals(0.0, summary.getTotalActivityCalories(), 0.0001);
        assertEquals(0.0, summary.getTotalFoodCalories(), 0.0001);
    }

    @Test
    void constructorInitializesWithAllValues() {
        Date date = new Date();
        DailySummary summary = new DailySummary(date, 1500.0, 500.0, 2000.0);

        assertEquals(date, summary.getDate());
        assertEquals(1500.0, summary.getBmr(), 0.0001);
        assertEquals(500.0, summary.getTotalActivityCalories(), 0.0001);
        assertEquals(2000.0, summary.getTotalFoodCalories(), 0.0001);
    }

    @Test
    void setBmrUpdatesValue() {
        DailySummary summary = new DailySummary(new Date());
        summary.setBmr(1800.0);

        assertEquals(1800.0, summary.getBmr(), 0.0001);
    }

    @Test
    void setTotalActivityCaloriesUpdatesValue() {
        DailySummary summary = new DailySummary(new Date());
        summary.setTotalActivityCalories(600.0);

        assertEquals(600.0, summary.getTotalActivityCalories(), 0.0001);
    }

    @Test
    void addActivityCaloriesAccumulates() {
        DailySummary summary = new DailySummary(new Date());
        summary.addActivityCalories(200.0);
        summary.addActivityCalories(150.0);
        summary.addActivityCalories(250.0);

        assertEquals(600.0, summary.getTotalActivityCalories(), 0.0001);
    }

    @Test
    void setTotalFoodCaloriesUpdatesValue() {
        DailySummary summary = new DailySummary(new Date());
        summary.setTotalFoodCalories(2500.0);

        assertEquals(2500.0, summary.getTotalFoodCalories(), 0.0001);
    }

    @Test
    void getBalanceCalculatesCorrectly() {
        DailySummary summary = new DailySummary(new Date());
        summary.setBmr(1800.0);
        summary.setTotalActivityCalories(400.0);
        summary.setTotalFoodCalories(2000.0);

        // Balance = BMR + ActivityCalories - FoodCalories
        // = 1800 + 400 - 2000 = 200
        assertEquals(200.0, summary.getBalance(), 0.0001);
    }

    @Test
    void getBalanceNegativeForSurplus() {
        DailySummary summary = new DailySummary(new Date());
        summary.setBmr(1500.0);
        summary.setTotalActivityCalories(300.0);
        summary.setTotalFoodCalories(2500.0);

        // Balance = 1500 + 300 - 2500 = -700
        assertEquals(-700.0, summary.getBalance(), 0.0001);
    }

    @Test
    void getBalanceZeroWhenBalanced() {
        DailySummary summary = new DailySummary(new Date());
        summary.setBmr(2000.0);
        summary.setTotalActivityCalories(500.0);
        summary.setTotalFoodCalories(2500.0);

        // Balance = 2000 + 500 - 2500 = 0
        assertEquals(0.0, summary.getBalance(), 0.0001);
    }

    @Test
    void multipleAddActivityCaloriesAndSetFoodCalories() {
        DailySummary summary = new DailySummary(new Date());
        summary.setBmr(1700.0);
        
        summary.addActivityCalories(100.0);
        summary.addActivityCalories(200.0);
        summary.setTotalFoodCalories(1800.0);

        assertEquals(1700.0, summary.getBmr(), 0.0001);
        assertEquals(300.0, summary.getTotalActivityCalories(), 0.0001);
        assertEquals(1800.0, summary.getTotalFoodCalories(), 0.0001);
        assertEquals(200.0, summary.getBalance(), 0.0001);
    }
}
