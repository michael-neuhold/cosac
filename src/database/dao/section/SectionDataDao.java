package database.dao.section;

import cosac.model.SectionData;
import database.DataAccessException;

import java.util.Collection;

public interface SectionDataDao extends AutoCloseable {

    int getCount() throws DataAccessException;
    Collection<SectionData> getAll() throws DataAccessException;
    void store(SectionData section) throws DataAccessException;
    void delete(int sectionID) throws DataAccessException;

}
