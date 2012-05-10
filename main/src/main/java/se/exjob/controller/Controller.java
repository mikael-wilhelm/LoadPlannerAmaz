package se.exjob.controller;

import se.exjob.daoImplementations.LoadDAOPostgres;
import se.exjob.daoImplementations.UserDAOPostgres;
import se.exjob.exceptions.*;
import se.exjob.databaseAccess.LoadDAO;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.model.Load;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import java.util.List;

public class Controller {
    private LoadDAO loadDAO = new LoadDAOPostgres();
    private UserDAO userDAO = new UserDAOPostgres();

    public Load insertLoad(String content, String harbor, String destination) throws ServerException {
        return loadDAO.insertLoad(content, harbor, destination);
    }

    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException {
        return loadDAO.getNotReservedLoadsFilteredByHarbor(s);
    }

    public List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException {
        return loadDAO.getReservedLoads(user);
    }

    public void reserveLoad(int loadID, User user) throws ServerException,LoadAlreadyReservedException, LoadNotFoundException {
        Load load = loadDAO.getLoad(loadID);
        if(load.getReserved()){
            throw new LoadAlreadyReservedException();
        }
        else{
            reserveLoad(load,user);
        }
    }

    public void reserveLoad(Load load, User user) throws ServerException {
        load.setReserved(true);
        load.setReservedBy(user);
        loadDAO.updateLoad(load);
    }

    public List<Load> getAllLoads() throws ServerException {
        return loadDAO.getAllLoads();
    }

    public void setDAO (LoadDAO dao){
        loadDAO = dao;
    }

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException, ServerException {
        return userDAO.authenticate(userName,password);
    }

    public void registerUser(String userName, String password) throws ServerException {
        userDAO.registerUser(userName,password);
    }
}
