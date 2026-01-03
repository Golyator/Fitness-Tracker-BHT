package controller;

import java.util.Date;
import model.DailySummary;
import view.DailySummaryViewFx;

/**
 * Controller für die tägliche Zusammenfassung (BMR, verbrannte Kalorien, konsumierte Kalorien).
 * Zentrale Stelle für alle Aktualisierungen der Summary-View.
 * Verwaltet das DailySummary Model und aktualisiert die View entsprechend.
 */
public class DailySummaryControllerFx {

    private final DailySummaryViewFx view;
    private final DailySummary summary;

    public DailySummaryControllerFx(DailySummaryViewFx view) {
        this.view = view;
        this.summary = new DailySummary(new Date());
    }

    /**
     * Setzt den BMR-Wert im Model und aktualisiert die View
     */
    public void setBmr(double bmr) {
        summary.setBmr(bmr);
        view.setBmr(bmr);
    }

    /**
     * Addiert Activity-Kalorien hinzu im Model und aktualisiert die View
     */
    public void addActivityCalories(double calories) {
        summary.addActivityCalories(calories);
        view.addActivityCalories(calories);
    }

    /**
     * Setzt die Gesamt-Food-Kalorien im Model und aktualisiert die View
     */
    public void setFoodCalories(double calories) {
        summary.setTotalFoodCalories(calories);
        view.setFoodCalories(calories);
    }

    // Getter für das Model
    public DailySummary getSummary() {
        return summary;
    }

    public double getBalance() {
        return summary.getBalance();
    }

    public double getBmr() {
        return summary.getBmr();
    }

    public double getTotalActivityCalories() {
        return summary.getTotalActivityCalories();
    }

    public double getTotalFoodCalories() {
        return summary.getTotalFoodCalories();
    }
}
