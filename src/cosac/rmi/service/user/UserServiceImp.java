package cosac.rmi.service.user;

import cosac.model.UserData;
import cosac.rmi.config.RMIConfig;
import database.dao.user.UserDataDao;
import database.dao.user.UserDataDaoJdbc;

import java.util.ArrayList;

public class UserServiceImp implements UserService {

    @Override
    public ArrayList<UserData> getAllUsers() {
        ArrayList<UserData> results = new ArrayList<>();
        try(UserDataDao userDataDao = new UserDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<UserData>)userDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public void updateUser(UserData user) {
        try(UserDataDao userDataDao = new UserDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            userDataDao.update(user);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

    @Override
    public void insertUser(UserData user) {
        try(UserDataDao userDataDao = new UserDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            userDataDao.store(user);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

}
