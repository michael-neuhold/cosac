package cosac.client;

import cosac.model.UserData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void connect() {
        try (Socket socket = new Socket("localhost", 50000)){

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String code = scanner.nextLine();
            oos.writeObject(code);
            System.out.println("server: " + ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Client client = new Client();
        client.connect();
    }
}
