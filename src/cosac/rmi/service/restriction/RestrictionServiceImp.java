package cosac.rmi.service.restriction;

import cosac.model.RestrictionData;
import cosac.rmi.config.RMIConfig;
import database.dao.restriction.RestrictionDataDao;
import database.dao.restriction.RestrictionDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RestrictionServiceImp implements RestrictionService {

    @Override
    public synchronized ArrayList<RestrictionData> getAllRestrictions() throws RemoteException {
        ArrayList<RestrictionData> results = new ArrayList<>();
        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<RestrictionData>)restrictionDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public synchronized void updateRestriction(RestrictionData restriction) throws RemoteException {
        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            restrictionDataDao.update(restriction);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

    @Override
    public synchronized void insertRestriction(RestrictionData restriction) throws RemoteException {
        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            restrictionDataDao.store(restriction);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

}
