package database;

import java.util.List;
import model.FoodRecord;

public interface FoodRepository {

    void save(FoodRecord record) throws Exception;

    List<FoodRecord> findAll() throws Exception;
}