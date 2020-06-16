package database.test;

import cosac.model.*;
import cosac.rmi.config.RMIConfig;
import database.dao.food.FoodDataDao;
import database.dao.food.FoodDataDaoJdbc;
import database.dao.order.OrderDataDao;
import database.dao.order.OrderDataDaoJdbc;
import database.dao.restriction.RestrictionDataDao;
import database.dao.restriction.RestrictionDataDaoJdbc;
import database.dao.section.SectionDataDao;
import database.dao.section.SectionDataDaoJdbc;
import database.dao.user.UserDataDao;
import database.dao.user.UserDataDaoJdbc;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest {

    // INITIAL

    @Test
    public void TestA_UserEmptyTable() {
        final int FINAL_SIZE = 0;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(UserDataDao userDataDao = new UserDataDaoJdbc(
            RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            RESULT_SIZE = userDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestB_OrderEmptyTable() {
        final int FINAL_SIZE = 0;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            RESULT_SIZE = foodDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestC_RestrictionEmptyTable() {
        final int FINAL_SIZE = 0;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            RESULT_SIZE = restrictionDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestD_SectionEmptyTable() {
        final int FINAL_SIZE = 0;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            RESULT_SIZE = sectionDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestE_FoodEmptyTable() {
        final int FINAL_SIZE = 0;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            RESULT_SIZE = foodDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    // INSERTS

    @Test
    public void TestF_UserInsert() {

        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;

        UserData user1 = new UserData("S0000000000", "Michael", "Neuhold", "S0000000000@students.fh-hagenberg.at", "pw", Role.STUDENT, false);
        UserData user2 = new UserData("S1111111111", "Julian", "Jany", "S1111111111@students.fh-hagenberg.at", "pw", Role.STUDENT, false);

        try(UserDataDao userDataDao = new UserDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            userDataDao.store(user1);
            userDataDao.store(user2);
            RESULT_SIZE = userDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestG_RestrictionInsert() {

        final int PLACEHOLDER = 0; // will be replaced by db
        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;

        RestrictionData restriction1 = new RestrictionData(PLACEHOLDER, "00:00", "01:00", 20);
        RestrictionData restriction2 = new RestrictionData(PLACEHOLDER, "01:00", "02:00", 30);

        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            restrictionDataDao.store(restriction1);
            restrictionDataDao.store(restriction2);
            RESULT_SIZE = restrictionDataDao.getAll().size();

        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestH_SectionInsert() {

        final int PLACEHOLDER = 0; // will be replaced by db
        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;

        SectionData section1 = new SectionData(PLACEHOLDER, "section 1");
        SectionData section2 = new SectionData(PLACEHOLDER, "section 2");

        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            sectionDataDao.store(section1);
            sectionDataDao.store(section2);
            RESULT_SIZE = sectionDataDao.getAll().size();

        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestI_FoodInsert() {

        final int PLACEHOLDER = 0; // will be replaced by db
        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;

        FoodData food1 = new FoodData(PLACEHOLDER, 2, "Nudeln");
        FoodData food2 = new FoodData(PLACEHOLDER, 2, "Pizza");

        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            foodDataDao.store(food1);
            foodDataDao.store(food2);
            RESULT_SIZE = foodDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestJ_OrderInsert() {

        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;

        OrderDataInsert order1 = new OrderDataInsert(1, "S0000000000", 2);
        OrderDataInsert order2 = new OrderDataInsert(2, "S1111111111", 2);

        try(OrderDataDao orderDataDao = new OrderDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            orderDataDao.store(order1);
            orderDataDao.store(order2);
            RESULT_SIZE = orderDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    // UPDATES

    @Test
    public void TestK_UserUpdate() {

        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;
        ArrayList<UserData> users = null;
        UserData changedUser = new UserData("S0000000000", "M", "N", "S0000000000@students.fh-hagenberg.at", "pw", Role.STUDENT, false);

        try(UserDataDao userDataDao = new UserDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            userDataDao.update(changedUser);
            users = (ArrayList<UserData>) userDataDao.getAll();
            RESULT_SIZE = users.size();
        } catch(Exception exc) { exc.printStackTrace(); }

        UserData updatedUserFromDB = users.stream()
            .filter( user -> user.getStudentID().equals(changedUser.getStudentID()))
            .findFirst()
            .orElse(null);

        assertEquals(updatedUserFromDB.getFirstname(), changedUser.getFirstname());
        assertEquals(updatedUserFromDB.getLastname(), changedUser.getLastname());
        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestL_RestrictionUpdate() {

        final int FINAL_SIZE = 2;
        int RESULT_SIZE = Integer.MAX_VALUE;
        ArrayList<RestrictionData> restrictions = null;

        RestrictionData changedRestriction = new RestrictionData(1, "10:00", "11:00", 20);

        try(RestrictionDataDao restrictionDataDao = new RestrictionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            restrictionDataDao.update(changedRestriction);
            restrictions = (ArrayList<RestrictionData>) restrictionDataDao.getAll();
            RESULT_SIZE = restrictions.size();

        } catch(Exception exc) { exc.printStackTrace(); }

        RestrictionData updatedRestrictionFromDB = restrictions.stream()
            .filter( restriction -> restriction.getRestrictionId() == changedRestriction.getRestrictionId())
            .findFirst()
            .orElse(null);

        assertEquals(updatedRestrictionFromDB.getStartTime(), changedRestriction.getStartTime());
        assertEquals(updatedRestrictionFromDB.getEndTime(), changedRestriction.getEndTime());
        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    // DELETES

    @Test
    public void TestM_FoodDelete() {

        final int FINAL_SIZE = 1;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            foodDataDao.delete(1);
            RESULT_SIZE = foodDataDao.getAll().size();
        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    @Test
    public void TestN_SectionDelete() {

        final int FINAL_SIZE = 1;
        int RESULT_SIZE = Integer.MAX_VALUE;

        try(SectionDataDao sectionDataDao = new SectionDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            sectionDataDao.delete(1);
            RESULT_SIZE = sectionDataDao.getAll().size();

        } catch(Exception exc) { exc.printStackTrace(); }

        assertEquals(RESULT_SIZE, FINAL_SIZE);
    }

    // EXCEPTIONS

    @Test
    public void TestO_InsertExistingUser() {

        boolean EXCEPTION_THROWN = false;

        UserData user1 = new UserData("S0000000000", "Michael", "Neuhold", "S0000000000@students.fh-hagenberg.at", "pw", Role.STUDENT, false);

        try(UserDataDao userDataDao = new UserDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            userDataDao.store(user1);
        } catch(Exception exc) {
            EXCEPTION_THROWN = true;
        }

        assertTrue(EXCEPTION_THROWN);
    }

    @Test
    public void TestP_InsertFoodWithNonexistingSection() {

        boolean EXCEPTION_THROWN = false;
        final int PLACEHOLDER = 0;
        final int NON_EXISTING_SECTION_ID = 10;

        FoodData food1 = new FoodData(PLACEHOLDER, NON_EXISTING_SECTION_ID, "Nudeln");

        try(FoodDataDao foodDataDao = new FoodDataDaoJdbc(
                RMIConfig.CONNECTION_STRING, RMIConfig.USERNAME, RMIConfig.PASSWORD))
        {
            foodDataDao.store(food1);
        } catch(Exception exc) {
            EXCEPTION_THROWN = true;
        }

        assertTrue(EXCEPTION_THROWN);
    }

}
