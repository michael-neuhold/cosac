package database.dao.section;

import cosac.model.SectionData;
import database.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SectionDataDaoJdbc implements SectionDataDao {

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public SectionDataDaoJdbc(String connectionString, String username, String password) {
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

    private ArrayList<SectionData> getWhere(String query, Object... args) throws DataAccessException {
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Section " + query + ";")) {
            //for(int i = 0; i < args.length;) statement.setObject(i + 1, args[i]);
            ArrayList<SectionData> result = new ArrayList<>();
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(
                        new SectionData(
                            resultSet.getInt("sectionID"),
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
            ResultSet resultSet = statement.executeQuery("Select COUNT(sectionID) AS count FROM Section;")) {
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch(SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public SectionData getById(int sectionID) throws DataAccessException {
        ArrayList<SectionData> result = getWhere("WHERE sectionID ='" + sectionID + "'");
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Collection<SectionData> getAll() throws DataAccessException {
        return getWhere("");
    }

    @Override
    public void store(SectionData section) throws DataAccessException {
        try(Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(
                    String.format("INSERT INTO Section (sectionID, name)" +
                                    "VALUES ('%d','%s');",
                            section.getId(),
                            section.getName()),
                    Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException exc) { throw new DataAccessException("SQLException: " + exc.getMessage()); }
    }

    @Override
    public void delete(int sectionID) throws DataAccessException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Section Where sectionID = ?")) {
            preparedStatement.setInt(1, sectionID);
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            throw new DataAccessException("SQLException: " + exc.getMessage());
        }
    }

    @Override
    public void close() throws Exception {

    }

}
