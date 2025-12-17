package org;

import org.database.FileBmrRepository;
import org.controller.BmrController;
import org.view.BmrView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // 1. Repository erstellen (kümmert sich selbst um Init)
        FileBmrRepository repository = new FileBmrRepository();

        // Swing Start
        SwingUtilities.invokeLater(() -> {
            BmrView view = new BmrView();
            // Repository in Controller injizieren
            new BmrController(view, repository);
            view.setVisible(true);
        });
    }
}