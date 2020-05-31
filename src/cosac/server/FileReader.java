package cosac.server;

import cosac.model.FoodData;
import cosac.model.RestrictionData;
import cosac.model.SectionData;
import cosac.model.UserData;
import logger.Logger;

import java.io.*;
import java.util.ArrayList;


public class FileReader<T> {

    public ArrayList<T> readFrom(String filename) {
        Logger.serverFileIO("\t |<- [reads from] " + filename);
        ArrayList<T> dataSet = null;
        try(ObjectInput is = new ObjectInputStream(new FileInputStream("./src/persist/" + filename))) {
            dataSet = (ArrayList<T>)is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Logger.serverFileIO(dataSet);
        return dataSet;
    }

    public static void main(String[] args) {
        FileReader<UserData> fileReader1 = new FileReader();
        System.out.println("data: " + fileReader1.readFrom("userDataSet.ser"));

        FileReader<FoodData> fileReader2 = new FileReader();
        System.out.println("data: " + fileReader2.readFrom("foodDataSet.ser"));

        FileReader<RestrictionData> fileReader3 = new FileReader();
        System.out.println("data: " + fileReader3.readFrom("restrictionDataSet.ser"));

        FileReader<SectionData> fileReader4 = new FileReader();
        System.out.println("data: " + fileReader4.readFrom("sectionDataSet.ser"));
    }


}
