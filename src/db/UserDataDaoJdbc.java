package db;

import cosac.model.UserData;

import java.sql.*;
import java.util.Collection;

public class UserDataDaoJdbc implements UserDataDao {

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public UserDataDaoJdbc(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws DataAccessException{
        if(connection == null) {
            try { connection = DriverManager.getConnection(connectionString,username,password); }
            catch (SQLException exc) {
                throw new DataAccessException("Can't establish connection to database.");
            };
        }
        return connection;
    }

    @Override
    public int getCount() throws DataAccessException {
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select COUNT(userID) AS count FROM User;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public UserData getById(String userId) throws DataAccessException {
        return null;
    }

    @Override
    public Collection<UserData> getAll() throws DataAccessException {
        return null;
    }

    @Override
    public void store(UserData user) throws DataAccessException {
        //if(person.getId() != -1) throw new DataAccessException("objects cant be inserted twice");
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                    String.format("INSERT INTO User (userID, firstname, lastname, email, password, role, locked)" +
                                    "VALUES ('%s','%s','%s','%s','%s','%s','%d');",
                            user.getStudentID(),
                            user.getFirstname(),
                            user.getLastname(),
                            user.getEmail(),
                            user.getPassword(),
                            "role student",
                            1),
                    Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(String userId) throws DataAccessException {

    }

    @Override
    public void update(UserData user) throws DataAccessException {

    }

    @Override
    public void close() throws Exception {

    }

}
