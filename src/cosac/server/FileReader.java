package cosac.server;

import cosac.model.UserData;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class FileReader {

    public UserData getUserData() {
        UserData userDataSet = null;
        try(ObjectInput is = new ObjectInputStream(new FileInputStream("userDataSet.ser"))) {
            userDataSet = (UserData)is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDataSet;
    }


    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        UserData userData = fileReader.getUserData();
        System.out.println("data: " + userData);
    }


}
