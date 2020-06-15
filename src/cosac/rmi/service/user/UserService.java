package cosac.rmi.service.user;

import cosac.model.UserData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserService extends Remote {

    ArrayList<UserData> getAllUsers() throws RemoteException;
    void updateUser(UserData user) throws RemoteException;
    void insertUser(UserData user) throws RemoteException;

}
