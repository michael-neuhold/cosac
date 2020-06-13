package cosac.rmi;

import cosac.model.UserData;
import cosac.rmi.service.user.UserServiceable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClient {

    private static final String SERVER = "localhost";
    private static final String CONNECTION_UserService = "rmi://" + SERVER + "/UserService";

    public static void main(String[] args) {
        System.out.println("lookup for " + CONNECTION_UserService);
        try {

            UserServiceable userService = (UserServiceable) Naming.lookup(CONNECTION_UserService);
            ArrayList<UserData> users = userService.getAllUsers();
            for(UserData userData : users) {
                System.out.println("client -> " + userData);
            }

        } catch (MalformedURLException | RemoteException | NotBoundException exc) {
            exc.printStackTrace();
        }
    }

}
