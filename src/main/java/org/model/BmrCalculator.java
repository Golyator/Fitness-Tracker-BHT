package org.model;

public class BmrCalculator {

    // Berechnung von Basal Metabolic Rate (Grundumsatzbrechnung) nach der Mifflin-St Jeor Formel
    public static double calculate(UserProfile user) {
        double bmr;
        // Formel: (10 x Gewicht) + (6.25 x Größe) - (5 x Alter) + s
        // s = +5 für Männer, s = -161 für Frauen
        double baseCalculation = (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge());

        if ("Männlich".equalsIgnoreCase(user.getGender())) {
            bmr = baseCalculation + 5;
        } else {
            bmr = baseCalculation - 161;
        }
        return bmr;
    }
}
