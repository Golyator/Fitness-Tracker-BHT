package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FoodViewFx {

    private final TextField foodNameField;
    private final TextField caloriesPerUnitField;
    private final TextField amountField;
    private final Button addFoodButton;
    private final Label totalCaloriesLabel;
    private final TextArea foodListArea;

    private final Parent root;

    public FoodViewFx() {
        Label titleLabel = new Label("Food-Kalorienrechner");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        ColumnConstraints colLabel = new ColumnConstraints();
        ColumnConstraints colField = new ColumnConstraints();
        colField.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().addAll(colLabel, colField);

        // Name des Lebensmittels
        Label foodNameLabel = new Label("Lebensmittel:");
        foodNameField = new TextField();
        foodNameField.setPromptText("z.B. Apfel");
        foodNameField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(0, foodNameLabel, foodNameField);

        // Kalorien pro Einheit (z.B. kcal pro 100g)
        Label caloriesPerUnitLabel = new Label("kcal pro Einheit:");
        caloriesPerUnitField = new TextField();
        caloriesPerUnitField.setPromptText("z.B. 52 (kcal/100g)");
        caloriesPerUnitField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(1, caloriesPerUnitLabel, caloriesPerUnitField);

        // Menge (z.B. Gramm oder Stück)
        Label amountLabel = new Label("Menge (Einheiten):");
        amountField = new TextField();
        amountField.setPromptText("z.B. 1.5");
        amountField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(2, amountLabel, amountField);

        addFoodButton = new Button("Lebensmittel hinzufügen");
        HBox buttonBox = new HBox(addFoodButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(5, 0, 0, 0));

        // Liste der eingetragenen Foods
        foodListArea = new TextArea();
        foodListArea.setEditable(false);
        foodListArea.setPrefRowCount(5);
        foodListArea.setWrapText(true);

        // Gesamt-Kalorien
        totalCaloriesLabel = new Label("Gesamt: 0 kcal");
        totalCaloriesLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        HBox totalBox = new HBox(totalCaloriesLabel);
        totalBox.setAlignment(Pos.CENTER_LEFT);
        totalBox.setPadding(new Insets(5, 0, 0, 0));
        totalBox.setMaxWidth(Double.MAX_VALUE);

        VBox centerBox = new VBox(10, formGrid, buttonBox, foodListArea, totalBox);
        centerBox.setPadding(new Insets(10, 10, 10, 10));

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

        // Diese View liefert nur die Card; der Hintergrund kommt von BmrViewFx
        VBox wrapper = new VBox(card);
        wrapper.setAlignment(Pos.TOP_CENTER);

        this.root = wrapper;
    }

    public Parent getRoot() {
        return root;
    }

    public String getFoodNameInput() {
        return foodNameField.getText();
    }

    public String getCaloriesPerUnitInput() {
        return caloriesPerUnitField.getText();
    }

    public String getAmountInput() {
        return amountField.getText();
    }

    public Button getAddFoodButton() {
        return addFoodButton;
    }

    public void addFoodLine(String line) {
        if (!foodListArea.getText().isEmpty()) {
            foodListArea.appendText(System.lineSeparator());
        }
        foodListArea.appendText(line);
    }

    public void setTotalCalories(String text) {
        totalCaloriesLabel.setText(text);
    }

    public void clearInputs() {
        foodNameField.clear();
        caloriesPerUnitField.clear();
        amountField.clear();
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eingabefehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}