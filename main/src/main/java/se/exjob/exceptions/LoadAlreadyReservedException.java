package se.exjob.exceptions;

public class LoadAlreadyReservedException extends Exception {
    public LoadAlreadyReservedException(String message){
        super(message);
    }
}
