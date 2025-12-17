package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.FoodRepository;
import model.FoodCalculator;
import model.FoodRecord;
import view.FoodViewFx;
import view.DailySummaryViewFx;

public class FoodControllerFx {

    private final FoodViewFx view;
    private final FoodRepository repository;
    private final DailySummaryViewFx summaryView;

    // Einfache In-Memory-Speicherung der Foods
    private final List<FoodEntry> foodEntries = new ArrayList<>();
    private double totalCalories = 0.0;


    public FoodControllerFx(FoodViewFx view, FoodRepository repository, DailySummaryViewFx summaryView) {
        this.view = view;
        this.repository = repository;
        this.summaryView = summaryView;

        this.view.getAddFoodButton().setOnAction(event -> onAddFood());
    }

    private void onAddFood() {
        try {
            String name = view.getFoodNameInput();
            if (name == null || name.isBlank()) {
                view.showErrorMessage("Bitte einen Lebensmittelnamen eingeben.");
                return;
            }

            double caloriesPerUnit = Double.parseDouble(view.getCaloriesPerUnitInput());
            double amount = Double.parseDouble(view.getAmountInput());

            // Zentrale Berechnung über FoodCalculator
            double calories = FoodCalculator.calculate(caloriesPerUnit, amount);

            FoodEntry entry = new FoodEntry(name, caloriesPerUnit, amount, calories);
            foodEntries.add(entry);

            totalCalories += calories;

            // In CSV speichern (FoodRecord)
            FoodRecord record = new FoodRecord(
                    (float) calories,
                    (float) amount,
                    new Date()    // aktuelles Datum -> später für Tagesauswertung nutzbar
            );
            repository.save(record);

            // Darstellung in der View aktualisieren
            String line = String.format(
                    "%s: %.2f Einheiten × %.2f kcal = %.2f kcal",
                    entry.name, entry.amount, entry.caloriesPerUnit, entry.calories
            );
            view.addFoodLine(line);
            view.setTotalCalories(String.format("Gesamt: %.2f kcal", totalCalories));
            view.clearInputs();

            // Food-Gesamtkalorien in die Tagesbilanz eintragen
            summaryView.setFoodCalories(totalCalories);

        } catch (NumberFormatException ex) {
            view.showErrorMessage("Bitte gültige Zahlen für Kalorien pro Einheit und Menge eingeben.");
        } catch (Exception ex) {
            view.showErrorMessage("Fehler: " + ex.getMessage());
        }
    }

    /**
     * Ein Eintrag für ein einzelnes Lebensmittel inkl. berechneter Kalorien.
     */
    private static class FoodEntry {
        final String name;
        final double caloriesPerUnit;
        final double amount;
        final double calories;

        FoodEntry(String name, double caloriesPerUnit, double amount, double calories) {
            this.name = name;
            this.caloriesPerUnit = caloriesPerUnit;
            this.amount = amount;
            this.calories = calories;
        }
    }
}