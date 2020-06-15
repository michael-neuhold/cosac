package cosac.rmi.service.order;

import cosac.model.OrderData;
import cosac.rmi.config.RMIConfig;
import database.dao.order.OrderDataDao;
import database.dao.order.OrderDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class OrderServiceImp implements OrderService {

    @Override
    public ArrayList<OrderData> getAllOrders() throws RemoteException {
        ArrayList<OrderData> results = new ArrayList<>();
        try(OrderDataDao orderDataDao = new OrderDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<OrderData>)orderDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public ArrayList<OrderData> getOrdersByRestrictionId(int restrictionId) throws RemoteException {
        ArrayList<OrderData> results = new ArrayList<>();
        try(OrderDataDao orderDataDao = new OrderDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<OrderData>)orderDataDao.getByRestrictionId(restrictionId);
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

}
