package database.dao.food;

import cosac.model.FoodData;
import database.DataAccessException;

import java.util.Collection;

public interface FoodDataDao extends AutoCloseable {

    int getCount() throws DataAccessException;
    FoodData getById(int foodID) throws DataAccessException;
    Collection<FoodData> getAll() throws DataAccessException;
    void store(FoodData food) throws DataAccessException;
    void delete(int foodID) throws DataAccessException;

}
