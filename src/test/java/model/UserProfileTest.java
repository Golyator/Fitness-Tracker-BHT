package model;

import org.junit.jupiter.api.Test;

import model.UserProfile;

import static org.junit.jupiter.api.Assertions.*;


public class UserProfileTest {
    
    @Test
    void constructor_setsAllFieldsCorrectly() {
        UserProfile user = new UserProfile(80.5, 180.0, 30, "Männlich");

        assertEquals(80.5, user.getWeight(), 0.001);
        assertEquals(180.0, user.getHeight(), 0.001);
        assertEquals(30, user.getAge());
        assertEquals("Männlich", user.getGender());
    }

    @Test
    void bmr_isZeroByDefault() {
        UserProfile user = new UserProfile(70.0, 175.0, 25, "Weiblich");

        assertEquals(0.0, user.getBmr(), 0.001);
    }

    @Test
    void setBmr_updatesBmrCorrectly() {
        UserProfile user = new UserProfile(90.0, 185.0, 40, "Männlich");

        user.setBmr(1850.75);

        assertEquals(1850.75, user.getBmr(), 0.001);
    }

    @Test
    void bmr_canBeOverwritten() {
        UserProfile user = new UserProfile(65.0, 170.0, 20, "Weiblich");

        user.setBmr(1400.0);
        user.setBmr(1500.0);

        assertEquals(1500.0, user.getBmr(), 0.001);
    }
    
}
