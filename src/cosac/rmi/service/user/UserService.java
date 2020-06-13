package cosac.rmi.service.user;

import cosac.model.UserData;
import database.dao.user.UserDataDao;
import database.dao.user.UserDataDaoJdbc;

import java.util.ArrayList;

public class UserService implements UserServiceable {

    private static final String SERVER = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    @Override
    public ArrayList<UserData> getAllUsers() {
        ArrayList<UserData> results = new ArrayList<>();
        try(UserDataDao userDataDao = new UserDataDaoJdbc(CONNECTION_STRING, USERNAME, PASSWORD)) {
            results = (ArrayList<UserData>)userDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public UserData updateUser(UserData user) {
        return null;
    }

    @Override
    public void deleteUser(String userID) {

    }

    @Override
    public void insertUser(UserData user) {

    }

}
