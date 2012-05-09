package se.exjob.showBeans;

import se.exjob.controller.Controller;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name ="registerLoadsManageBean")
@RequestScoped
public class RegisterLoadsManageBean {

    private Controller controller = new Controller();
    private String tempContent;
    private String tempHarbor;
    private String tempDestination;
    private List<Load> loads = new ArrayList<Load>();
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public void registerLoad(){
        try {
            controller.insertLoad(tempContent, tempHarbor,tempDestination);
        } catch (Exception ignored) {

        }
    }

    public List<Load> getLoads() {
        try {
            loads = controller.getReservedLoads(loggedInUser.getLoggedInUser());
        } catch (LoadNotFoundException ignored) {

        } catch (ServerException e) {

        }
        return loads;
    }

    public String getTempHarbor() {
        return tempHarbor;
    }

    public void setTempHarbor(String tempHarbor) {
        this.tempHarbor = tempHarbor;
    }

    public String getTempContent() {
        return tempContent;
    }

    public void setTempContent(String tempContent) {
        this.tempContent = tempContent;
    }

    public String getTempDestination() {
        return tempDestination;
    }

    public void setTempDestination(String tempDestination) {
        this.tempDestination = tempDestination;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
