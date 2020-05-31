package cosac.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import cosac.communication.Protocol;
import cosac.model.Role;
import cosac.model.UserData;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            int requestCode = (Integer)ois.readObject();
            Protocol requestType = Protocol.valueOf(requestCode);
            handleRequest(ois, oos,requestType);

            ois.close();
            oos.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(ObjectInputStream ois, ObjectOutputStream oos, Protocol request)
            throws IOException, ClassNotFoundException
    {
        switch (request) {
            case GET_FOOD_DATA_SETS: break;
            case GET_USER_DATA_SETS:
                UserData user1 = new UserData("S1", "Michael", "Neuhold","michi.neuhold@gmail.com", Role.STUDENT, false);
                UserData user2 = new UserData("S2", "Julian", "Jany","julian.jany@gmail.com", Role.STUDENT, false);
                ArrayList<UserData> userSets = new ArrayList<>();
                userSets.add(user1);
                userSets.add(user2);
                oos.writeObject(userSets);
                break;
            case GET_SECTION_DATA_SETS: break;
            case GET_RESTRICTION_DATA_SETS: break;
            case SET_FOOD_DATA_SETS: break;
            case SET_USER_DATA_SETS: break;
            case SET_SECTION_DATA_SETS: break;
            case SET_RESTRICTION_DATA_SETS: break;
            default: System.out.println("...");
        }
    }

}
