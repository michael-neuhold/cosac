package database.dao.restriction;

import cosac.model.RestrictionData;
import database.DataAccessException;

import java.util.Collection;

public interface RestrictionDataDao extends AutoCloseable {

    int getCount() throws DataAccessException;
    RestrictionData getById(int restrictionID) throws DataAccessException;
    Collection<RestrictionData> getAll() throws DataAccessException;
    void store(RestrictionData user) throws DataAccessException;
    void delete(int restrictionID) throws DataAccessException;
    void update(RestrictionData user) throws DataAccessException;

}
