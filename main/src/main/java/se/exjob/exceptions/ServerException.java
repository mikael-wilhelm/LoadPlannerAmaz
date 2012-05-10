package se.exjob.exceptions;

/**
 * Created by IntelliJ IDEA.
 * UserImpl: mikael.lof
 * Date: 2012-05-02
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class ServerException extends Exception {
    public ServerException(Throwable throwable){
        super(throwable);
    }
}
