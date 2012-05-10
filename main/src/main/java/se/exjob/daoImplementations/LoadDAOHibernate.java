package se.exjob.daoImplementations;

import se.exjob.databaseAccess.LoadDAO;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import java.util.List;

public class LoadDAOHibernate implements LoadDAO {
    @Override
    public Load insertLoad(String content, String harbor, String destination) throws ServerException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Load updateLoad(Load load) throws ServerException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Load getLoad(int loadID) throws LoadNotFoundException, ServerException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Load> getAllLoads() throws ServerException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
