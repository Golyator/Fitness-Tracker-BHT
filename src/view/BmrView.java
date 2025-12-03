package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BmrView extends JFrame {

    private JTextField ageField;
    private JTextField weightField;
    private JTextField heightField;
    // Änderung: Statt Textfeld nutzen wir RadioButtons
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup genderGroup;

    private JButton calculateButton;
    private JLabel resultLabel;

    public BmrView() {
        setTitle("BMR Rechner");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Gewicht (kg):"));
        weightField = new JTextField();
        add(weightField);

        add(new JLabel("Größe (cm):"));
        heightField = new JTextField();
        add(heightField);

        add(new JLabel("Alter (Jahre):"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Geschlecht:"));
        // Panel für Radio Buttons erstellen, damit sie in einer Zelle sind
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleButton = new JRadioButton("Männlich");
        femaleButton = new JRadioButton("Weiblich");
        maleButton.setSelected(true); // Standardauswahl

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        add(genderPanel);

        calculateButton = new JButton("Berechnen");
        add(calculateButton);

        resultLabel = new JLabel("Ergebnis: ");
        add(resultLabel);
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }

    // Neue Getter-Methoden für die Eingabewerte
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
        if (maleButton.isSelected()) {
            return "Männlich";
        } else if (femaleButton.isSelected()) {
            return "Weiblich";
        }
        return "Unbekannt";
    }

     public void setBmrResult(String result) {
        resultLabel.setText(result);
    }

    // Neue Methode für Fehlermeldungen
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Eingabefehler", JOptionPane.ERROR_MESSAGE);
    }
}
