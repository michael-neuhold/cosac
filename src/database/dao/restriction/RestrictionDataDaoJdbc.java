package database.dao.restriction;

import cosac.model.RestrictionData;
import database.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class RestrictionDataDaoJdbc implements RestrictionDataDao {

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public RestrictionDataDaoJdbc(String connectionString, String username, String password) {
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

    private ArrayList<RestrictionData> getWhere(String query, Object... args) throws DataAccessException {
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Restriction " + query + ";")) {
            //for(int i = 0; i < args.length;) statement.setObject(i + 1, args[i]);
            ArrayList<RestrictionData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new RestrictionData(
                            resultSet.getInt("restrictionID"),
                            resultSet.getString("startTime"),
                            resultSet.getString("endTime"),
                            resultSet.getInt("visitorLimit")
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
            ResultSet resultSet = statement.executeQuery("Select COUNT(restrictionID) AS count FROM Restriction;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public RestrictionData getById(int restrictionID) throws DataAccessException {
        ArrayList<RestrictionData> result = getWhere("WHERE restrictionID ='" + restrictionID + "'");
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Collection<RestrictionData> getAll() throws DataAccessException {
        return getWhere("");
    }

    @Override
    public void store(RestrictionData restriction) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                    String.format("INSERT INTO Restriction (restrictionID, startTime, endTime, visitorLimit)" +
                                    "VALUES ('%d','%s','%s','%d');",
                            restriction.getTimeslotId(),
                            restriction.getStartTime(),
                            restriction.getEndTime(),
                            restriction.getVisitorLimit()),
                    Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(int restrictionID) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Restriction Where restrictionID = ?")) {
            preparedStatement.setInt(1, restrictionID);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            throw new DataAccessException("SQLException: " + exc.getMessage());
        }
    }

    @Override
    public void update(RestrictionData restriction) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(
                "UPDATE Restriction SET startTime=?, endTime=?, visitorLimit=? WHERE restrictionID=?;"
        )) {
            preparedStatement.setString(1, restriction.getStartTime());
            preparedStatement.setString(2, restriction.getStartTime());
            preparedStatement.setInt(3, restriction.getVisitorLimit());
            preparedStatement.setInt(4, restriction.getTimeslotId());
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }

}
