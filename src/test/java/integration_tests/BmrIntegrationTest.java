package integration_tests;

import org.controller.BmrController;
import org.database.BmrRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.model.UserProfile;
import org.view.BmrView;

import javax.swing.JButton;

import static org.mockito.Mockito.*;

class BmrControllerIT {

    static class FakeView extends BmrView {
        String weight = "";
        String height = "";
        String age = "";
        String gender = "female";
        String result = null;
        String lastError = null;
        JButton calculate = new JButton("Calculate");

        @Override public String getWeightInput() { return weight; }
        @Override public String getHeightInput() { return height; }
        @Override public String getAgeInput() { return age; }
        @Override public String getGenderInput() { return gender; }
        @Override public JButton getCalculateButton() { return calculate; }
        @Override public void setBmrResult(String text) { result = text; }
        @Override public void showErrorMessage(String message) { lastError = message; }
    }

    private FakeView view;
    private BmrRepository repository;
    private BmrController controller;

    @BeforeEach
    void setUp() {
        view = new FakeView();
        repository = mock(BmrRepository.class);
        controller = new BmrController(view, repository);
    }

    @Test
    void validInputs_calculates_formats_saves() throws Exception {
        view.weight = "80.0";
        view.height = "180";
        view.age = "25";
        view.gender = "male";

        // Simulate clicking the button
        view.getCalculateButton().doClick();

        // Result displayed with correct prefix/suffix
        org.assertj.core.api.Assertions.assertThat(view.result).startsWith("Ergebnis: ").endsWith(" kcal/Tag");

        // Repository save called with populated UserProfile
        ArgumentCaptor<UserProfile> captor = ArgumentCaptor.forClass(UserProfile.class);
        verify(repository, times(1)).save(captor.capture());
        var saved = captor.getValue();
        org.assertj.core.api.Assertions.assertThat(saved.getWeight()).isEqualTo(80.0);
        org.assertj.core.api.Assertions.assertThat(saved.getHeight()).isEqualTo(180);
        org.assertj.core.api.Assertions.assertThat(saved.getAge()).isEqualTo(25);
        org.assertj.core.api.Assertions.assertThat(saved.getGender()).isEqualTo("male");
        org.assertj.core.api.Assertions.assertThat(saved.getBmr()).isGreaterThan(0.0);
    }

    @Test
    void invalid_number_inputs_show_error_no_save() throws Exception {
        view.weight = "xyz";
        view.height = "180";
        view.age = "25";

        view.getCalculateButton().doClick();

        org.assertj.core.api.Assertions.assertThat(view.lastError).isEqualTo("Bitte geben Sie gültige Zahlen ein!");
        verify(repository, never()).save(any());
        org.assertj.core.api.Assertions.assertThat(view.result).isNull();
    }

    @Test
    void repository_throws_error_message_shown() throws Exception {
        doThrow(new RuntimeException("DB down")).when(repository).save(any());

        view.weight = "60";
        view.height = "165";
        view.age = "22";

        view.getCalculateButton().doClick();

        org.assertj.core.api.Assertions.assertThat(view.lastError).contains("Fehler: DB down");
        verify(repository, times(1)).save(any());
    }
}
