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

    // GET REQUESTS

    public static ArrayList<UserData> getUserDataFromDB() {
        ArrayList<UserData> userData = new ArrayList<>();
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userData = userService.getAllUsers();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return userData;
    }

    public static ArrayList<FoodData> getFoodDataFromDB() {
        ArrayList<FoodData> foodData = new ArrayList<>();
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodData = foodService.getAllFood();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return foodData;
    }

    public static ArrayList<RestrictionData> getRestrictionDataFromDB() {
        ArrayList<RestrictionData> restrictionData = new ArrayList<>();
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionData = restrictionService.getAllRestrictions();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return restrictionData;
    }

    public static ArrayList<SectionData> getSectionDataFromDB() {
        ArrayList<SectionData> sectionData = new ArrayList<>();
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionData = sectionService.getAllSection();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return sectionData;
    }

    public static ArrayList<OrderData> getOrderDataFromDB() {
        ArrayList<OrderData> orderData = new ArrayList<>();
        try {
        OrderService orderService = (OrderService) Naming.lookup(RMIConfig.CONNECTION_ORDER_SERVICE);
        orderData = orderService.getAllOrders();
        } catch (RemoteException | MalformedURLException | NotBoundException exc) {
            exc.printStackTrace();
        }
        return orderData;
    }

    // POST REQUESTS

    public static void updateUserAtDB(UserData user) {
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userService.updateUser(user);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void updateRestrictionAtDB(RestrictionData restriction) {
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionService.updateRestriction(restriction);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }


    public static void insertUserAtDB(UserData user) {
        try {
            UserService userService = (UserService) Naming.lookup(RMIConfig.CONNECTION_USER_SERVICE);
            userService.insertUser(user);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertRestrictionAtDB(RestrictionData restriction) {
        try {
            RestrictionService restrictionService = (RestrictionService) Naming.lookup(RMIConfig.CONNECTION_RESTRICTION_SERVICE);
            restrictionService.insertRestriction(restriction);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertFoodAtDB(FoodData food) {
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodService.insertFood(food);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void insertSectionAtDB(SectionData section) {
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionService.insertSection(section);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }


    public static void deleteFoodAtDB(int foodID) {
        try {
            FoodService foodService = (FoodService) Naming.lookup(RMIConfig.CONNECTION_FOOD_SERVICE);
            foodService.deleteFood(foodID);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

    public static void deleteSectionAtDB(int sectionID) {
        try {
            SectionService sectionService = (SectionService) Naming.lookup(RMIConfig.CONNECTION_SECTION_SERVICE);
            sectionService.deleteSection(sectionID);
        } catch (RemoteException | NotBoundException | MalformedURLException exc) {
            exc.printStackTrace();
        }
    }

}
