package db;

import cosac.model.UserData;
import java.util.Collection;

public interface UserDataDao  extends AutoCloseable{
    int getCount() throws DataAccessException;
    UserData getById(String userId) throws DataAccessException;
    Collection<UserData> getAll() throws DataAccessException;
    void store(UserData user) throws DataAccessException;
    void delete(String userId) throws DataAccessException;
    void update(UserData user) throws DataAccessException;
}
