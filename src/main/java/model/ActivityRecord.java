package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityRecord {
    private int durationMinutes;
    private String intensity;
    private Date date;
    private ActivityType activity;

    public ActivityRecord(int durationMinutes, String intensity, Date date, ActivityType activity) {
        this.durationMinutes = durationMinutes;
        this.intensity = intensity;
        this.date = date;
        this.activity = activity;

    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public String getIntensity() {
        return intensity;
    }
    public Date getDate() {
        return date;
    }
    public ActivityType getActivity() {
        return activity;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setActivity(ActivityType activity) {
        this.activity = activity;
    }
}

