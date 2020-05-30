package cosac.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

       try(ServerSocket socket = new ServerSocket(50000)) {
            while(true) {
                Socket s = socket.accept();
                Thread newClient = new ClientHandler(s);
                newClient.start();
            }
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

}
