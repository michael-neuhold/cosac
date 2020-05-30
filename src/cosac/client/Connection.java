package cosac.client;

import cosac.communication.Protocol;
import cosac.model.UserData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

    public static void connect (Protocol request) {
        try (Socket socket = new Socket("localhost", 50000)){

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject("requestType");
            System.out.println("respond: " + ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void handleRequest(Socket socket, Protocol request) throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        oos.writeObject(request);
        switch (request) {
            case GET_FOOD_DATA_SETS: break;
            case GET_USER_DATA_SETS:
                //oos.writeObject(request);
                //System.out.println("server: " + (UserData)ois.readObject());
                break;
            case GET_SECTION_DATA_SETS: break;
            case GET_RESTRICTION_DATA_SETS: break;
            case SET_FOOD_DATA_SETS: break;
            case SET_USER_DATA_SETS: break;
            case SET_SECTION_DATA_SETS: break;
            case SET_RESTRICTION_DATA_SETS: break;
        }
    }

    public static void main(String[] args) {
        Connection.connect(Protocol.GET_USER_DATA_SETS);
    }
}
