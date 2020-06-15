package database.dao.food;

import cosac.model.FoodData;
import database.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FoodDataDaoJdbc implements FoodDataDao {

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public FoodDataDaoJdbc(String connectionString, String username, String password) {
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

    private ArrayList<FoodData> getWhere(String query) throws DataAccessException {
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Food " + query + ";")) {
            ArrayList<FoodData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new FoodData(
                            resultSet.getInt("foodID"),
                            resultSet.getInt("Section_sectionID"),
                            resultSet.getString("name")
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
            ResultSet resultSet = statement.executeQuery("Select COUNT(foodID) AS count FROM Food;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public FoodData getById(int foodID) throws DataAccessException {
        ArrayList<FoodData> result = getWhere("WHERE foodID ='" + foodID + "'");
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Collection<FoodData> getAll() throws DataAccessException {
        return getWhere("");
    }

    @Override
    public void store(FoodData food) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                String.format("INSERT INTO Food (name, Section_sectionID)" +
                                "VALUES ('%s','%d');",
                    food.getName(),
                    food.getSectionId()),
                Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(int foodID) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Food Where foodID = ?")) {
            preparedStatement.setInt(1, foodID);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            throw new DataAccessException("SQLException: " + exc.getMessage());
        }
    }

    @Override
    public void close() throws Exception {

    }

}
