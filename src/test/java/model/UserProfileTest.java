package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests für den UserProfil")

class UserProfileTest {

    @Test
    @DisplayName("Konstruktortest mit setter und getter")
    void constructorAndGettersShouldReturnPassedValues() {
        UserProfile user = new UserProfile(70.0, 175, 30, "Männlich");

        assertEquals(70.0, user.getWeight());
        assertEquals(175, user.getHeight());
        assertEquals(30, user.getAge());
        assertEquals("Männlich", user.getGender());
    }

    @Test
    @DisplayName("Bmr-Getter- und Settertest")
    void bmrSetterAndGetterShouldWork() {
        UserProfile user = new UserProfile(70.0, 175, 30, "Männlich");

        user.setBmr(1648.75);

        assertEquals(1648.75, user.getBmr(), 0.0001);
    }
    @Test
    @DisplayName("Negatives Gewicht wird abgelehnt")
    void constructorRejectsNegativeWeight() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new UserProfile(-1.0, 175, 30, "Männlich")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("negativ"),
                "Fehlermeldung sollte negative Eingaben erwähnen");
    }

    @Test
    @DisplayName("Negatives Alter und negative Größe werden abgelehnt")
    void constructorRejectsNegativeHeightAndAge() {
        IllegalArgumentException exHeight = assertThrows(
                IllegalArgumentException.class,
                () -> new UserProfile(70.0, -10, 30, "Männlich")
        );
        assertTrue(exHeight.getMessage().toLowerCase().contains("negativ"));

        IllegalArgumentException exAge = assertThrows(
                IllegalArgumentException.class,
                () -> new UserProfile(70.0, 175, -5, "Männlich")
        );
        assertTrue(exAge.getMessage().toLowerCase().contains("negativ"));
    }

    @Test
    @DisplayName("Nicht realistische Werte (Gewicht <= 30, Größe <= 150, Alter < 14 oder > 90) werden abgelehnt")
    void constructorRejectsUnrealisticValues() {
        // Gewicht <= 30
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(30.0, 175, 30, "Männlich"));
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(25.0, 175, 30, "Weiblich"));

        // Größe <= 150, >= 210
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(70.0, 150, 30, "Männlich"));
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(70.0, 211, 30, "Weiblich"));

        // Alter < 14
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(70.0, 175, 13, "Männlich"));

        // Alter > 90
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile(70.0, 175, 91, "Weiblich"));
    }

    @Test
    @DisplayName("Grenzwerte innerhalb des gültigen Bereichs werden akzeptiert")
    void constructorAcceptsBoundaryValues() {
        // Gewicht minimal gerade noch gültig > 30, also 30.1
        UserProfile user1 = new UserProfile(30.1, 151, 14, "Männlich");
        assertEquals(30.1, user1.getWeight());

        // Maximales Alter 90
        UserProfile user2 = new UserProfile(80.0, 180, 90, "Weiblich");
        assertEquals(90, user2.getAge());
    }
}