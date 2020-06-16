package cosac.rmi.service.section;

import cosac.model.SectionData;
import cosac.rmi.config.RMIConfig;
import database.dao.section.SectionDataDao;
import database.dao.section.SectionDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SectionServiceImp implements SectionService {

    @Override
    public synchronized ArrayList<SectionData> getAllSection() throws RemoteException {
        ArrayList<SectionData> results = new ArrayList<>();
        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            results = (ArrayList<SectionData>)sectionDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public synchronized void deleteSection(int sectionID) throws RemoteException {
        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            sectionDataDao.delete(sectionID);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

    @Override
    public synchronized void insertSection(SectionData section) throws RemoteException {
        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            sectionDataDao.store(section);
        } catch(Exception exc) { exc.printStackTrace(); }
    }

}
