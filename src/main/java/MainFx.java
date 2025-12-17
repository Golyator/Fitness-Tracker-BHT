import controller.ActivityControllerFx;
import controller.BmrControllerFx;
import controller.FoodControllerFx;
import database.ActivityRepository;
import database.BmrRepository;
import database.FileActivityRepository;
import database.FileBmrRepository;
import database.FileFoodRepository;
import database.FoodRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ActivityViewFx;
import view.BmrViewFx;
import view.DailySummaryViewFx;
import view.FoodViewFx;

public class MainFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Repositories
        BmrRepository bmrRepository = new FileBmrRepository();
        FoodRepository foodRepository = new FileFoodRepository();
        ActivityRepository activityRepository = new FileActivityRepository();

        // Views
        BmrViewFx bmrView = new BmrViewFx();
        ActivityViewFx activityView = new ActivityViewFx();
        FoodViewFx foodView = new FoodViewFx();
        DailySummaryViewFx summaryView = new DailySummaryViewFx();

        // Reihenfolge: BMR (oben), Activity, Food, Tagesbilanz (unten)
        bmrView.addBelow(activityView.getRoot());
        bmrView.addBelow(foodView.getRoot());
        bmrView.addBelow(summaryView.getRoot());

        // Controller VERKNÜPFEN – mit SummaryView
        new BmrControllerFx(bmrView, bmrRepository, summaryView);
        new FoodControllerFx(foodView, foodRepository, summaryView);
        new ActivityControllerFx(activityView, activityRepository, summaryView);

        Scene scene = new Scene(bmrView.getRoot(), 450, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fitness-Tracker-BHT");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}