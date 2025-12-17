package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class UserProfileTest {
    
    @Test
    void constructor_setsAllFieldsCorrectly() {
        UserProfile user = new UserProfile(80.5, 180, 30, "Männlich");

        assertEquals(80.5, user.getWeight(), 0.001);
        assertEquals(180, user.getHeight(), 0.001);
        assertEquals(30, user.getAge());
        assertEquals("Männlich", user.getGender());
    }

    @Test
    void setBmr_updatesBmrCorrectly() {
        UserProfile user = new UserProfile(90.0, 185, 40, "Männlich");

        user.setBmr(1850.75);

        assertEquals(1850.75, user.getBmr(), 0.001);
    }

    @Test
    void bmr_canBeOverwritten() {
        UserProfile user = new UserProfile(65.0, 170, 20, "Weiblich");

        user.setBmr(1400.0);
        user.setBmr(1500.0);

        assertEquals(1500.0, user.getBmr(), 0.001);
    }
    
}
