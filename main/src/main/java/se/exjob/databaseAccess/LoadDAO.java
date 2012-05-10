package se.exjob.databaseAccess;

import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import java.util.List;

public interface LoadDAO {
    public Load insertLoad(String content, String harbor,String destination) throws ServerException;
    public Load updateLoad(Load load) throws ServerException;
    public Load getLoad(int loadID) throws LoadNotFoundException, ServerException;
    public List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException;
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException;
    public List<Load> getAllLoads() throws ServerException;

}
