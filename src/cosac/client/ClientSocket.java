package cosac.client;

import cosac.communication.Protocol;
import cosac.model.*;
import javafx.collections.FXCollections;

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
            case GET_FOOD_DATA_SETS: handleGetFoodDataSets(ois); break;
            case GET_USER_DATA_SETS: handleGetUserDataSets(ois); break;
            case GET_SECTION_DATA_SETS: handleGetSectionDataSets(ois); break;
            case GET_RESTRICTION_DATA_SETS: handleGetRestrictionDataSets(ois); break;
            case GET_ORDER_DATA_SETS: handleGetOrderDataSets(ois); break;
            case SET_FOOD_DATA_SETS: handleSetFoodDataSets(oos); break;
            case SET_USER_DATA_SETS: handleSetUserDataSets(oos); break;
            case SET_SECTION_DATA_SETS: handleSetSectionDataSets(oos); break;
            case SET_RESTRICTION_DATA_SETS: handleSetRestrictionDataSets(oos); break;
            case SET_ORDER_DATA_SETS: handleSetOrderDataSets(oos); break;
        }
    }

    private static void handleGetFoodDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        DataContainer.getInstance().setFoodDataSet(
            FXCollections.observableArrayList((ArrayList<FoodData>)ois.readObject())
        );
    }

    private static void handleGetUserDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        DataContainer.getInstance().setUserDataSet(
            FXCollections.observableArrayList((ArrayList<UserData>)ois.readObject())
        );
    }

    private static void handleGetRestrictionDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        DataContainer.getInstance().setRestrictionDataSet(
            FXCollections.observableArrayList((ArrayList<RestrictionData>)ois.readObject())
        );
    }

    private static void handleGetSectionDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        DataContainer.getInstance().setSectionDataSet(
            FXCollections.observableArrayList((ArrayList<SectionData>)ois.readObject())
        );
    }

    private static void handleGetOrderDataSets(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        DataContainer.getInstance().setOrderDataSets(
            FXCollections.observableArrayList((ArrayList<OrderData>)ois.readObject())
        );
    }

    private static void handleSetFoodDataSets(ObjectOutputStream oos) throws IOException {
        oos.writeObject(new ArrayList<>(DataContainer.getInstance().getFoodDataSets()));
    }

    private static void handleSetUserDataSets(ObjectOutputStream oos) throws IOException {
        oos.writeObject(new ArrayList<>(DataContainer.getInstance().getUserDataSets()));
    }

    private static void handleSetRestrictionDataSets(ObjectOutputStream oos) throws IOException {
        oos.writeObject(new ArrayList<>(DataContainer.getInstance().getRestrictionDataSets()));
    }

    private static void handleSetSectionDataSets(ObjectOutputStream oos) throws IOException {
        oos.writeObject(new ArrayList<>(DataContainer.getInstance().getSectionDataSets()));
    }

    private static void handleSetOrderDataSets(ObjectOutputStream oos) throws IOException {
        oos.writeObject(new ArrayList<>(DataContainer.getInstance().getOrderDataSets()));
    }

}
