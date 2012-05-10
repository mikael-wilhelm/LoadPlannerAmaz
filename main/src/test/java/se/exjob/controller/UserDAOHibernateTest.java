package se.exjob.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import se.exjob.daoImplementations.UserDAOHibernate;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class UserDAOHibernateTest {
    UserDAO userDAO;
    @Before
    public void setUp(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LoadDaoHibernateTest");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userDAO = new UserDAOHibernate();
        ((UserDAOHibernate) userDAO).setEntityManager(entityManager);


    }

    @Test
    public void insertUserTest() throws ServerException, NoSuchUserNameException, PasswordException {
        User expectedUser = new UserImpl("tetUsername", "testPassword");

        userDAO.registerUser(expectedUser);

        //SUT
        User userResult = userDAO.authenticate(expectedUser.getUserName(),expectedUser.getPassword());

        assertThat(userResult, is(expectedUser));
    }
}
