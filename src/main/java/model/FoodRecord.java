package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodRecord {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    private float calories;
    private float portionSize;
    private Date date;
    
    public FoodRecord(float calories, float portionSize, Date date) {
        this.calories = calories;
        this.portionSize = portionSize;
        this.date = date;
    }

    // Getter-Methoden
    public float getCalories() {
        return calories;
    }
    public float getPortionSize() {
        return portionSize;
    }
    public Date getDate() {
        return date;
    }

    // Setter-Methoden
    public void  setCalories(float calories) {
        this.calories = calories;
    }
    public void  setPortionSize( float portionSize) {
        this.portionSize = portionSize;
    }
    public void setDate( Date date) {
        this.date = date;
    }
}
