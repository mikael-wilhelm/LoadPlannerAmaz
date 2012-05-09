package se.exjob.daoImplementations;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.database.Users;
import se.exjob.exceptions.PasswordException;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.model.User;

public class UserDAOHashTable implements UserDAO{

    @Override
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException {
        Users users = Users.getInstance();
        return users.authenticate(userName,password);
    }

    @Override
    public void registerUser(String userName, String password) {
        Users.getInstance().registerUser(userName,password);
    }
}
