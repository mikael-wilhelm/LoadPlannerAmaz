package se.exjob.controller;

import se.exjob.daoImplementations.LoadDAOHashTable;
import se.exjob.daoImplementations.UserDAOHashTable;
import se.exjob.database.Loads;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.database.Users;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControllerTest {

    private Controller controller;
    @Before
    public void initTests(){
        controller = new Controller();
        controller.setDAO(new LoadDAOHashTable());
        controller.setUserDAO(new UserDAOHashTable());
    }

    @Test
    public void insertLoadsTest() throws Exception {
        //SUT
        controller.insertLoad("TestContent1", "TestHarbor1","TestDest1");
        controller.insertLoad("TestContent2", "TestHarbor2","TestDest2");

        List<Load> allLoads = controller.getAllLoads();

        int result = allLoads.size();
        int expected = 2;

        assertThat(result, is(expected));

        Loads.getInstance().clearAllEntries();
    }

    @Test
    public void reserveLoadByIDTest()throws Exception{

        Load expectedNotReservedLoad = controller.insertLoad("LoadNotResContent", "LoadNotResHarbor","LoadNotResDest");
        Load expectedReservedLoad = controller.insertLoad("LoadResContent", "LoadResHarbor","loadResDest");
        Load expectedReservedLoadByOtherUser = controller.insertLoad("LoadResContent", "LoadResHarbor","loadResDest");

        int loadToReserveId = expectedReservedLoad.getId();
        int loadToReserveId2 = expectedReservedLoadByOtherUser.getId();

        User user = new User("foo","bar");
        User user2 = new User("foo2","bar2");
        // SUT
        controller.reserveLoad(loadToReserveId,user);
        controller.reserveLoad(loadToReserveId2,user2);

        assertThatOneLoadIsReserved(user, expectedNotReservedLoad, expectedReservedLoad);
    }

    private void assertThatOneLoadIsReserved(User user, Load expectedNotReservedLoad, Load expectedReservedLoad) throws LoadNotFoundException, ServerException {
        List<Load> reservedLoads = controller.getReservedLoads(user);
        List<Load> notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor("");

        int reservedLoadsSize = reservedLoads.size();
        int notReservedLoadsSize = notReservedLoads.size();
        int expectedReservedSize = 1;
        int expectedNotReservedSize = 1;

        int firstIndex = 0;
        Load reservedLoadResult = reservedLoads.get(firstIndex);
        Load notReservedLoadResult = notReservedLoads.get(firstIndex);

        assertThat(reservedLoadsSize, is(expectedReservedSize));
        assertThat(notReservedLoadsSize, is(expectedNotReservedSize));
        assertThat(notReservedLoadResult,is(expectedNotReservedLoad));
        assertThat(reservedLoadResult,is(expectedReservedLoad));
    }

    @Test
    public void filterResultsTest() throws Exception {
        controller.insertLoad("TestContent", "a","TestDest");
        controller.insertLoad("TestContent", "a","TestDest");
        controller.insertLoad("TestContent", "b","TestDest");

        List<Load> filteredLoadList = controller.getNotReservedLoadsFilteredByHarbor("a");

        int resultFilteredLoadsSize = filteredLoadList.size();
        int expectedSize = 2;

        assertThat(resultFilteredLoadsSize, is(expectedSize));
    }

    @Test
    public void authenticate() throws NoSuchUserNameException, PasswordException, ServerException {
        Users.getInstance().registerUser("foo", "bar");
        User user = controller.authenticate("foo", "bar");

        User expectedUser = new User("foo", "bar");

        assertThat(user, is(expectedUser));
    }

    @Test (expected = PasswordException.class)
    public void wrongAuthenticate() throws NoSuchUserNameException, PasswordException, ServerException {
        Users.getInstance().registerUser("foo", "bar");
        User user = controller.authenticate("foo", "barr");
    }

    @Test
    public void registerUserTest() throws NoSuchUserNameException, PasswordException, ServerException {
        controller.registerUser("foo", "bar");

        User user = controller.authenticate("foo","bar");

        User expectedUser = new User("foo", "bar");

        assertThat(user, is(expectedUser));
    }

    @After
    public void tearDown() {
        Loads.getInstance().clearAllEntries();
        Users.getInstance().clearAllUsers();
    }
}
