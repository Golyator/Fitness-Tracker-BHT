package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
    // Container, in den wir Elemente UNTERHALB der BMR-Card einhängen können
    private final VBox contentBox;

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

        // Unterer Bereich im Card (Button + Ergebnis) – bleibt unten
        VBox bottomArea = new VBox(10, buttonBox, resultBox);
        bottomArea.setAlignment(Pos.CENTER);
        bottomArea.setPadding(new Insets(10, 10, 10, 10));

        // "Card"-Container für Inhalt, damit dieser sich vom Hintergrund abhebt
        BorderPane card = new BorderPane();
        card.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(10, 10, 0, 10));

        card.setCenter(formGrid);
        BorderPane.setMargin(formGrid, new Insets(10));

        card.setBottom(bottomArea);

        card.setPadding(new Insets(10));
        card.setMaxWidth(420);
        // Höhe soll sich nicht beliebig in die Vertikale strecken
        card.setMaxHeight(Region.USE_PREF_SIZE);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 15, 0.2, 0, 4);"
        );

        // Container, in dem BMR-Card und weitere Cards (z.B. Food) untereinander liegen
        contentBox = new VBox(20, card);
        contentBox.setAlignment(Pos.TOP_CENTER);

        // Äußerer Root-Container mit schönem Verlauf
        VBox outerRoot = new VBox(contentBox);
        outerRoot.setAlignment(Pos.TOP_CENTER);
        outerRoot.setPadding(new Insets(30));
        outerRoot.setFillWidth(true);
        // Card soll nicht vertikal mitwachsen, nur der Hintergrund
        VBox.setVgrow(contentBox, Priority.NEVER);

        outerRoot.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);" +
                        "-fx-font-family: 'Segoe UI', 'Helvetica Neue', Arial, sans-serif;"
        );

        // ScrollPane um outerRoot
        ScrollPane scrollPane = new ScrollPane(outerRoot);
        scrollPane.setFitToWidth(true); // Inhalt füllt die Breite
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // nur vertikales Scrollen

        this.root = scrollPane;
    }

    // Root für Scene
    public Parent getRoot() {
        return root;
    }

    /**
     * weitere UI-Elemente wie FoodViewFx unterhalb des BMR-Rechners
     * einzufügen.
     */
    public void addBelow(Parent node) {
        contentBox.getChildren().add(node);
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