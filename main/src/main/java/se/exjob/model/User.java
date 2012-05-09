package se.exjob.model;


public class User {
    private String userName;
    private String password;
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof User)) {return false; }

        User user = (User) o;

        if (!userName.equals(user.userName)) {return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

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
}
