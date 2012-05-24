package se.exjob.databaseAccess;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;

public interface UserDAO {
    User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException, ServerException;
    void registerUser(String userName, String password) throws ServerException;
    void registerUser(User user) throws ServerException;
}
