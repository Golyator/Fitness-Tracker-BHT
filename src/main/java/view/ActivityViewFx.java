package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.ActivityType;

public class ActivityViewFx {

    private final ComboBox<ActivityType> activityTypeBox;
    private final ComboBox<String> intensityBox;
    private final TextField durationField;
    private final Button addActivityButton;
    private final Label resultLabel;

    private final Parent root;

    public ActivityViewFx() {
        Label titleLabel = new Label("Aktivitäts-Rechner");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        ColumnConstraints colLabel = new ColumnConstraints();
        ColumnConstraints colField = new ColumnConstraints();
        colField.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().addAll(colLabel, colField);

        // Aktivitätstyp
        Label activityLabel = new Label("Aktivität:");
        activityTypeBox = new ComboBox<>();
        activityTypeBox.getItems().addAll(ActivityType.values());
        activityTypeBox.getSelectionModel().selectFirst();
        activityTypeBox.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(0, activityLabel, activityTypeBox);

        // Dauer in Minuten
        Label durationLabel = new Label("Dauer (Minuten):");
        durationField = new TextField();
        durationField.setPromptText("z.B. 30");
        durationField.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(1, durationLabel, durationField);

        // Intensität
        Label intensityLabel = new Label("Intensität:");
        intensityBox = new ComboBox<>();
        intensityBox.getItems().addAll("leicht", "mittel", "hoch");
        intensityBox.getSelectionModel().select("mittel");
        intensityBox.setMaxWidth(Double.MAX_VALUE);
        formGrid.addRow(2, intensityLabel, intensityBox);

        addActivityButton = new Button("Aktivität hinzufügen");
        HBox buttonBox = new HBox(addActivityButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(5, 0, 0, 0));

        resultLabel = new Label("Verbrannte Kalorien: –");
        resultLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        HBox resultBox = new HBox(resultLabel);
        resultBox.setAlignment(Pos.CENTER_LEFT);
        resultBox.setPadding(new Insets(5, 0, 0, 0));
        resultBox.setMaxWidth(Double.MAX_VALUE);

        VBox centerBox = new VBox(10, formGrid, buttonBox, resultBox);
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

        VBox wrapper = new VBox(card);
        wrapper.setAlignment(Pos.TOP_CENTER);

        this.root = wrapper;
    }

    public Parent getRoot() {
        return root;
    }

    public ActivityType getSelectedActivityType() {
        return activityTypeBox.getValue();
    }

    public String getDurationInput() {
        return durationField.getText();
    }

    public String getIntensityInput() {
        return intensityBox.getValue();
    }

    public Button getAddActivityButton() {
        return addActivityButton;
    }

    public void setResultText(String text) {
        resultLabel.setText(text);
    }

    public void clearInputs() {
        durationField.clear();
        intensityBox.getSelectionModel().select("mittel");
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eingabefehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}