package cosac.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


import cosac.communication.Protocol;
import cosac.model.*;
import logger.Logger;

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

    private synchronized void handleRequest(ObjectInputStream ois, ObjectOutputStream oos, Protocol request)
            throws IOException, ClassNotFoundException
    {
        switch (request) {
            case GET_FOOD_DATA_SETS: handleGetFoodDataSets(oos); break;
            case GET_USER_DATA_SETS: handleGetUserDataSets(oos); break;
            case GET_SECTION_DATA_SETS: handleGetSectionDataSets(oos); break;
            case GET_RESTRICTION_DATA_SETS: handleGetRestrictionDataSets(oos); break;
            case GET_ORDER_DATA_SETS: handleGetOrderDataSets(oos); break;
            case SET_FOOD_DATA_SETS: handleSetFoodDataSets(ois); break;
            case SET_USER_DATA_SETS: handleSetUserDataSets(ois); break;
            case SET_SECTION_DATA_SETS: handleSetSectionDataSets(ois); break;
            case SET_RESTRICTION_DATA_SETS: handleSetRestrictionDataSets(ois); break;
            case SET_ORDER_DATA_SETS: handleSetOrderDataSets(ois); break;
            default: Logger.error("protocol error");
        }
    }

    private void handleGetFoodDataSets(ObjectOutputStream oos) throws IOException {
        Logger.serverSocket("server sends FoodDataSets ");
        FileReader<FoodData> fileReader = new FileReader<>();
        oos.writeObject(fileReader.readFrom("foodDataSet.ser"));
    }

    private void handleGetUserDataSets(ObjectOutputStream oos) throws IOException {
        Logger.serverSocket("server sends UserDataSets ");
        FileReader<UserData> fileReader = new FileReader<>();
        oos.writeObject(fileReader.readFrom("userDataSet.ser"));
    }

    private void handleGetSectionDataSets(ObjectOutputStream oos) throws IOException {
        Logger.serverSocket("server sends SectionDataSets ");
        FileReader<SectionData> fileReader = new FileReader<>();
        oos.writeObject(fileReader.readFrom("sectionDataSet.ser"));
    }

    private void handleGetRestrictionDataSets(ObjectOutputStream oos) throws IOException {
        Logger.serverSocket("server sends RestrictionDataSets ");
        FileReader<RestrictionData> fileReader = new FileReader<>();
        oos.writeObject(fileReader.readFrom("restrictionDataSet.ser"));
    }

    private void handleGetOrderDataSets(ObjectOutputStream oos) throws IOException {
        Logger.serverSocket("server sends OrderDataSets ");
        FileReader<OrderData> fileReader = new FileReader<>();
        oos.writeObject(fileReader.readFrom("orderDataSet.ser"));
    }

    private void handleSetFoodDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Logger.serverSocket("server receives FoodDataSets ");
        FileWriter<FoodData> fileWriter = new FileWriter<>();
        fileWriter.writeIntoFile(
            (ArrayList<FoodData>)ois.readObject(),
            "foodDataSet.ser"
        );
    }

    private void handleSetUserDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Logger.serverSocket("server receives UserDataSets ");
        FileWriter<UserData> fileWriter = new FileWriter<>();
        fileWriter.writeIntoFile(
            (ArrayList<UserData>)ois.readObject(),
            "userDataSet.ser"
        );
    }

    private void handleSetSectionDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Logger.serverSocket("server receives SectionDataSets ");
        FileWriter<SectionData> fileWriter = new FileWriter<>();
        fileWriter.writeIntoFile(
            (ArrayList<SectionData>)ois.readObject(),
            "sectionDataSet.ser"
        );
    }

    private void handleSetRestrictionDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Logger.serverSocket("server receives RestrictionDataSets ");
        FileWriter<RestrictionData> fileWriter = new FileWriter<>();
        fileWriter.writeIntoFile(
            (ArrayList<RestrictionData>)ois.readObject(),
            "restrictionDataSet.ser"
        );
    }

    private void handleSetOrderDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Logger.serverSocket("server receives OrderDataSets ");
        FileWriter<OrderData> fileWriter = new FileWriter<>();
        fileWriter.writeIntoFile(
            (ArrayList<OrderData>)ois.readObject(),
            "orderDataSet.ser"
        );
    }

}
