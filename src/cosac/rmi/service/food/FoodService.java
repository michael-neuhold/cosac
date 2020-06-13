package cosac.rmi.service.food;

import cosac.model.FoodData;
import database.dao.food.FoodDataDao;
import database.dao.food.FoodDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class FoodService implements FoodServiceable {

    private static final String SERVER = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    @Override
    public ArrayList<FoodData> getAllFood() throws RemoteException {
        ArrayList<FoodData> results = new ArrayList<>();
        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(CONNECTION_STRING, USERNAME, PASSWORD)) {
            results = (ArrayList<FoodData>)foodDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public void deleteFood(int foodID) throws RemoteException {

    }

    @Override
    public void insertFood(FoodData food) throws RemoteException {

    }

}
