package model;

// Diese Klasse ist ein einfaches "POJO" (Plain Old Java Object)
public class UserProfile {
    private int age;
    private double weight; // in kg
    private double height; // in cm
    private String gender; // "Männlich" oder "Weiblich"
    private double bmr;    // Berechneter Wert

    public UserProfile(int age, double weight, double height, String gender) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    // Getter und Setter
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getGender() { return gender; }
    
    public void setBmr(double bmr) { this.bmr = bmr; }
    public double getBmr() { return bmr; }
}
