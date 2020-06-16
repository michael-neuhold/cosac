package database.dao.user;

import cosac.model.UserData;
import database.DataAccessException;

import java.util.Collection;

public interface UserDataDao  extends AutoCloseable {
    int getCount() throws DataAccessException;
    Collection<UserData> getAll() throws DataAccessException;
    void store(UserData user) throws DataAccessException;
    void delete(String userId) throws DataAccessException;
    void update(UserData user) throws DataAccessException;
}
