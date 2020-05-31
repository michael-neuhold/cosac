package cosac.client;

import cosac.communication.Protocol;
import cosac.model.FoodData;
import cosac.model.RestrictionData;
import cosac.model.SectionData;
import cosac.model.UserData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocket {

    public static void connect (Protocol requestType) {
        try (Socket socket = new Socket("localhost", 50000)){

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Integer requestCode = requestType.getValue();
            oos.writeObject(requestCode);

            handleRequest(ois, oos, requestType);

            oos.close();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void handleRequest(ObjectInputStream ois, ObjectOutputStream oos, Protocol requestType)
            throws IOException, ClassNotFoundException
    {
        switch (requestType) {
            case GET_FOOD_DATA_SETS:
                System.out.println("server: " + (ArrayList<FoodData>)ois.readObject());
                break;
            case GET_USER_DATA_SETS:
                System.out.println("server: " + (ArrayList<UserData>)ois.readObject());
                break;
            case GET_SECTION_DATA_SETS:
                System.out.println("server: " + (ArrayList<SectionData>)ois.readObject());
                break;
            case GET_RESTRICTION_DATA_SETS:
                System.out.println("server: " + (ArrayList<RestrictionData>)ois.readObject());
                break;
            case SET_FOOD_DATA_SETS:
                break;
            case SET_USER_DATA_SETS:
                break;
            case SET_SECTION_DATA_SETS:
                break;
            case SET_RESTRICTION_DATA_SETS:
                break;
        }
    }

    public static void main(String[] args) {
        ClientSocket.connect(Protocol.GET_FOOD_DATA_SETS);
        ClientSocket.connect(Protocol.GET_USER_DATA_SETS);
        ClientSocket.connect(Protocol.GET_SECTION_DATA_SETS);
        ClientSocket.connect(Protocol.GET_RESTRICTION_DATA_SETS);
    }
}
