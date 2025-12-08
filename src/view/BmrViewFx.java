package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BmrViewFx {

    private final TextField weightField;
    private final TextField heightField;
    private final TextField ageField;
    private final RadioButton maleButton;
    private final RadioButton femaleButton;
    private final Button calculateButton;
    private final Label resultLabel;

    private final Parent root;

    public BmrViewFx() {
        // Überschrift
        Label titleLabel = new Label("BMR-Rechner");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Grid für Labels + Textfelder
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        // Spalten: 1 = Label, 2 = Eingabefeld (soll mitwachsen)
        ColumnConstraints colLabel = new ColumnConstraints();
        ColumnConstraints colField = new ColumnConstraints();
        colField.setHgrow(Priority.ALWAYS); // Eingabefelder wachsen mit
        formGrid.getColumnConstraints().addAll(colLabel, colField);

        // Gewicht
        Label weightLabel = new Label("Gewicht (kg):");
        weightField = new TextField();
        weightField.setPromptText("z.B. 70");
        weightField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(0, weightLabel, weightField);

        // Größe
        Label heightLabel = new Label("Größe (cm):");
        heightField = new TextField();
        heightField.setPromptText("z.B. 175");
        heightField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(1, heightLabel, heightField);

        // Alter
        Label ageLabel = new Label("Alter (Jahre):");
        ageField = new TextField();
        ageField.setPromptText("z.B. 30");
        ageField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(2, ageLabel, ageField);

        // Geschlecht
        Label genderLabel = new Label("Geschlecht:");
        maleButton = new RadioButton("Männlich");
        femaleButton = new RadioButton("Weiblich");
        maleButton.setSelected(true);

        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleButton, femaleButton);
        genderBox.setAlignment(Pos.CENTER_LEFT);
        // soll bei Vergrößerung ebenfalls Platz nutzen können
        genderBox.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(3, genderLabel, genderBox);

        // Button
        calculateButton = new Button("Berechnen");
        calculateButton.setDefaultButton(true);
        HBox buttonBox = new HBox(calculateButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(5, 0, 0, 0));

        // Ergebnis
        resultLabel = new Label("Ergebnis: ");
        resultLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        HBox resultBox = new HBox(resultLabel);
        resultBox.setAlignment(Pos.CENTER_LEFT);
        resultBox.setPadding(new Insets(5, 0, 0, 0));
        resultBox.setMaxWidth(Double.MAX_VALUE);

        // Root-Layout
        VBox rootBox = new VBox(15, titleLabel, formGrid, buttonBox, resultBox);
        rootBox.setPadding(new Insets(20));
        rootBox.setAlignment(Pos.TOP_CENTER);
        rootBox.setFillWidth(true); // Kinder dürfen volle Breite nutzen

        // Formular soll in der VBox mitwachsen
        VBox.setVgrow(formGrid, Priority.ALWAYS);

        // "Card"-Container für Inhalt, damit dieser sich vom Hintergrund abhebt
        VBox card = new VBox(15, titleLabel, formGrid, buttonBox, resultBox);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_CENTER);
        card.setFillWidth(true);
        card.setMaxWidth(420);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 15, 0.2, 0, 4);"
        );

        VBox.setVgrow(card, Priority.ALWAYS);

        // Äußerer Root-Container mit schönem Verlauf
        VBox outerRoot = new VBox(card);
        outerRoot.setAlignment(Pos.CENTER);
        outerRoot.setPadding(new Insets(30));
        outerRoot.setFillWidth(true);

        outerRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);" +
                        "-fx-font-family: 'Segoe UI', 'Helvetica Neue', Arial, sans-serif;"
        );

        this.root = outerRoot;
    }

    // Root für Scene
    public Parent getRoot() {
        return root;
    }

    // Zugriffsmethoden
    public String getWeightInput() {
        return weightField.getText();
    }

    public String getHeightInput() {
        return heightField.getText();
    }

    public String getAgeInput() {
        return ageField.getText();
    }

    public String getGenderInput() {
        if (maleButton.isSelected()) return "Männlich";
        if (femaleButton.isSelected()) return "Weiblich";
        return "Unbekannt";
    }

    public Button getCalculateButton() {
        return calculateButton;
    }

    public void setBmrResult(String result) {
        resultLabel.setText(result);
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eingabefehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}