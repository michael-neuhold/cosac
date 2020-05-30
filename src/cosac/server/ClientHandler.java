package cosac.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

            String code = (String)ois.readObject();
            if(code.equals("getData1")) {
                System.out.println("-> send data 1");
                oos.writeObject("data 1");
            } else if (code.equals("getData2")){
                System.out.println("-> send data 2");
                oos.writeObject("data 2");
            }

            ois.close();
            oos.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
