package cosac.rmi.service.restriction;

import cosac.model.RestrictionData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RestrictionServiceable extends Remote {

    ArrayList<RestrictionData> getAllRestrictions() throws RemoteException;
    void updateRestriction(RestrictionData restriction) throws RemoteException;
    void insertRestriction(RestrictionData restriction) throws RemoteException;

}
