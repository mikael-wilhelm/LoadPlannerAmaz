package se.exjob.model;

public class UserNotFoundUser implements User {

    private String userName = "noUser";
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserNotFoundUser that = (UserNotFoundUser) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }


}
