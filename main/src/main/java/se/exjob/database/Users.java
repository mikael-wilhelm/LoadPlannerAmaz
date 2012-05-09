package se.exjob.database;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.model.User;

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

    public User registerUser(String userName, String password) {
        User tempUser = new User(userName,password);
        if(!userList.containsValue(tempUser)){
            userList.put(userName, password);
            return tempUser;
        }
        else{
            return null;
        }
    }

    public User authenticate(String userName, String password) throws NoSuchUserNameException,PasswordException {
        User tempUser = new User(userName,userList.get(userName));
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
