package se.exjob.model;

public interface User {
    String getUserName();
    String getPassword();
    boolean equals(Object o);
    int hashCode();
}
