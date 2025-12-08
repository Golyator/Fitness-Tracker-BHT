package model;

public class UserProfile {
    private double weight;
    private double height;
    private int age;
    private String gender;
    private double bmr; // Neues Feld für das Ergebnis

    public UserProfile(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }
    
    // Getter und Setter für BMR
    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public double getBmr() {
        return bmr;
    }

    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
}
