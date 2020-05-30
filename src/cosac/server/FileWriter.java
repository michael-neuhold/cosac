package cosac.server;

import cosac.model.Role;
import cosac.model.UserData;

import java.io.*;

import static java.lang.System.inheritedChannel;
import static java.lang.System.out;

public class FileWriter {

    public void writeUserData(UserData userDataSet) {
        try(ObjectOutput os = new ObjectOutputStream(new FileOutputStream("userDataSet.ser"))){
            os.writeObject(userDataSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileWriter fileWrite = new FileWriter();
        UserData user = new UserData("S1", "Michael", "Neuhold","michi.neuhold@gmail.com", Role.STUDENT, false);
        fileWrite.writeUserData(user);
    }
}
