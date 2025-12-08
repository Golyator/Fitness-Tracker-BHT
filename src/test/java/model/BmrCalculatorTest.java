package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BmrCalculatorTest {
    
    @Test
    void calculate_returnsCorrectBmrForMale() {
        // TODO: Konstruktor an deine UserProfile-Implementierung anpassen
        UserProfile user = new UserProfile(
                80.0,        // Gewicht in kg
                180.0,       // Größe in cm
                30,          // Alter in Jahren
                "Männlich"   // Geschlecht
        );

        double result = BmrCalculator.calculate(user);

        // base = 10*80 + 6.25*180 - 5*30 = 1775
        // männlich: +5 = 1780
        assertEquals(1780.0, result, 0.1);
    }

    @Test
    void calculate_returnsCorrectBmrForFemale() {
        UserProfile user = new UserProfile(
                60.0,
                165.0,
                25,
                "Weiblich"
        );

        double result = BmrCalculator.calculate(user);

        // base = 10*60 + 6.25*165 - 5*25
        //      = 600 + 1031.25 - 125 = 1506.25
        // weiblich: -161 = 1345.25
        assertEquals(1345.25, result, 0.1);
    }

    @Test
    void calculate_isCaseInsensitiveForMale() {
        UserProfile user = new UserProfile(
                80.0,
                180.0,
                30,
                "mÄnNlIcH"
        );

        double result = BmrCalculator.calculate(user);

        assertEquals(1780.0, result, 0.1);
    }

    @Test
    void calculate_usesFemaleBranchForNonMaleGender() {
        UserProfile user = new UserProfile(
                80.0,
                180.0,
                30,
                "Divers"
        );

        double result = BmrCalculator.calculate(user);

        // gleiche base wie oben: 1775 - 161 = 1614
        assertEquals(1614.0, result, 0.1);
    }
}
