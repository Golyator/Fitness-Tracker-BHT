package database;

import model.UserProfile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileBmrRepository implements BmrRepository {

    private static final String FILE_NAME = "fitness_data.csv";

    public FileBmrRepository() {
        initialize();
    }

    private void initialize() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Alter;Gewicht;Größe;Geschlecht;BMR"); // Trennung durch semikolon
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Fehler beim Erstellen der Datei: " + e.getMessage());
            }
        }
    }

    @Override
    public void save(UserProfile user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String line = String.format("%d;%.2f;%d;%s;%.2f", // Trennung durch semikolon
                    user.getAge(),
                    user.getWeight(),
                    user.getHeight(), // ist int -> %d
                    user.getGender(),
                    user.getBmr());

            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Speichern fehlgeschlagen", e);
        }
    }
}