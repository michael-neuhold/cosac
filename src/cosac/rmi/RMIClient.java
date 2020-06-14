package cosac.rmi;

import cosac.client.DataContainer;
import cosac.model.UserData;
import cosac.rmi.service.food.FoodService;
import cosac.rmi.service.order.OrderService;
import cosac.rmi.service.restriction.RestrictionService;
import cosac.rmi.service.section.SectionService;
import cosac.rmi.service.user.UserService;
import javafx.collections.FXCollections;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClient {

    private static final String SERVER = "localhost";
    private static final String CONNECTION_STRING = "rmi://" + SERVER;
    private static final String CONNECTION_USER_SERVICE = CONNECTION_STRING + "/UserService";
    private static final String CONNECTION_SECTION_SERVICE = CONNECTION_STRING + "/SectionService";
    private static final String CONNECTION_FOOD_SERVICE = CONNECTION_STRING + "/FoodService";
    private static final String CONNECTION_RESTRICTION_SERVICE = CONNECTION_STRING + "/RestrictionService";
    private static final String CONNECTION_ORDER_SERVICE = CONNECTION_STRING + "/OrderService";

    public static void connect(Protocol protocol, Object data) {
        try {
            switch (protocol) {
                //case GET_USER_DATA_SETS: getUserDataFromDB(); break;
                case GET_FOOD_DATA_SETS: getFoodDataFromDB(); break;
                case GET_ORDER_DATA_SETS: getOrderDataFromDB(); break;
                case GET_RESTRICTION_DATA_SETS: getRestrictionDataFromDB(); break;
                case GET_SECTION_DATA_SETS: getSectionDataFromDB(); break;

                case UPDATE_USER_DATA_SET: updateUserDataAtDB((UserData) data); break;
                case INSERT_USER_DATA_SET: insertUserDataAtDB((UserData) data); break;
            }
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static ArrayList<UserData> getUserDataFromDB() {
        ArrayList<UserData> users = new ArrayList<>();
        try {
            UserService userService = (UserService) Naming.lookup(CONNECTION_USER_SERVICE);
            users =  userService.getAllUsers();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return users;
    }

    public static void getFoodDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        FoodService foodService = (FoodService) Naming.lookup(CONNECTION_FOOD_SERVICE);
        DataContainer.getInstance().setFoodDataSet(
            FXCollections.observableArrayList((foodService.getAllFood()))
        );
    }

    public static void getRestrictionDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        RestrictionService restrictionService = (RestrictionService) Naming.lookup(CONNECTION_RESTRICTION_SERVICE);
        DataContainer.getInstance().setRestrictionDataSet(
            FXCollections.observableArrayList((restrictionService.getAllRestrictions()))
        );
    }

    public static void getSectionDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        SectionService sectionService = (SectionService) Naming.lookup(CONNECTION_SECTION_SERVICE);
        DataContainer.getInstance().setSectionDataSet(
            FXCollections.observableArrayList((sectionService.getAllSection()))
        );
    }

    public static void getOrderDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        OrderService orderService = (OrderService) Naming.lookup(CONNECTION_ORDER_SERVICE);
        DataContainer.getInstance().setOrderDataSets(
            FXCollections.observableArrayList((orderService.getAllOrders()))
        );
    }

    public static void updateUserDataAtDB(UserData user)
        throws RemoteException, MalformedURLException, NotBoundException
    {
        UserService userService = (UserService) Naming.lookup(CONNECTION_USER_SERVICE);
        userService.updateUser(user);
    }

    public static void insertUserDataAtDB(UserData user)
        throws RemoteException, MalformedURLException, NotBoundException
    {
        UserService userService = (UserService) Naming.lookup(CONNECTION_USER_SERVICE);
        userService.insertUser(user);
    }

}
