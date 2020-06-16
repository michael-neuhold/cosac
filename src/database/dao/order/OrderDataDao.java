package database.dao.order;

import cosac.model.OrderData;
import cosac.model.OrderDataInsert;
import database.DataAccessException;

import java.util.Collection;

public interface OrderDataDao extends AutoCloseable {

    int getCount() throws DataAccessException;
    Collection<OrderData> getAll() throws DataAccessException;
    void store(OrderDataInsert order) throws DataAccessException;

}
