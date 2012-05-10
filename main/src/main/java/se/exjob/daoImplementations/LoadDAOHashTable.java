package se.exjob.daoImplementations;


import se.exjob.databaseAccess.LoadDAO;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.model.Load;
import se.exjob.database.Loads;
import se.exjob.model.User;
import se.exjob.model.UserImpl;

import java.util.ArrayList;
import java.util.List;

public  class LoadDAOHashTable implements LoadDAO {

    public Load insertLoad(String content, String harbor,String destination){
        return Loads.getInstance().insertLoad(content, harbor,destination);
    }

    @Override
    public List<Load> getNotReservedLoadsFilteredByHarbor(String matcher) {
        Loads tempLoads = Loads.getInstance();
        List<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values()){
            if(isLoadMatching(load,matcher)){
                loadsMatching.add(load);
            }
        }
        return loadsMatching;
    }

    private boolean isLoadMatching(Load load,String matcher){
        return !load.getReserved() && (matcher.equals(load.getHarbor())||matcher.equals(""));
    }

    public List<Load> getAllLoads() {
        Loads tempLoads = Loads.getInstance();
        List<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values()) {
            loadsMatching.add(load);
        }
        return loadsMatching;
    }

    public List<Load> getReservedLoads(User user) {
        Loads tempLoads = Loads.getInstance();
        List<Load> loadsMatching = new ArrayList<Load>();
        for(Load load : tempLoads.getLoads().values()) {
            if(load.getReserved() && load.getReservedBy().equals(user)){
                loadsMatching.add(load);
            }
        }
        return loadsMatching;
    }

    public Load updateLoad(Load load) {
        Loads.getInstance().updateLoad(load);
        return load;
    }

    public Load getLoad(int loadID) throws LoadNotFoundException {
        Load load = Loads.getInstance().getLoad(loadID);
        if(load != null){
            return load;
        }
        else {
            throw new LoadNotFoundException();
        }
    }
}
