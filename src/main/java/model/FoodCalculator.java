package model;

/**
 * Hilfsklasse zur Berechnung der Kalorien eines Lebensmittels.
 *
 * Formel:
 *   Gesamtkalorien = Kalorien pro Einheit × Menge
 *
 * Beispiel:
 *   52 kcal pro 100g, 1.5 Einheiten (150g) -> 78 kcal
 */
public class FoodCalculator {

    public static double calculate(double caloriesPerUnit, double amount) {
        return caloriesPerUnit * amount;
    }
}