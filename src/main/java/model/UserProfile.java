package model;

public class UserProfile {
    private double weight;
    private int height;
    private int age;
    private String gender;
    private double bmr; // Neues Feld für das Ergebnis

    public UserProfile(double weight, int height, int age, String gender) {
        validateInputs(weight, height, age);
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    private void validateInputs(double weight, int height, int age) {
        // Schritt 1: Negative Eingaben abfangen
        if (weight < 0 || height < 0 || age < 0) {
            throw new IllegalArgumentException("Negative Eingaben sind nicht erlaubt.");
        }

        // Schritt 2: Realistische Grenzen prüfen
        if (weight <= 30|| weight >= 230) {
            throw new IllegalArgumentException("Das Gewicht muss größer als 30 und kleiner als 230 kg sein.");
        }
        if (height <= 150 || height >= 210) {
            throw new IllegalArgumentException("Die Größe muss zwischen 150 und 210 cm liegen.");
        }
        if (age < 14 || age > 90) {
            throw new IllegalArgumentException("Das Alter muss zwischen 14 und 90 Jahren liegen.");
        }
    }
    
    // Getter und Setter für BMR
    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public double getBmr() {
        return bmr;
    }

    public double getWeight() { return weight; }
    public int getHeight() { return height; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
}
