package cosac.rmi;

import cosac.model.*;
import cosac.rmi.config.RMIConfig;
import cosac.rmi.service.food.FoodService;
import cosac.rmi.service.order.OrderService;
import cosac.rmi.service.restriction.RestrictionService;
import cosac.rmi.service.section.SectionService;
import cosac.rmi.service.user.UserService;
import util.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMIClient {

    // GET REQUESTS

    public static ArrayList<UserData> getUserData() {
        ArrayList<UserData> userData = new ArrayList<>();
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userData = userService.getAllUsers();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }

        Logger.clientRMI("getUserData");
        Logger.dataTransfer(userData);
        return userData;
    }

    public static ArrayList<FoodData> getFoodData() {
        ArrayList<FoodData> foodData = new ArrayList<>();
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodData = foodService.getAllFood();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }

        Logger.clientRMI("getFoodData");
        Logger.dataTransfer(foodData);
        return foodData;
    }

    public static ArrayList<RestrictionData> getRestrictionData() {
        ArrayList<RestrictionData> restrictionData = new ArrayList<>();
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionData = restrictionService.getAllRestrictions();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }

        Logger.clientRMI("getRestrictionData");
        Logger.dataTransfer(restrictionData);
        return restrictionData;
    }

    public static ArrayList<SectionData> getSectionData() {
        ArrayList<SectionData> sectionData = new ArrayList<>();
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionData = sectionService.getAllSection();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }

        Logger.clientRMI("getSectionData");
        Logger.dataTransfer(sectionData);
        return sectionData;
    }

    public static ArrayList<OrderData> getOrderData() {
        ArrayList<OrderData> orderData = new ArrayList<>();
        try {
        OrderService orderService = (OrderService) Naming.lookup(RMIConfig.CONNECTION_ORDER_SERVICE);
        orderData = orderService.getAllOrders();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }

        Logger.clientRMI("getOrderData");
        Logger.dataTransfer(orderData);
        return orderData;
    }

    // POST REQUESTS

    public static void updateUser(UserData user) {
        Logger.clientRMI("updateUser");
        Logger.dataTransfer(user);
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userService.updateUser(user);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void updateRestriction(RestrictionData restriction) {
        Logger.clientRMI("updateRestriction");
        Logger.dataTransfer(restriction);
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionService.updateRestriction(restriction);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }


    public static void insertUser(UserData user) {
        Logger.clientRMI("insertUser");
        Logger.dataTransfer(user);
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userService.insertUser(user);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertRestriction(RestrictionData restriction) {
        Logger.clientRMI("insertRestriction");
        Logger.dataTransfer(restriction);
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionService.insertRestriction(restriction);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertFood(FoodData food) {
        Logger.clientRMI("insertFood");
        Logger.dataTransfer(food);
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodService.insertFood(food);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertSection(SectionData section) {
        Logger.clientRMI("insertSection");
        Logger.dataTransfer(section);
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionService.insertSection(section);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }


    public static void deleteFood(int foodID) {
        Logger.clientRMI("deleteFood with ID = " + foodID);
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodService.deleteFood(foodID);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void deleteSection(int sectionID) {
        Logger.clientRMI("deleteSection with ID = " + sectionID);
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionService.deleteSection(sectionID);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

}
