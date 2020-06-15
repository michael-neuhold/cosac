package cosac.rmi.service.order;

import cosac.model.OrderData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrderService extends Remote {

    ArrayList<OrderData> getAllOrders() throws RemoteException;

}
