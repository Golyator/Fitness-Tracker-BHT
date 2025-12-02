package database;

import model.UserProfile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseManager {

    private static final String FILE_NAME = "fitness_data.csv";

    // Erstellt die Datei, falls sie nicht existiert
    public static void initialize() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Kopfzeile schreiben
                writer.write("Alter,Gewicht,Größe,Geschlecht,BMR");
                writer.newLine();
                System.out.println("Datenbank-Datei erstellt: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Fehler beim Erstellen der Datenbank-Datei: " + e.getMessage());
            }
        }
    }

    // Speichert einen Nutzer in der Datei
    public static void saveUser(UserProfile user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Daten als kommagetrennte Zeile formatieren
            String line = String.format("%d,%.2f,%.2f,%s,%.2f",
                    user.getAge(),
                    user.getWeight(),
                    user.getHeight(),
                    user.getGender(),
                    user.getBmr());

            writer.write(line);
            writer.newLine();
            System.out.println("Daten gespeichert in: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
            throw new RuntimeException("Speichern fehlgeschlagen", e);
        }
    }
}