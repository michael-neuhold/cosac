package cosac.rmi.service.section;

import cosac.model.SectionData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SectionService extends Remote {

    ArrayList<SectionData> getAllSection() throws RemoteException;
    void deleteSection(int sectionID) throws RemoteException;
    void insertSection(SectionData sectionData) throws RemoteException;

}
