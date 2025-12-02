import controller.BmrController;
import database.DatabaseManager;
import view.BmrView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // 1. Datenbank (Datei) initialisieren
        DatabaseManager.initialize();

        // Swing Start
        SwingUtilities.invokeLater(() -> {
            BmrView view = new BmrView();
            new BmrController(view);
            view.setVisible(true);
        });
    }
}