package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.model.FoodType;

public class FoodTypeTest {

    @Test
    void constructor_setsAllFieldsCorrectly() {
        FoodType ft = new FoodType(1, "Apple", 52.0f);

        assertEquals(1, ft.getId());
        assertEquals("Apple", ft.getName());
        assertEquals(52.0f, ft.getCaloriesPerUnit(), 0.001f);
    }

    @Test
    void fields_canBeUpdated() {
        FoodType ft = new FoodType(2, "Banana", 89.0f);
        ft.setId(3);
        ft.setName("Orange");
        ft.setCaloriesPerUnit(47.5f);

        assertEquals(3, ft.getId());
        assertEquals("Orange", ft.getName());
        assertEquals(47.5f, ft.getCaloriesPerUnit(), 0.001f);
    }
}
