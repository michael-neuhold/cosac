package cosac.client;

import cosac.communication.Protocol;
import cosac.model.*;
import javafx.collections.ObservableList;
import util.Logger;


public class DataContainer {

    private static DataContainer instance;

    private static ObservableList<RestrictionData> restrictionDataSets;
    private static ObservableList<UserData> userDataSets;
    private static ObservableList<FoodData> foodDataSets;
    private static ObservableList<SectionData> sectionDataSet;
    private static ObservableList<OrderData> orderDataSets;

    public DataContainer() {
        Logger.clientStatus("initialize data container");
    }

    public static DataContainer getInstance() {
        if(DataContainer.instance == null) {
            DataContainer.instance = new DataContainer();
        }
        return DataContainer.instance;
    }

    public void initialize() {
        Logger.clientStatus("| get all data sets from server");
        Thread thread = new Thread( () -> {
            ClientSocket.connect(Protocol.GET_USER_DATA_SETS);
            ClientSocket.connect(Protocol.GET_FOOD_DATA_SETS);
            ClientSocket.connect(Protocol.GET_RESTRICTION_DATA_SETS);
            ClientSocket.connect(Protocol.GET_SECTION_DATA_SETS);
            ClientSocket.connect(Protocol.GET_ORDER_DATA_SETS);
        });
        thread.start();
    }

    public void setUserDataSet(ObservableList<UserData> userDataSets) {
        DataContainer.userDataSets = userDataSets;
    }

    public void setRestrictionDataSet(ObservableList<RestrictionData> restrictionDataSets) {
        DataContainer.restrictionDataSets = restrictionDataSets;
    }

    public void setSectionDataSet(ObservableList<SectionData> sectionDataSet) {
        DataContainer.sectionDataSet = sectionDataSet;
    }

    public void setFoodDataSet(ObservableList<FoodData> foodDataSets) {
        DataContainer.foodDataSets = foodDataSets;
    }

    public ObservableList<RestrictionData> getRestrictionDataSets() {
        return restrictionDataSets;
    }

    public ObservableList<UserData> getUserDataSets() {
        return userDataSets;
    }

    public ObservableList<FoodData> getFoodDataSets() {
        return foodDataSets;
    }

    public ObservableList<SectionData> getSectionDataSets() {
        return sectionDataSet;
    }

    public ObservableList<OrderData> getOrderDataSets() {
        return orderDataSets;
    }

    public void setOrderDataSets(ObservableList<OrderData> orderDataSets) {
        DataContainer.orderDataSets = orderDataSets;
    }

    /* ==  RESTRICTIONS  == */

    public void addRestriction(RestrictionData newRestriction) {
        if(!restrictionDataSets.contains(newRestriction)) {
            restrictionDataSets.add(newRestriction);
        } else {
            Logger.error("Restriction exists already");
        }
    }

    /* ==  USER  == */

    public void addUser(UserData newUser) {
        if(!userDataSets.contains(newUser)) {
            userDataSets.add(newUser);
        } else {
            Logger.error("User ID exists already");
        }
    }

    public UserData getUserSetById(String id) {
        for(UserData user : userDataSets) {
            if(user.getStudentID().equals(id)) return user;
        }
        return null;
    }

    /* ==  ORDER  == */

    /* ==  FOOD  == */

    public void addFood(FoodData newFood) {
        if(!foodDataSets.contains(newFood)) {
            foodDataSets.add(newFood);
        } else {
            Logger.error("Food ID exists already");
        }
    }

    public void removeFood(String id) {
        FoodData toDelete = getFoodById(Integer.parseInt(id));
        if(toDelete != null) {
            foodDataSets.remove(toDelete);
        } else {
            Logger.error("Food ID does not exist");
        }
    }

    private FoodData getFoodById(int id) {
        for(FoodData food : foodDataSets)
            if(food.getId() == id) return food;
        return null;
    }

    /* == SECTION == */

    public void addSection(SectionData newSection) {
        if(!sectionDataSet.contains(newSection)) {
            sectionDataSet.add(newSection);
        } else {
            Logger.error("Section ID exists already!");
        }
    }

    public void removeSection(String id) {
        SectionData toDelete = getSectionBy(Integer.parseInt(id));
        if(toDelete != null) {
            sectionDataSet.remove(toDelete);
        } else {
            Logger.error("Section ID does not exist");
        }
    }

    private SectionData getSectionBy(int id) {
        for(SectionData section : sectionDataSet)
            if(section.getId() == id) return section;
        return null;
    }

}
