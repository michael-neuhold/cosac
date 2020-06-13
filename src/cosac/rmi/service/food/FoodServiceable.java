package cosac.rmi.service.food;

import cosac.model.FoodData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FoodServiceable extends Remote {

    ArrayList<FoodData> getAllFood() throws RemoteException;
    void deleteFood(int foodID) throws RemoteException;
    void insertFood(FoodData food) throws RemoteException;

}
