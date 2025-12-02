package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BmrView extends JFrame {

    private JTextField ageField = new JTextField(5);
    private JTextField weightField = new JTextField(5);
    private JTextField heightField = new JTextField(5);
    private JComboBox<String> genderBox = new JComboBox<>(new String[]{"Männlich", "Weiblich"});
    private JButton saveButton = new JButton("BMR Berechnen & Speichern");
    private JLabel resultLabel = new JLabel("Ergebnis: ---");

    public BmrView() {
        setTitle("Fitness App - BMR Rechner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10)); // Grid Layout für Ordnung

        // Zeile 1
        add(new JLabel("Alter (Jahre):"));
        add(ageField);

        // Zeile 2
        add(new JLabel("Gewicht (kg):"));
        add(weightField);

        // Zeile 3
        add(new JLabel("Größe (cm):"));
        add(heightField);

        // Zeile 4
        add(new JLabel("Geschlecht:"));
        add(genderBox);

        // Zeile 5
        add(new JLabel("")); // Leerer Platzhalter
        add(saveButton);

        // Zeile 6
        add(new JLabel("Dein Grundumsatz:"));
        add(resultLabel);

        // Fenster zentrieren und sichtbar machen
        setLocationRelativeTo(null);
    }

    // Getter für die Eingabewerte (damit der Controller sie lesen kann)
    public String getAgeInput() { return ageField.getText(); }
    public String getWeightInput() { return weightField.getText(); }
    public String getHeightInput() { return heightField.getText(); }
    public String getGenderInput() { return (String) genderBox.getSelectedItem(); }

    // Setter für das Ergebnis
    public void setResultText(String text) {
        resultLabel.setText(text);
    }

    // Methode, um dem Button einen Listener (den Controller) hinzuzufügen
    public void addSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
    
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}
