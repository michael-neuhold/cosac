package cosac.rmi;

import cosac.model.*;
import cosac.rmi.config.RMIConfig;
import cosac.rmi.service.food.FoodService;
import cosac.rmi.service.order.OrderService;
import cosac.rmi.service.restriction.RestrictionService;
import cosac.rmi.service.section.SectionService;
import cosac.rmi.service.user.UserService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClient {

    public static ArrayList<?> connect(Get protocol) {
        ArrayList<?> data = null;
        try {
            switch (protocol) {
                case GET_FOOD_DATA_SETS:        data = getFoodDataFromDB();
                case GET_ORDER_DATA_SETS:       data = getOrderDataFromDB();
                case GET_RESTRICTION_DATA_SETS: data = getRestrictionDataFromDB();
                case GET_SECTION_DATA_SETS:     data = getSectionDataFromDB();
                case GET_USER_DATA_SETS:        data = getUserDataFromDB();
            }
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
        return data;
    }

    public static void connect(Post protocol, Object data) {
        try {
            switch (protocol) {
                case UPDATE_USER_DATA_SET: updateUserDataAtDB((UserData) data); break;
                case INSERT_USER_DATA_SET: insertUserDataAtDB((UserData) data); break;
                case INSERT_RESTRICTION_DATA_SET:  break;
                case INSERT_FOOD_DATA_SET:  break;
                case INSERT_SECTION_DATA_SET:  break;
                case UPDATE_RESTRICTION_DATA_SET:  break;
            }
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    // GET REQUESTS

    public static ArrayList<UserData> getUserDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
        return userService.getAllUsers();
    }

    public static ArrayList<FoodData> getFoodDataFromDB()
        throws RemoteException, NotBoundException, MalformedURLException
    {
        FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
        return foodService.getAllFood();
    }

    public static ArrayList<RestrictionData> getRestrictionDataFromDB()
        throws RemoteException, NotBoundException, MalformedURLException
    {
        RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
        return restrictionService.getAllRestrictions();
    }

    public static ArrayList<SectionData> getSectionDataFromDB()
        throws RemoteException, NotBoundException, MalformedURLException
    {
        SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
        return sectionService.getAllSection();
    }

    public static ArrayList<OrderData> getOrderDataFromDB()
        throws RemoteException, NotBoundException, MalformedURLException
    {
        OrderService orderService = (OrderService) Naming.lookup(RMIConfig.CONNECTION_ORDER_SERVICE);
        return orderService.getAllOrders();
    }

    // POST REQUESTS

    public static void updateUserDataAtDB(UserData user)
            throws RemoteException, NotBoundException, MalformedURLException
    {
        UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
        userService.updateUser(user);
    }

    public static void insertUserDataAtDB(UserData user)
        throws RemoteException, MalformedURLException, NotBoundException
    {
        UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
        userService.insertUser(user);
    }

}
