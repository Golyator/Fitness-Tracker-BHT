package org.model;

public class FoodType {
    private int id;
    private String name;
    private float caloriesPerUnit;

    public FoodType(int id, String name, float caloriesPerUnit) {
        this.id = id;
        this.name = name;
        this.caloriesPerUnit = caloriesPerUnit;
    }

    // Getter-Methoden
    public int getId() {
        return id;
    }
    public float getCaloriesPerUnit() {
        return caloriesPerUnit;
    }
    public String getName() {
        return name;
    }

    // Setter-Methoden  
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCaloriesPerUnit(float caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }
}
