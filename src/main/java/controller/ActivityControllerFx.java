package controller;

import java.util.Date;

import database.ActivityRepository;
import model.ActivityCalculator;
import model.ActivityRecord;
import model.ActivityType;
import model.CurrentUserContext;
import model.UserProfile;
import view.ActivityViewFx;
import view.DailySummaryViewFx;

public class ActivityControllerFx {

    private final ActivityViewFx view;
    private final ActivityRepository repository;
    private final DailySummaryViewFx summaryView;

    // NEU: Akkumulierte Aktivitätskalorien für den Tag
    private double totalActivityCalories = 0.0;

    public ActivityControllerFx(ActivityViewFx view, ActivityRepository repository, DailySummaryViewFx summaryView) {
        this.view = view;
        this.repository = repository;
        this.summaryView = summaryView;

        this.view.getAddActivityButton().setOnAction(event -> onAddActivity());
    }

    private void onAddActivity() {
        try {
            if (!CurrentUserContext.hasUser()) {
                view.showErrorMessage("Bitte zuerst im BMR-Rechner ein Profil berechnen.");
                return;
            }
            UserProfile user = CurrentUserContext.getCurrentUser();

            ActivityType type = view.getSelectedActivityType();
            if (type == null) {
                view.showErrorMessage("Bitte eine Aktivität auswählen.");
                return;
            }

            int durationMinutes = Integer.parseInt(view.getDurationInput());
            if (durationMinutes <= 0) {
                view.showErrorMessage("Die Dauer muss größer als 0 sein.");
                return;
            }

            String intensity = view.getIntensityInput();
            if (intensity == null || intensity.isBlank()) {
                view.showErrorMessage("Bitte eine Intensität auswählen.");
                return;
            }

            ActivityRecord record = new ActivityRecord(
                    durationMinutes,
                    intensity,
                    new Date(),
                    type
            );

            double calories = ActivityCalculator.calculate(record, user);

            repository.save(record);

            // HIER: Feld wird benutzt, jetzt deklariert
            totalActivityCalories += calories;

            String resultText = String.format("Verbrannte Kalorien: %.2f kcal", calories);
            view.setResultText(resultText);
            view.clearInputs();

            // Aktivitätskalorien in Tagesbilanz eintragen
            summaryView.addActivityCalories(calories);

        } catch (NumberFormatException ex) {
            view.showErrorMessage("Bitte eine gültige Zahl für die Dauer eingeben.");
        } catch (Exception ex) {
            view.showErrorMessage("Fehler: " + ex.getMessage());
        }
    }
}