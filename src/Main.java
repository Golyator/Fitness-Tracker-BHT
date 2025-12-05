import controller.BmrController;
import database.BmrRepository;
import database.FileBmrRepository;
import view.BmrView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // 1. Repository erstellen (kümmert sich selbst um Init)
        BmrRepository repository = new FileBmrRepository();

        // Swing Start
        SwingUtilities.invokeLater(() -> {
            BmrView view = new BmrView();
            // Repository in Controller injizieren
            new BmrController(view, repository);
            view.setVisible(true);
        });
    }
}