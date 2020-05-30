package cosac.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataContainer {

    private static ObservableList<RestrictionData> restrictionDataSets = FXCollections.observableArrayList (
        new RestrictionData("08:00", "08:30", 20),
        new RestrictionData("08:30", "09:00", 30),
        new RestrictionData("09:00", "09:30", 10),
        new RestrictionData("10:00", "10:30", 10)
    );

    private static ObservableList<UserData> userDataSets = FXCollections.observableArrayList(
        new UserData("s1","Michael", "Neuhold", "michi.neuhold@gmail.com", Role.ADMIN, false),
        new UserData("s2","Julian", "Jany", "julian.jany@gmail.com", Role.STUDENT,false),
        new UserData("s3","Maxi", "Ranger", "maxi.ranger@gmail.com", Role.STUDENT,false),
        new UserData("s4","Claudia", "Wimmeder", "claudia.wimmeder@gmail.com", Role.STUDENT,false),
        new UserData("s5","Pia", "Schaenzle", "pia.schaenzle@gmail.com", Role.STUDENT,false)
    );

    private static ObservableList orderDataSets = FXCollections.observableArrayList (
        "Wiener Schnitzel vom Milchkalb",
        "Wiener Tafelspitz",
        "Duett vom Labonca Sonnenschwein",
        "Fisch des Tages"
    );

    private static ObservableList<FoodData> foodDataSets = FXCollections.observableArrayList(
        new FoodData(1,1,"Nudelsuppe"),
        new FoodData(2,2,"Schnitzl"),
        new FoodData(3,2,"Berner Würstel"),
        new FoodData(4,4,"Eis-Kaffee")
    );

    private static ObservableList<SectionData> sectionDataSet = FXCollections.observableArrayList(
        new SectionData(1,"Vorspeisen"),
        new SectionData(2,"Fleischgerichte"),
        new SectionData(3,"Nudelgerichte"),
        new SectionData(4,"Nachspeisen")
    );

    public static ObservableList<RestrictionData> getRestrictionDataSets() {
        return restrictionDataSets;
    }

    public static ObservableList<UserData> getUserDataSets() {
        return userDataSets;
    }

    public static ObservableList getOrderDataSets() {
        return orderDataSets;
    }

    public static ObservableList<FoodData> getFoodDataSets() {
        return foodDataSets;
    }

    public static ObservableList<SectionData> getSectionDataSet() {
        return sectionDataSet;
    }

    /* ==  RESTRICTIONS  == */

    public static void addRestriction(String startTime, String endTime, int limit) {
        if(!startTime.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) return;
        if(!endTime.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) return;
        RestrictionData newRestriction = new RestrictionData(startTime,endTime,limit);
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
