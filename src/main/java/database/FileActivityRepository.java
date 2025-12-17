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

import model.ActivityRecord;
import model.ActivityType;

public class FileActivityRepository implements ActivityRepository {

    private static final String FILE_NAME = "activity_data.csv";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    @Override
    public void save(ActivityRecord record) throws Exception {
        File file = new File(FILE_NAME);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                writer.write("date;activityType;durationMinutes;intensity");
                writer.newLine();
            }
            String dateString = sdf.format(record.getDate());
            writer.write(
                    dateString + ";" +
                            record.getActivity().name() + ";" +
                            record.getDurationMinutes() + ";" +
                            record.getIntensity()
            );
            writer.newLine();
        }
    }

    @Override
    public List<ActivityRecord> findAll() throws Exception {
        List<ActivityRecord> records = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return records;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Header überspringen
                }
                if (line.isBlank()) {
                    continue;
                }
                ActivityRecord record = parseLine(line);
                if (record != null) {
                    records.add(record);
                }
            }
        }

        return records;
    }

    private ActivityRecord parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 4) {
            return null;
        }
        try {
            Date date = sdf.parse(parts[0]);
            ActivityType type = ActivityType.valueOf(parts[1]);
            int durationMinutes = Integer.parseInt(parts[2]);
            String intensity = parts[3];

            return new ActivityRecord(durationMinutes, intensity, date, type);
        } catch (ParseException | IllegalArgumentException e) {
            return null;
        }
    }
}