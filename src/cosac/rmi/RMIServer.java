package cosac.rmi;

import cosac.rmi.config.RMIConfig;
import cosac.rmi.service.food.FoodServiceImp;
import cosac.rmi.service.order.OrderServiceImp;
import cosac.rmi.service.restriction.RestrictionServiceImp;
import cosac.rmi.service.section.SectionServiceImp;
import cosac.rmi.service.user.UserServiceImp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {

    public static void main(String[] args) {
        try {

            LocateRegistry.createRegistry(RMIConfig.RMI_PORT);

            Naming.rebind(
                RMIConfig.ENDPOINT_USER_SERVICE,
                UnicastRemoteObject.exportObject(new UserServiceImp(), 0)
            );

            Naming.rebind(
                RMIConfig.ENDPOINT_SECTION_SERVICE,
                UnicastRemoteObject.exportObject(new SectionServiceImp(), 0)
            );

            Naming.rebind(
                RMIConfig.ENDPOINT_FOOD_SERVICE,
                UnicastRemoteObject.exportObject(new FoodServiceImp(), 0)
            );

            Naming.rebind(
                RMIConfig.ENDPOINT_RESTRICTION_SERVICE,
                UnicastRemoteObject.exportObject(new RestrictionServiceImp(), 0)
            );

            Naming.rebind(
                RMIConfig.ENDPOINT_ORDER_SERVICE,
                UnicastRemoteObject.exportObject(new OrderServiceImp(), 0)
            );

        } catch (RemoteException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }
}
