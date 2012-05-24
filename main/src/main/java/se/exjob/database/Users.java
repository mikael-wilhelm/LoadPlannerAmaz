package se.exjob.database;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.model.UserImpl;

import java.util.HashMap;
import java.util.Map;

public final class Users {
    private Map<String, String> userList = new HashMap<String, String> ();
    private static Users users = new Users();

    private Users(){
        userList.put("foo","bar");
    }

    public static Users getInstance() {
        return users;
    }

    public UserImpl registerUser(String userName, String password) {
        UserImpl tempUser = new UserImpl(userName,password);
        if(!userList.containsValue(tempUser.getUserName())){
            userList.put(userName, password);
            return tempUser;
        }
        else{
            return null;
        }
    }

    public UserImpl authenticate(String userName, String password) throws NoSuchUserNameException,PasswordException {
        UserImpl tempUser = new UserImpl(userName,userList.get(userName));
        if(tempUser.getPassword() == null){
            throw new NoSuchUserNameException();
        }
        else if(tempUser.getPassword().equals(password)){
            return tempUser;
        }
        else{
            throw new PasswordException();
        }
    }

    public void clearAllUsers() {
        userList.clear();
    }
}
