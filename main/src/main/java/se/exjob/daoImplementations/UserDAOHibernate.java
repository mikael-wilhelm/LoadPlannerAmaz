package se.exjob.daoImplementations;

import se.exjob.databaseAccess.UserDAO;
import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

public class UserDAOHibernate implements UserDAO {
    private EntityManager entityManager;
    @Override
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException, ServerException {
        User result;
        result = entityManager.find(UserImpl.class, userName);
        if(result == null)
            throw new NoSuchUserNameException();
        if(!result.getPassword().equals(password))
            throw new PasswordException();
        return result;
    }

    @Override
    public void registerUser(String userName, String password) throws ServerException {
        UserImpl user = new UserImpl(userName,password);
        EntityTransaction entityTransaction = beginTransaction();
        entityManager.persist(user);
        commitTransaction(entityTransaction);
    }

    @Override
    public void registerUser(User user) throws ServerException {
        registerUser(user.getUserName(),user.getPassword());
    }

    private void commitTransaction(EntityTransaction entityTransaction) {
        try {
            entityTransaction.commit();
        } catch (RollbackException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        }
    }

    private EntityTransaction beginTransaction() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        return entityTransaction;
    }

    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
}
