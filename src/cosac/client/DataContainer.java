package cosac.client;

import cosac.communication.Protocol;
import cosac.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.security.Signature;
import java.util.ArrayList;

public class DataContainer {

    private static DataContainer instance;

    private static ObservableList<RestrictionData> restrictionDataSets;
    private static ObservableList<UserData> userDataSets;
    private static ObservableList<FoodData> foodDataSets;
    private static ObservableList<SectionData> sectionDataSet;
    private static ObservableList<OrderData> orderDataSets;

    public DataContainer() {
        System.out.println("init DataContainer");
    }

    public static DataContainer getInstance() {
        if(DataContainer.instance == null) {
            DataContainer.instance = new DataContainer();
        }
        return DataContainer.instance;
    }

    public void initialize() {
        ClientSocket.connect(Protocol.GET_USER_DATA_SETS);
        ClientSocket.connect(Protocol.GET_FOOD_DATA_SETS);
        ClientSocket.connect(Protocol.GET_RESTRICTION_DATA_SETS);
        ClientSocket.connect(Protocol.GET_SECTION_DATA_SETS);
        ClientSocket.connect(Protocol.GET_ORDER_DATA_SETS);
    }

    public static void setUserDataSet(ObservableList<UserData> userDataSets) {
        DataContainer.userDataSets = userDataSets;
    }

    public static void setRestrictionDataSet(ObservableList<RestrictionData> restrictionDataSets) {
        DataContainer.restrictionDataSets = restrictionDataSets;
    }

    public static void setSectionDataSet(ObservableList<SectionData> sectionDataSet) {
        DataContainer.sectionDataSet = sectionDataSet;
    }

    public static void setFoodDataSet(ObservableList<FoodData> foodDataSets) {
        DataContainer.foodDataSets = foodDataSets;
    }

    public static ObservableList<RestrictionData> getRestrictionDataSets() {
        return restrictionDataSets;
    }

    public static ObservableList<UserData> getUserDataSets() {
        return userDataSets;
    }

    public static ObservableList<FoodData> getFoodDataSets() {
        return foodDataSets;
    }

    public static ObservableList<SectionData> getSectionDataSet() {
        return sectionDataSet;
    }

    public static ObservableList<OrderData> getOrderDataSets() {
        return orderDataSets;
    }

    public static void setOrderDataSets(ObservableList<OrderData> orderDataSets) {
        DataContainer.orderDataSets = orderDataSets;
    }

    /* ==  RESTRICTIONS  == */

    public static void addRestriction(String startTime, String endTime, int limit) {
        if(!startTime.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) return;
        if(!endTime.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) return;
        RestrictionData newRestriction = new RestrictionData(1,startTime,endTime,limit);
        if(!restrictionDataSets.contains(newRestriction)) {
            restrictionDataSets.add(newRestriction);
        } else {
            System.out.println("Restriction exists already");
        }
    }

    /* ==  USER  == */

    static public void addUser(String id, String firstname, String lastname, String email, Role role) {
        UserData newUser = new UserData(id, firstname, lastname, email, Role.STUDENT,false);
        if(!userDataSets.contains(newUser)) {
            userDataSets.add(newUser);
        } else {
            System.out.println("User ID exists already");
        }
    }

    static public UserData getUserSetById(String id) {
        for(UserData user : userDataSets) {
            if(user.getStudentID().equals(id)) return user;
        }
        return null;
    }

    /* ==  ORDER  == */

    /* ==  FOOD  == */

    static public void addFood(String id, String sectionId, String name) {
        FoodData newFood = new FoodData(Integer.parseInt(id),Integer.parseInt(sectionId),name);
        if(!foodDataSets.contains(newFood)) {
            foodDataSets.add(newFood);
        } else {
            System.out.println("Food ID exists already!");
        }
    }

    static public void removeFood(String id) {
        FoodData toDelete = getFoodById(Integer.parseInt(id));
        if(toDelete != null) {
            foodDataSets.remove(toDelete);
        } else {
            System.out.println("Food ID does not exist!");
        }
    }

    static private FoodData getFoodById(int id) {
        for(FoodData food : foodDataSets)
            if(food.getId() == id) return food;
        return null;
    }

    /* == SECTION == */

    static public void addSection(String sectionId, String name) {
        SectionData newSection = new SectionData(Integer.parseInt(sectionId), name);
        if(!sectionDataSet.contains(newSection)) {
            sectionDataSet.add(newSection);
        } else {
            System.out.println("Section ID exists already!");
        }
    }

    static public void removeSection(String id) {
        SectionData toDelete = getSectionBy(Integer.parseInt(id));
        if(toDelete != null) {
            sectionDataSet.remove(toDelete);
        } else {
            System.out.println("Section ID does not exist!");
        }
    }

    static private SectionData getSectionBy(int id) {
        for(SectionData section : sectionDataSet)
            if(section.getId() == id) return section;
        return null;
    }

}
