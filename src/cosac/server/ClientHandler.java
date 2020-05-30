package cosac.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

            String request = (String)ois.readObject();
            System.out.println(request);
            oos.writeObject("data");

            ois.close();
            oos.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
/*
    private void handleRequest(Protocol request) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("handleRequestServer");
        switch (request) {
            case GET_FOOD_DATA_SETS: break;
            case GET_USER_DATA_SETS:
                System.out.println(request);
                UserData user = new UserData("S1", "Michael", "Neuhold","michi.neuhold@gmail.com", Role.STUDENT, false);
                oos.writeObject(user);
                break;
            case GET_SECTION_DATA_SETS: break;
            case GET_RESTRICTION_DATA_SETS: break;
            case SET_FOOD_DATA_SETS: break;
            case SET_USER_DATA_SETS: break;
            case SET_SECTION_DATA_SETS: break;
            case SET_RESTRICTION_DATA_SETS: break;
            default: System.out.println("...");
        }

        oos.close();
    }
*/
}
