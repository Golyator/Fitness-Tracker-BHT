package controller;

import database.DatabaseManager;
import model.BmrCalculator;
import model.UserProfile;
import view.BmrView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BmrController {

    private BmrView view;

    public BmrController(BmrView view) {
        this.view = view;
        this.view.addSaveListener(new SaveButtonListener());
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Daten holen
                int age = Integer.parseInt(view.getAgeInput());
                double weight = Double.parseDouble(view.getWeightInput());
                double height = Double.parseDouble(view.getHeightInput());
                String gender = view.getGenderInput();

                // 2. Model erstellen
                UserProfile user = new UserProfile(age, weight, height, gender);

                // 3. Logik (BMR)
                double bmr = BmrCalculator.calculate(user);
                user.setBmr(bmr);

                // 4. Speichern (Delegation an DatabaseManager)
                DatabaseManager.saveUser(user);

                // 5. View Update
                view.setResultText(String.format("%.2f kcal", bmr));

            } catch (NumberFormatException ex) {
                view.showErrorMessage("Bitte gib gültige Zahlen ein!");
            } catch (Exception ex) {
                view.showErrorMessage("Speicherfehler: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}