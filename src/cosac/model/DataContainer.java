package cosac.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataContainer {

    static public ObservableList<RestrictionData> restrictionDataSets = FXCollections.observableArrayList (
            new RestrictionData("08:00", "08:30", 20),
            new RestrictionData("08:30", "09:00", 30),
            new RestrictionData("09:00", "09:30", 10)
    );

    static public ObservableList<UserData> userDataSets = FXCollections.observableArrayList(
            new UserData("s1","Michael", "Neuhold", "michi.neuhold@gmail.com", false),
            new UserData("s2","Julian", "Jany", "julian.jany@gmail.com", false),
            new UserData("s3","Maxi", "Ranger", "maxi.ranger@gmail.com", false),
            new UserData("s4","Claudia", "Wimmeder", "claudia.wimmeder@gmail.com", false),
            new UserData("s5","Pia", "Schaenzle", "pia.schaenzle@gmail.com", false)
    );


    /* ==== */

    static public UserData getUserSetById(String id) {
        for(UserData user : userDataSets) {
            if(user.getStudentID().equals(id)) return user;
        }
        return null;
    }

}
