package se.exjob.showBeans;
import se.exjob.controller.Controller;
import se.exjob.exceptions.LoadAlreadyReservedException;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;
import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration") // JSF-bean
@ManagedBean
@SessionScoped
public class ShowLoadsManageBean {

    private Controller controller = new Controller();
    private String filter="";
    private List<Load> notReservedLoads = new ArrayList<Load>();
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public void reserveLoad(Load load){
        FacesMessage doneMessage = tryToReserveLoad(load);
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        currentInstance.addMessage(null, doneMessage);
        populateNotReservedLoads();
    }

    public void populateNotReservedLoads(){
        try {
            notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor("");
        } catch (ServerException ignored) {
        } catch (LoadNotFoundException ignored) {
        }
    }

    public List<Load> getNotReservedLoads() {
        return notReservedLoads;
    }


    public void setNotReservedLoads(List<Load> notReservedLoads) {
        this.notReservedLoads = notReservedLoads;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public UserSessionBean getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private FacesMessage tryToReserveLoad(Load load) {
        FacesMessage doneMessage;
        try{
            int id = load.getId();
            User loggedInUser1 = loggedInUser.getLoggedInUser();
            controller.reserveLoad(id, loggedInUser1);
            doneMessage = new FacesMessage("Load reserved"); // todo proper message
        }
        catch (ServerException se){
            doneMessage = new FacesMessage("Server Side Error");
        }
        catch(LoadNotFoundException e){
            doneMessage = new FacesMessage("The Load couldn't be found");
        }
        catch (LoadAlreadyReservedException e) {
            doneMessage = new FacesMessage("The Load is already reserved");
        }

        return doneMessage;
    }
}
