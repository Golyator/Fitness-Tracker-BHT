package model;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityRecordTest  {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    @Test
    public void testConstructorActivityRecord() throws Exception {
        Date date = sdf.parse("12.12.2025");

        ActivityRecord record = new ActivityRecord(45, "medium", date, ActivityType.RUNNING);

        assertEquals(45, record.getDurationMinutes());
        assertEquals("medium", record.getIntensity());
        assertEquals(date, record.getDate());
        assertEquals(ActivityType.RUNNING, record.getActivity());

    }
}
