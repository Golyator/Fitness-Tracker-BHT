package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests für den UserProfil")

class UserProfileTest {

    @Test
    void constructorAndGettersShouldReturnPassedValues() {
        UserProfile user = new UserProfile(70.0, 175, 30, "Männlich");

        assertEquals(70.0, user.getWeight());
        assertEquals(175, user.getHeight());
        assertEquals(30, user.getAge());
        assertEquals("Männlich", user.getGender());
    }

    @Test
    void bmrSetterAndGetterShouldWork() {
        UserProfile user = new UserProfile(70.0, 175, 30, "Männlich");

        user.setBmr(1648.75);

        assertEquals(1648.75, user.getBmr(), 0.0001);
    }
}