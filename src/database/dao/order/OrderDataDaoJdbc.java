package database.dao.order;

import cosac.model.OrderData;
import database.DataAccessException;
import util.Logger;

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

    private ArrayList<OrderData> getWhere(String query, Object... args) throws DataAccessException {
        try(PreparedStatement statement = getConnection().prepareStatement(
            "SELECT orderID, User_userID, User.firstname, User.lastname, Food.name FROM Order_ " +
            "INNER JOIN Food on Food.foodID = Food_foodID " +
            "INNER JOIN User on User.userID = User_userID " +
            "INNER JOIN Restriction on Restriction.restrictionID = Restriction_restrictionID " +
            query + ";")) {
            //for(int i = 0; i < args.length;) statement.setObject(i + 1, args[i]);
            ArrayList<OrderData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new OrderData(
                            resultSet.getInt("orderID"),
                            resultSet.getString("User_userID"),
                            resultSet.getString("User.firstname"),
                            resultSet.getString("User.lastname"),
                            resultSet.getString("Food.name")
                        )
                   );
                }
            }
            System.out.println("## order: \n");
            Logger.dataTransfer(result);
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
    public OrderData getById(int orderID) throws DataAccessException {
        ArrayList<OrderData> result = getWhere("WHERE orderID = " + orderID);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public ArrayList<OrderData> getByRestrictionId(int restrictionId) throws DataAccessException {
        return getWhere("WHERE Restriction_restrictionID = " + restrictionId);
    }

    @Override
    public ArrayList<OrderData> getAll() throws DataAccessException {
        return getWhere("");
    }

    @Override
    public void store(OrderData order) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
           /* statement.executeUpdate(
                    String.format("INSERT INTO User (restrictionID, startTime, endTime, visitorLimit)" +
                                    "VALUES ('%d','%s','%s','%d');",
                            order.getTimeslotId(),
                            order.getStartTime(),
                            order.getEndTime(),
                            order.getVisitorLimit()),
                    Statement.RETURN_GENERATED_KEYS
            );*/
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void close() throws Exception {

    }


}
