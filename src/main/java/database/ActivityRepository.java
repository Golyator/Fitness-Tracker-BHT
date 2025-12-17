package database;

import java.util.List;
import model.ActivityRecord;

public interface ActivityRepository {

    void save(ActivityRecord record) throws Exception;

    List<ActivityRecord> findAll() throws Exception;
}