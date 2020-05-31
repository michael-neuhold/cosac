package cosac.server;

import cosac.model.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileWriter<T> {

    public void writeIntoFile(ArrayList<T> dataSet, String filename) {
        try(ObjectOutput os = new ObjectOutputStream(new FileOutputStream("./src/persist/" + filename))) {
            os.writeObject(dataSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // USER DATA SETS
        FileWriter<UserData> fileWriter1 = new FileWriter();
        UserData user2 = new UserData("S2", "Michael", "Neuhold","michi.neuhold@gmail.com", Role.STUDENT, false);
        //UserData user3 = new UserData("S3", "Michael", "Neuhold","michi.neuhold@gmail.com", Role.STUDENT, false);
        ArrayList<UserData> userDataSet = new ArrayList<>();
        userDataSet.add(user2);
        //userDataSet.add(user3);
        fileWriter1.writeIntoFile(userDataSet, "userDataSet.ser");

        // FOOD DATA SETS
        FileWriter<FoodData> fileWriter2 = new FileWriter<>();
        FoodData food1 = new FoodData(1,1,"Pizza");
        FoodData food2 = new FoodData(2,2,"Schnitzel");
        ArrayList<FoodData> foodDataSet = new ArrayList<>();
        foodDataSet.add(food1);
        foodDataSet.add(food2);
        fileWriter2.writeIntoFile(foodDataSet, "foodDataSet.ser");

        // RESTRICTION DATA SETS
        FileWriter<RestrictionData> fileWriter3 = new FileWriter<>();
        RestrictionData restriction1 = new RestrictionData(1,"12:00","13:00",20);
        RestrictionData restriction2 = new RestrictionData(2,"13:00","14:00",50);
        ArrayList<RestrictionData> restrictionDataSet = new ArrayList<>();
        restrictionDataSet.add(restriction1);
        restrictionDataSet.add(restriction2);
        fileWriter3.writeIntoFile(restrictionDataSet, "restrictionDataSet.ser");

        // SECTION DATA SETS
        FileWriter<SectionData> fileWriter4 = new FileWriter<>();
        SectionData section1 = new SectionData(1,"section 1");
        SectionData section2 = new SectionData(2,"section 2");
        ArrayList<SectionData> sectionDataSet = new ArrayList<>();
        sectionDataSet.add(section1);
        sectionDataSet.add(section2);
        fileWriter4.writeIntoFile(sectionDataSet, "sectionDataSet.ser");

        // ORDER DATA SETS
        FileWriter<OrderData> fileWriter5 = new FileWriter<>();
        OrderData order1 = new OrderData("1","food 1",1);
        OrderData order2 = new OrderData("2","food 2",2);
        ArrayList<OrderData> orderDataSet = new ArrayList<>();
        orderDataSet.add(order1);
        orderDataSet.add(order2);
        fileWriter5.writeIntoFile(orderDataSet, "orderDataSet.ser");

    }
}
