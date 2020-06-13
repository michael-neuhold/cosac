package cosac.rmi;

import cosac.rmi.service.user.UserService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {

    private static final String SERVER = "localhost";
    private static final int RMI_PORT = 1099;
    private static final String CONNECTION_STRING = "rmi://" + SERVER + ":" + RMI_PORT;
    private static final String CONNECTION_USER_SERVICE = CONNECTION_STRING + "/UserService";
    private static final String CONNECTION_FOOD_SERVICE = CONNECTION_STRING + "/FoodService";
    private static final String CONNECTION_ORDER_SERVICE = CONNECTION_STRING + "/OrderService";
    private static final String CONNECTION_RESTRICTION_SERVICE = CONNECTION_STRING + "/RestrictionService";
    private static final String CONNECTION_SECTION_SERVICE = CONNECTION_STRING + "/SectionService";


    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(RMI_PORT);

            UserService userService = new UserService();

            Naming.rebind(CONNECTION_USER_SERVICE, UnicastRemoteObject.exportObject(userService, 0));

        } catch (RemoteException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }
}
