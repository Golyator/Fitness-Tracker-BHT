package integration_tests;

import controller.ActivityControllerFx;
import database.ActivityRepository;
import model.ActivityType;
import model.CurrentUserContext;
import model.UserProfile;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import view.ActivityViewFx;
import view.DailySummaryViewFx;

import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javafx.application.Platform;

class ActivityControllerFxIT {

    static class TestActivityViewFx extends ActivityViewFx {
        ActivityType selectedType;
        String durationInput;
        String intensityInput;
        String lastError;
        String lastResultText;
        boolean cleared;

        @Override
        public ActivityType getSelectedActivityType() {
            return selectedType;
        }

        @Override
        public String getDurationInput() {
            return durationInput;
        }

        @Override
        public String getIntensityInput() {
            return intensityInput;
        }

        @Override
        public void setResultText(String text) {
            lastResultText = text;
        }

        @Override
        public void clearInputs() {
            cleared = true;
        }

        @Override
        public void showErrorMessage(String msg) {
            lastError = msg;
        }
    }

    static class TestDailySummaryViewFx extends DailySummaryViewFx {
        double lastAddedCalories;

        @Override
        public void addActivityCalories(double calories) {
            lastAddedCalories = calories;
        }
    }

    private static void invokeOnAdd(ActivityControllerFx controller) throws Exception {
        Method m = ActivityControllerFx.class.getDeclaredMethod("onAddActivity");
        m.setAccessible(true);
        m.invoke(controller);
    }

    @BeforeAll
    static void startFx() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");

        try {
            Platform.startup(() -> {
            });
        } catch (IllegalStateException ignored) {
        }
    }

    @Test
    void noUserShowsErrorAndDoesNotSave() throws Exception {
        try (MockedStatic<CurrentUserContext> ctx = mockStatic(CurrentUserContext.class)) {
            ctx.when(CurrentUserContext::hasUser).thenReturn(false);

            TestActivityViewFx view = new TestActivityViewFx();
            ActivityRepository repository = mock(ActivityRepository.class);
            TestDailySummaryViewFx summaryView = new TestDailySummaryViewFx();

            ActivityControllerFx controller = new ActivityControllerFx(view, repository, summaryView);
            invokeOnAdd(controller);

            assertEquals("Bitte zuerst im BMR-Rechner ein Profil berechnen.", view.lastError);
            verifyNoInteractions(repository);
            assertEquals(0.0, summaryView.lastAddedCalories, 0.0001);
        }
    }

    @Test
    void invalidDurationFormatShowsSpecificError() throws Exception {
        UserProfile user = new UserProfile(80, 190, 67, "männlich");

        try (MockedStatic<CurrentUserContext> ctx = mockStatic(CurrentUserContext.class)) {
            ctx.when(CurrentUserContext::hasUser).thenReturn(true);
            ctx.when(CurrentUserContext::getCurrentUser).thenReturn(user);

            TestActivityViewFx view = new TestActivityViewFx();
            view.selectedType = ActivityType.HIIT;
            view.durationInput = "abc";
            view.intensityInput = "mittel";

            ActivityRepository repository = mock(ActivityRepository.class);
            TestDailySummaryViewFx summaryView = new TestDailySummaryViewFx();

            ActivityControllerFx controller = new ActivityControllerFx(view, repository, summaryView);
            invokeOnAdd(controller);

            assertEquals("Bitte eine gültige Zahl für die Dauer eingeben.", view.lastError);
            verifyNoInteractions(repository);
            assertEquals(0.0, summaryView.lastAddedCalories, 0.0001);
        }
    }

    @Test
    void durationLeqZeroShowsError() throws Exception {
        UserProfile user = new UserProfile(80, 190, 67, "männlich");

        try (MockedStatic<CurrentUserContext> ctx = mockStatic(CurrentUserContext.class)) {
            ctx.when(CurrentUserContext::hasUser).thenReturn(true);
            ctx.when(CurrentUserContext::getCurrentUser).thenReturn(user);

            TestActivityViewFx view = new TestActivityViewFx();
            view.selectedType = ActivityType.CYCLING;
            view.durationInput = "0";
            view.intensityInput = "mittel";

            ActivityRepository repository = mock(ActivityRepository.class);
            TestDailySummaryViewFx summaryView = new TestDailySummaryViewFx();

            ActivityControllerFx controller = new ActivityControllerFx(view, repository, summaryView);
            invokeOnAdd(controller);

            assertEquals("Die Dauer muss größer als 0 sein.", view.lastError);
            verifyNoInteractions(repository);
            assertEquals(0.0, summaryView.lastAddedCalories, 0.0001);
        }
    }

    @Test
    void missingActivityTypeShowsError() throws Exception {
        UserProfile user = new UserProfile(80, 190, 67, "männlich");

        try (MockedStatic<CurrentUserContext> ctx = mockStatic(CurrentUserContext.class)) {
            ctx.when(CurrentUserContext::hasUser).thenReturn(true);
            ctx.when(CurrentUserContext::getCurrentUser).thenReturn(user);

            TestActivityViewFx view = new TestActivityViewFx();
            view.selectedType = null;
            view.durationInput = "30";
            view.intensityInput = "mittel";

            ActivityRepository repository = mock(ActivityRepository.class);
            TestDailySummaryViewFx summaryView = new TestDailySummaryViewFx();

            ActivityControllerFx controller = new ActivityControllerFx(view, repository, summaryView);
            invokeOnAdd(controller);

            assertEquals("Bitte eine Aktivität auswählen.", view.lastError);
            verifyNoInteractions(repository);
            assertEquals(0.0, summaryView.lastAddedCalories, 0.0001);
        }
    }

    @Test
    void missingIntensityShowsError() throws Exception {
        UserProfile user = new UserProfile(80, 190, 67, "männlich");

        try (MockedStatic<CurrentUserContext> ctx = mockStatic(CurrentUserContext.class)) {
            ctx.when(CurrentUserContext::hasUser).thenReturn(true);
            ctx.when(CurrentUserContext::getCurrentUser).thenReturn(user);

            TestActivityViewFx view = new TestActivityViewFx();
            view.selectedType = ActivityType.HIIT;
            view.durationInput = "30";
            view.intensityInput = " ";

            ActivityRepository repository = mock(ActivityRepository.class);
            TestDailySummaryViewFx summaryView = new TestDailySummaryViewFx();

            ActivityControllerFx controller = new ActivityControllerFx(view, repository, summaryView);
            invokeOnAdd(controller);
            assertEquals(0.0, summaryView.lastAddedCalories, 0.0001);
        }
    }
}
