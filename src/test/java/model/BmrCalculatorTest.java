package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests für den BmrCalculator")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Reihenfolge per @Order festlegen
public class BmrCalculatorTest {

    @Test
    @Order(1) // 1. Test: Mann
    @DisplayName("Berechnet BMR korrekt für einen Mann")
    void testCalculateBmrForMale() {
        // Beispielwerte: Mann, 70 kg, 175 cm, 30 Jahre
        // Erwarteter Wert nach Mifflin-St.Jeor:
        // 10*70 + 6.25*175 - 5*30 + 5 = 700 + 1093.75 - 150 + 5 = 1648.75

        UserProfile user = new UserProfile(70.0, 175, 30, "Männlich");

        double result = BmrCalculator.calculate(user);

        assertEquals(1648.75, result, 0.01, "BMR für Mann sollte korrekt berechnet werden");
    }

    @Test
    @Order(2) // 2. Test: Frau
    @DisplayName("Berechnet BMR korrekt für eine Frau")
    void testCalculateBmrForFemale() {
        // Beispielwerte: Frau, 60 kg, 165 cm, 28 Jahre
        // Erwarteter Wert nach Mifflin-St.Jeor:
        // 10*60 + 6.25*165 - 5*28 - 161 = 600 + 1031.25 - 140 - 161 = 1330.25

        UserProfile user = new UserProfile(60.0, 165, 28, "Weiblich");

        double result = BmrCalculator.calculate(user);

        assertEquals(1330.25, result, 0.01, "BMR für Frau sollte korrekt berechnet werden");
    }

    @AfterAll
    static void allTestsPassedMessage() {
        System.out.println("Alle BmrCalculator-Tests wurden erfolgreich (Mann → Frau) ausgeführt.");
    }
}