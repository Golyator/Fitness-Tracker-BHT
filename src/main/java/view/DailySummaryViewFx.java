package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Zeigt nur die Tagesbilanz an:
 *   Bilanz = BMR + Aktivitätskalorien - Foodkalorien
 * als Text:
 *   "Tagesbilanz: +300 kcal" oder "Tagesbilanz: -500 kcal"
 */
public class DailySummaryViewFx {

    private final Label balanceLabel;

    private double bmr = 0.0;
    private double activityCalories = 0.0;
    private double foodCalories = 0.0;

    private final Parent root;

    public DailySummaryViewFx() {
        Label titleLabel = new Label("Tagesbilanz");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        balanceLabel = new Label("Tagesbilanz: 0 kcal");
        balanceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        VBox centerBox = new VBox(10, balanceLabel);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(10));

        BorderPane card = new BorderPane();
        card.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(10, 10, 0, 10));

        card.setCenter(centerBox);

        card.setPadding(new Insets(10));
        card.setMaxWidth(420);
        card.setMaxHeight(Region.USE_PREF_SIZE);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 15, 0.2, 0, 4);"
        );

        HBox wrapper = new HBox(card);
        wrapper.setAlignment(Pos.CENTER);

        this.root = wrapper;
        updateLabel();
    }

    public Parent getRoot() {
        return root;
    }

    // Wird vom BMR-Controller aufgerufen
    public void setBmr(double bmr) {
        this.bmr = bmr;
        updateLabel();
    }

    // Wird vom Activity-Controller bei jeder Aktivität aufgerufen
    public void addActivityCalories(double calories) {
        this.activityCalories += calories;
        updateLabel();
    }

    // Wird vom Food-Controller aufgerufen, um die Gesamtsumme der Food-Kalorien zu setzen
    public void setFoodCalories(double calories) {
        this.foodCalories = calories;
        updateLabel();
    }

    private void updateLabel() {
        double balance = -(bmr + activityCalories - foodCalories);
        String sign = balance > 0 ? "+" : "";
        String text = String.format("%s%.2f kcal", sign, balance);
        balanceLabel.setText(text);
    }
}