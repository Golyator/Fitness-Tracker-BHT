package controller;

import model.BmrCalculator;
import model.UserProfile;
import view.BmrView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BmrController implements ActionListener {

    private BmrView view;

    public BmrController(BmrView view) {
        this.view = view;
        // Korrektur: Statt einer nicht existierenden addSaveListener Methode
        // holen wir uns den Button und fügen diesen Controller als Listener hinzu.
        this.view.getCalculateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double weight = Double.parseDouble(view.getWeightInput());
            // Korrektur: height muss ein int sein, da UserProfile int erwartet.
            // Wir parsen es direkt als int.
            int height = Integer.parseInt(view.getHeightInput());
            int age = Integer.parseInt(view.getAgeInput());
            String gender = view.getGenderInput();

            UserProfile user = new UserProfile(weight, height, age, gender);

            // Korrektur: Die Methode im Calculator heißt 'calculate', nicht 'calculateBMR'
            double bmr = BmrCalculator.calculate(user);

            // Setze BMR im UserProfile (wichtig für DatabaseManager später)
            user.setBmr(bmr);

            view.setBmrResult("Ergebnis: " + String.format("%.2f", bmr) + " kcal/Tag");

        } catch (NumberFormatException ex) {
            view.showErrorMessage("Bitte geben Sie gültige Zahlen ein! (Größe und Alter müssen ganzzahlig sein)");
        } catch (IllegalArgumentException ex) {
            view.showErrorMessage(ex.getMessage());
        }
    }
}