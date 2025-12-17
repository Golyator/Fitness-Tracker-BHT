package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.FoodRecord;

public class FileFoodRepository implements FoodRepository {

    private static final String FILE_NAME = "food_data.csv";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    @Override
    public void save(FoodRecord record) throws Exception {
        File file = new File(FILE_NAME);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Header nur einmal schreiben, wenn Datei neu angelegt wird
            if (!fileExists) {
                writer.write("date;calories;portionSize");
                writer.newLine();
            }
            String dateString = sdf.format(record.getDate());
            writer.write(dateString + ";" + record.getCalories() + ";" + record.getPortionSize());
            writer.newLine();
        }
    }

    @Override
    public List<FoodRecord> findAll() throws Exception {
        List<FoodRecord> records = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return records;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                // Header überspringen
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.isBlank()) {
                    continue;
                }
                FoodRecord record = parseLine(line);
                if (record != null) {
                    records.add(record);
                }
            }
        }
        return records;
    }

    private FoodRecord parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) {
            return null;
        }
        try {
            Date date = sdf.parse(parts[0]);
            float calories = Float.parseFloat(parts[1]);
            float portionSize = Float.parseFloat(parts[2]);
            return new FoodRecord(calories, portionSize, date);
        } catch (ParseException | NumberFormatException e) {
            // Zeile ignorieren, wenn sie nicht lesbar ist
            return null;
        }
    }
}
