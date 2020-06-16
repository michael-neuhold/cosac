package database.dao.order;

import cosac.model.OrderData;
import cosac.model.OrderDataInsert;
import database.DataAccessException;

import java.sql.*;
import java.util.ArrayList;

public class OrderDataDaoJdbc implements OrderDataDao{

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public OrderDataDaoJdbc(String connectionString, String username, String password) {
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

    public ArrayList<OrderData> getAll() throws DataAccessException {
        try(PreparedStatement statement = getConnection()
            .prepareStatement(
            "SELECT orderID, User_userID, User.firstname, User.lastname, Food.name, " +
                "Restriction.startTime, Restriction.endTime FROM Order_ " +
            "INNER JOIN Restriction on Restriction.restrictionID = Restriction_restrictionID " +
            "INNER JOIN Food on Food.foodID = Food_foodID " +
            "INNER JOIN User on User.userID = User_userID "))
        {
            ArrayList<OrderData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new OrderData(
                            resultSet.getInt("orderID"),
                            resultSet.getString("Restriction.startTime") +
                            " - " + resultSet.getString("Restriction.endTime"),
                            resultSet.getString("User_userID"),
                            resultSet.getString("User.firstname"),
                            resultSet.getString("User.lastname"),
                            resultSet.getString("Food.name")
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
            ResultSet resultSet = statement.executeQuery("Select COUNT(orderID) AS count FROM Order_;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void store(OrderDataInsert order) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                String.format("INSERT INTO Order_ (Restriction_restrictionID, User_userID, Food_foodID)" +
                            "VALUES ('%d','%s','%d');",
                    order.getRestrictionId(),
                    order.getUserId(),
                    order.getFoodId(),
                Statement.RETURN_GENERATED_KEYS)
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void close() throws Exception {

    }

}
