package cosac.rmi.service.restriction;

import cosac.model.RestrictionData;
import database.dao.restriction.RestrictionDataDao;
import database.dao.restriction.RestrictionDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RestrictionService implements RestrictionServiceable {

    private static final String SERVER = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    @Override
    public ArrayList<RestrictionData> getAllRestrictions() throws RemoteException {
        ArrayList<RestrictionData> results = new ArrayList<>();
        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(CONNECTION_STRING, USERNAME, PASSWORD)) {
            results = (ArrayList<RestrictionData>)restrictionDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public RestrictionData updateRestriction(RestrictionData restriction) throws RemoteException {
        return null;
    }

    @Override
    public void insertRestriction(RestrictionData restriction) throws RemoteException {

    }

}
