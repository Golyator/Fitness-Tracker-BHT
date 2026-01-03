package model;

import java.util.Date;

/**
 * Model für die tägliche Zusammenfassung.
 * Speichert die aggregierten Daten: BMR, verbrannte Kalorien durch Aktivität und konsumierte Kalorien.
 */
public class DailySummary {
    private Date date;
    private double bmr;
    private double totalActivityCalories;
    private double totalFoodCalories;

    public DailySummary(Date date) {
        this.date = date;
        this.bmr = 0.0;
        this.totalActivityCalories = 0.0;
        this.totalFoodCalories = 0.0;
    }

    public DailySummary(Date date, double bmr, double totalActivityCalories, double totalFoodCalories) {
        this.date = date;
        this.bmr = bmr;
        this.totalActivityCalories = totalActivityCalories;
        this.totalFoodCalories = totalFoodCalories;
    }

    // Getter
    public Date getDate() {
        return date;
    }

    public double getBmr() {
        return bmr;
    }

    public double getTotalActivityCalories() {
        return totalActivityCalories;
    }

    public double getTotalFoodCalories() {
        return totalFoodCalories;
    }

    // Setter
    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public void setTotalActivityCalories(double totalActivityCalories) {
        this.totalActivityCalories = totalActivityCalories;
    }

    public void addActivityCalories(double calories) {
        this.totalActivityCalories += calories;
    }

    public void setTotalFoodCalories(double totalFoodCalories) {
        this.totalFoodCalories = totalFoodCalories;
    }

    /**
     * Berechnet die tägliche Bilanz: BMR + verbrannte Kalorien - konsumierte Kalorien
     * Positiver Wert: Kaloriendefizit (Gewichtsverlust)
     * Negativer Wert: Kalorienüberschuss (Gewichtszunahme)
     */
    public double getBalance() {
        return bmr + totalActivityCalories - totalFoodCalories;
    }
}
