package se.exjob.daoImplementations;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.database.Users;
import se.exjob.exceptions.PasswordException;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

public class UserDAOHashTable implements UserDAO{

    @Override
    public UserImpl authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException {
        Users users = Users.getInstance();
        return users.authenticate(userName,password);
    }

    @Override
    public void registerUser(String userName, String password) {
        Users.getInstance().registerUser(userName,password);
    }

    @Override
    public void registerUser(User user) throws ServerException {
        registerUser(user.getUserName(),user.getPassword());
    }
}
