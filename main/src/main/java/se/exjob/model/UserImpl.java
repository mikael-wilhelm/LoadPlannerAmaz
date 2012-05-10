package se.exjob.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserImpl implements User {
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    public UserImpl(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Id
    public String getUserName(){
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof UserImpl)) {return false; }

        UserImpl user = (UserImpl) o;

        if (!userName.equals(user.userName)) {return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }
}
