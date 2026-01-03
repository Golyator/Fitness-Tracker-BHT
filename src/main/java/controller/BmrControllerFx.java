package controller;

import database.BmrRepository;
import model.BmrCalculator;
import model.UserProfile;
import view.BmrViewFx;
import model.CurrentUserContext;

public class BmrControllerFx {

    private final BmrViewFx view;
    private final BmrRepository repository;
    private final DailySummaryControllerFx summaryController;

    public BmrControllerFx(BmrViewFx view, BmrRepository repository, DailySummaryControllerFx summaryController) {
        this.view = view;
        this.repository = repository;
        this.summaryController = summaryController;

        this.view.getCalculateButton().setOnAction(event -> onCalculate());
    }

    private void onCalculate() {
        try {
            double weight = Double.parseDouble(view.getWeightInput());
            int height = Integer.parseInt(view.getHeightInput());
            int age = Integer.parseInt(view.getAgeInput());
            String gender = view.getGenderInput();

            UserProfile user = new UserProfile(weight, height, age, gender);
            double bmr = BmrCalculator.calculate(user);
            user.setBmr(bmr);

            // Repository nutzen zum Speichern
            repository.save(user);

            // Aktuellen User im Kontext ablegen, damit Activity darauf zugreifen kann
            CurrentUserContext.setCurrentUser(user);

            // BMR in die Tagesbilanz eintragen
            summaryController.setBmr(bmr);

            view.setBmrResult("Ergebnis: " + String.format("%.2f", bmr) + " kcal/Tag");

        } catch (NumberFormatException ex) {
            view.showErrorMessage("Bitte geben Sie gültige Zahlen ein!");
        } catch (Exception ex) {
            view.showErrorMessage("Fehler: " + ex.getMessage());
        }
    }
}