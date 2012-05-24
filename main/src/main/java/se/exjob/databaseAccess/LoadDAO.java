package se.exjob.databaseAccess;

import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;

import java.util.List;

public interface LoadDAO {
    Load insertLoad(String content, String harbor,String destination) throws ServerException;
    Load updateLoad(Load load) throws ServerException;
    Load getLoad(int loadID) throws LoadNotFoundException, ServerException;
    List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException;
    List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException;
    List<Load> getAllLoads() throws ServerException;

}
