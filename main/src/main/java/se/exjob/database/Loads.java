package se.exjob.database;


import se.exjob.model.Load;

import java.util.HashMap;
import java.util.Map;

public final class Loads {
    private static Loads loads = new Loads();
    private Map<Integer,Load> loadList = new HashMap<Integer,Load>();
    private Loads(){

    }
    public Load insertLoad(String content, String harbor, String destination){
        int newKey =  loadList.size();
        Load load = new Load( newKey,content, harbor, destination) ;
        loadList.put(newKey,load);
        return load;

    }

    public Map<Integer,Load> getLoads(){
        return loadList;
    }

    public Load updateLoad(Load load){
        int loadID = load.getId();
        deleteLoad(load.getId());
        loadList.put(loadID,load);
        return load;
    }

    public Load getLoad(int loadID){
        return loadList.get(loadID);
    }

    public void deleteLoad(int loadID){
        loadList.remove(loadID);
    }

    public static Loads getInstance(){
        return loads;
    }

    public void clearAllEntries(){
        loadList.clear();
    }
}
