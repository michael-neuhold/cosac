package cosac.server;

import cosac.model.*;
import util.Logger;

import java.io.*;
import java.util.ArrayList;

public class FileWriter<T> {

    public synchronized void writeIntoFile(ArrayList<T> dataSet, String filename) {
        Logger.serverFileIO("\t |-> [writes into] " + filename);
        Logger.serverFileIO(dataSet);
        try(ObjectOutput os = new ObjectOutputStream(new FileOutputStream("./src/persist/" + filename))) {
            os.writeObject(dataSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  ONLY FOR TESTING (run to fill application with test data sets)
     */
    public static void main(String[] args) {

        // user1 pw: admin
        // user2 pw: init123
        UserData user1 = new UserData("P0000", "Michael", "Neuhold","michi.neuhold@gmail.com", "8c6976e5b541415bde98bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Role.STUDENT, false);
        UserData user2 = new UserData("S1810307094", "Julian", "Jany","julian.jany@gmail.com", "e08edb748f1289934cb8a223048aa09a5197b9ebcbe84fbebed833070b0a", Role.STUDENT, false);

        FoodData food1 = new FoodData(1,1,"Pizza");
        FoodData food2 = new FoodData(2,2,"Schnitzel");

        RestrictionData restriction1 = new RestrictionData(1,"12:00","13:00",20);
        RestrictionData restriction2 = new RestrictionData(2,"13:00","14:00",50);

        SectionData section1 = new SectionData(1,"section 1");
        SectionData section2 = new SectionData(2,"section 2");

        OrderData order1 = new OrderData("1","food 1",1);
        OrderData order2 = new OrderData("2","food 2",2);


        // USER DATA SETS
        FileWriter<UserData> fileWriter1 = new FileWriter();
        ArrayList<UserData> userDataSet = new ArrayList<>();
        userDataSet.add(user1);
        userDataSet.add(user2);


        // FOOD DATA SETS
        FileWriter<FoodData> fileWriter2 = new FileWriter<>();
        ArrayList<FoodData> foodDataSet = new ArrayList<>();
        foodDataSet.add(food1);
        foodDataSet.add(food2);


        // RESTRICTION DATA SETS
        FileWriter<RestrictionData> fileWriter3 = new FileWriter<>();
        ArrayList<RestrictionData> restrictionDataSet = new ArrayList<>();
        restrictionDataSet.add(restriction1);
        restrictionDataSet.add(restriction2);

        // SECTION DATA SETS
        FileWriter<SectionData> fileWriter4 = new FileWriter<>();
        ArrayList<SectionData> sectionDataSet = new ArrayList<>();
        sectionDataSet.add(section1);
        sectionDataSet.add(section2);

        // ORDER DATA SETS
        FileWriter<OrderData> fileWriter5 = new FileWriter<>();
        ArrayList<OrderData> orderDataSet = new ArrayList<>();
        orderDataSet.add(order1);
        orderDataSet.add(order2);

        fileWriter1.writeIntoFile(userDataSet, "userDataSet.ser");
        fileWriter2.writeIntoFile(foodDataSet, "foodDataSet.ser");
        fileWriter3.writeIntoFile(restrictionDataSet, "restrictionDataSet.ser");
        fileWriter4.writeIntoFile(sectionDataSet, "sectionDataSet.ser");
        fileWriter5.writeIntoFile(orderDataSet, "orderDataSet.ser");
    }
}
