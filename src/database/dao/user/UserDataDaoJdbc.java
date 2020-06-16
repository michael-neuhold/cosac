package database.dao.user;

import cosac.model.Role;
import cosac.model.UserData;
import database.DataAccessException;

import java.sql.*;
import java.util.ArrayList;

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

    public Connection getConnection() throws DataAccessException {
        if(connection == null) {
            try { connection = DriverManager.getConnection(connectionString,username,password); }
            catch (SQLException exc) {
                throw new DataAccessException("Can't establish connection to database.");
            };
        }
        return connection;
    }

    public ArrayList<UserData> getAll() throws DataAccessException {
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM User;")) {
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
                            resultSet.getString("role") == "STUDENT" ? Role.STUDENT : Role.ADMIN,
                            resultSet.getInt("locked") == 1 ? true : false
                        )
                    );
                }
            }
            return result;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public int getCount() throws DataAccessException {
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select COUNT(userID) AS count FROM User;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void store(UserData user) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection()
            .prepareStatement("INSERT INTO User (userID, firstname, lastname, email, password, role, locked) " +
            "VALUES (?,?,?,?,?,?,?);"))
        {
            preparedStatement.setString(1, user.getStudentID());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.setInt(7, user.isLocked() ? 1 : 0);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(String userId) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection()
            .prepareStatement("DELETE FROM User Where userID = ?"))
        {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            throw new DataAccessException("SQLException: " + exc.getMessage());
        }
    }

    @Override
    public void update(UserData user) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection()
            .prepareStatement(
            "UPDATE User SET firstname=?, lastname=?, email=?, password=?, role=?, locked=? WHERE userID=?;"))
        {
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
