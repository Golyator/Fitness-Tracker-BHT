package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ActivityTypeTest {

    @Test
    void runningHasCorrectMetValue(){
        assertEquals(8.3, ActivityType.RUNNING.getMet(), 0.001);
    }
    @Test
    void walkingHasCorrectValue(){
        assertEquals(3.5, ActivityType.WALKING.getMet(), 0.001);
    }
    @Test
    void cyclingHasCorrectValue(){
        assertEquals(7.2, ActivityType.CYCLING.getMet(), 0.001);
    }
}
