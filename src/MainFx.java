import controller.BmrControllerFx;
import database.BmrRepository;
import database.FileBmrRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.BmrViewFx;

public class MainFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Repository wie bisher anlegen
        BmrRepository repository = new FileBmrRepository();

        // 2. JavaFX-View erzeugen
        BmrViewFx view = new BmrViewFx();

        // 3. Controller anbinden
        new BmrControllerFx(view, repository);

        // 4. Scene und Stage konfigurieren
        Scene scene = new Scene(view.getRoot(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BMR Rechner (JavaFX)");

        // Fenster darf frei verändert werden, Layout passt sich automatisch an
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Startet die JavaFX Application
    }
}