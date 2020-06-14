package cosac.rmi.service.food;

import cosac.model.FoodData;
import cosac.rmi.config.RMIConfig;
import database.dao.food.FoodDataDao;
import database.dao.food.FoodDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class FoodServiceImp implements FoodService {

    @Override
    public ArrayList<FoodData> getAllFood() throws RemoteException {
        ArrayList<FoodData> results = new ArrayList<>();
        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<FoodData>)foodDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public void deleteFood(int foodID) throws RemoteException {
        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            foodDataDao.delete(foodID);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

    @Override
    public void insertFood(FoodData food) throws RemoteException {
        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            foodDataDao.store(food);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

}
