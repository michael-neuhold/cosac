package database.dao.order;

import cosac.model.OrderData;
import database.DataAccessException;

import java.util.ArrayList;
import java.util.Collection;

public interface OrderDataDao extends AutoCloseable {

    int getCount() throws DataAccessException;
    OrderData getById(int orderID) throws DataAccessException;
    ArrayList<OrderData> getByRestrictionId(int restrictionId) throws DataAccessException;
    Collection<OrderData> getAll() throws DataAccessException;
    void store(OrderData order) throws DataAccessException;

}
