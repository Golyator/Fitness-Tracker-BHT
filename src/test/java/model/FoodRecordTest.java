package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FoodRecordTest {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        @Test
        void constructor_setsAllFieldsCorrectly() throws Exception {
        Date date = sdf.parse("08.12.2025");
        FoodRecord fr = new FoodRecord(89.0f, 0.8f, date);

        assertEquals(89.0f, fr.getCalories());
        assertEquals(0.8f, fr.getPortionSize());
        assertEquals(date, fr.getDate());
    }

        @Test
        void fields_canBeUpdated() throws Exception{
        Date date1 = sdf.parse("08.12.2025");
        FoodRecord fr = new FoodRecord(89.0f,0.75f, date1);
        Date date2 = sdf.parse("03.12.2025");
        fr.setCalories(86.0f);
        fr.setPortionSize(0.9f);
        fr.setDate(date2);

        assertEquals(86.0f, fr.getCalories());
        assertEquals(0.9f, fr.getPortionSize());
        assertEquals(date2, fr.getDate());
        }
    
}
