package controller;

import database.BmrRepository; // Interface nutzen!
import model.BmrCalculator;
import model.UserProfile;
import view.BmrView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BmrController implements ActionListener {

    private BmrView view;
    private BmrRepository repository; // Referenz auf das Repository

    // Konstruktor nimmt Repository entgegen
    public BmrController(BmrView view, BmrRepository repository) {
        this.view = view;
        this.repository = repository;
        this.view.getCalculateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

            view.setBmrResult("Ergebnis: " + String.format("%.2f", bmr) + " kcal/Tag");

        } catch (NumberFormatException ex) {
            view.showErrorMessage("Bitte geben Sie gültige Zahlen ein!");
        } catch (Exception ex) { // Auch Fehler vom Repository fangen
            view.showErrorMessage("Fehler: " + ex.getMessage());
        }
    }
}