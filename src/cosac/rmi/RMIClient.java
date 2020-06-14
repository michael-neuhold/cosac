package cosac.rmi;

import cosac.client.DataContainer;
import cosac.model.*;
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

    public static void connect(Protocol protocol) {
        try {
            switch (protocol) {
                case GET_USER_DATA_SETS: getUserDataFromDB(); break;
                case GET_FOOD_DATA_SETS: getFoodDataFromDB(); break;
                case GET_ORDER_DATA_SETS: getOrderDataFromDB(); break;
                case GET_RESTRICTION_DATA_SETS: getRestrictionDataFromDB(); break;
                case GET_SECTION_DATA_SETS: getSectionDataFromDB(); break;
            }
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void getUserDataFromDB()
        throws RemoteException, MalformedURLException, NotBoundException
    {
        UserService userService = (UserService) Naming.lookup(CONNECTION_USER_SERVICE);
        DataContainer.getInstance().setUserDataSet(
            FXCollections.observableArrayList((userService.getAllUsers()))
        );
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

    public static void main(String[] args) {
        System.out.println("lookup for " + CONNECTION_USER_SERVICE);
        try {

            // user test
            UserService userService = (UserService) Naming.lookup(CONNECTION_USER_SERVICE);
            ArrayList<UserData> users = userService.getAllUsers();
            for(UserData userData : users) {
                System.out.println("client -> " + userData);
            }
            userService.updateUser(new UserData("S1810307095","JJ","JJ","JJ@JJ","pw", Role.STUDENT,false));
            userService.insertUser(new UserData("test","test", "test", "test", "test", Role.STUDENT, false));

            // food test
            FoodService foodService = (FoodService) Naming.lookup(CONNECTION_FOOD_SERVICE);
            ArrayList<FoodData> food = foodService.getAllFood();
            for(FoodData f : food) {
                System.out.println("client -> " + f);
            }
            foodService.deleteFood(1);
            foodService.insertFood(new FoodData(5, 1, "test"));

            // section test
            SectionService sectionService = (SectionService) Naming.lookup(CONNECTION_SECTION_SERVICE);
            ArrayList<SectionData> sections = sectionService.getAllSection();
            for(SectionData section : sections) {
                System.out.println("client -> " + section);
            }
            sectionService.deleteSection(2);
            sectionService.insertSection(new SectionData(5,"section5"));

            // restriction test
            RestrictionService restrictionServic = (RestrictionService) Naming.lookup(CONNECTION_RESTRICTION_SERVICE);
            ArrayList<RestrictionData> restrictions = restrictionServic.getAllRestrictions();
            for(RestrictionData restriction : restrictions) {
                System.out.println("client -> " + restriction);
            }
            restrictionServic.updateRestriction(new RestrictionData(2, "00:00", "0:00", 20));
            restrictionServic.insertRestriction(new RestrictionData(6, "00:00", "0:00", 20));


        } catch (MalformedURLException | RemoteException | NotBoundException exc) {
            exc.printStackTrace();
        }
    }

}
