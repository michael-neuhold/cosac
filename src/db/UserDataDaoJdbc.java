package db;

import cosac.model.Role;
import cosac.model.UserData;

import java.sql.*;
import java.util.ArrayList;
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
        ArrayList<UserData> result = getWhere("WHERE userID ='" + userId + "'");
        return result.isEmpty() ? null : result.get(0);
    }

    private ArrayList<UserData> getWhere(String query, Object... args) throws DataAccessException {

        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM User " + query + ";")) {
            //for(int i = 0; i < args.length;) statement.setObject(i + 1, args[i]);
            ArrayList<UserData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new UserData(
                            resultSet.getString("userID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("role") == "student" ? Role.STUDENT : Role.ADMIN,
                            resultSet.getInt("locked") == 1 ? true : false
                        )
                    );
                }
            }
            return result;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }


    @Override
    public Collection<UserData> getAll() throws DataAccessException {
        return getWhere("");
    }

    @Override
    public void store(UserData user) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                    String.format("INSERT INTO User (userID, firstname, lastname, email, password, role, locked)" +
                                    "VALUES ('%s','%s','%s','%s','%s','%s','%d');",
                            user.getStudentID(),
                            user.getFirstname(),
                            user.getLastname(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getRole() == Role.STUDENT ? "student" : "admin",
                            user.isLocked() ? 1 : 0),
                    Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(String userId) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM User Where userID = ?")) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            throw new DataAccessException("SQLException: " + exc.getMessage());
        }
    }

    @Override
    public void update(UserData user) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(
                "UPDATE User SET firstname=?, lastname=?, email=?, password=?, role=?, locked=? WHERE userID=?;"
        )) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setInt(6, user.isLocked() ? 1 : 0);
            preparedStatement.setString(7, user.getStudentID());
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }

}
