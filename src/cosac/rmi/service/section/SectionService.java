package cosac.rmi.service.section;

import cosac.model.SectionData;
import database.dao.section.SectionDataDao;
import database.dao.section.SectionDataDaoJdbc;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SectionService implements SectionServiceable {

    private static final String SERVER = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = null;
    private static final String CONNECTION_STRING = "jdbc:mysql://" + SERVER + "/CosacDB?autoReconnect=true&useSSL=false";

    @Override
    public ArrayList<SectionData> getAllSection() throws RemoteException {
        ArrayList<SectionData> results = new ArrayList<>();
        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(CONNECTION_STRING, USERNAME, PASSWORD)) {
            results = (ArrayList<SectionData>)sectionDataDao.getAll();
        } catch(Exception exc) { exc.printStackTrace(); }
        return results;
    }

    @Override
    public void deleteSection(int sectionID) throws RemoteException {

    }

    @Override
    public void insertSection(SectionData section) throws RemoteException {

    }

}
