package se.exjob.showBeans;

import se.exjob.controller.Controller;
import se.exjob.exceptions.LoadAlreadyReservedException;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="showLoadsManageBean")
@RequestScoped
public class ShowLoadsManageBean {

    private List<Load> notReservedLoads = new ArrayList<Load>();
    private Controller controller = new Controller();
    private String filter="";
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;
    private HtmlDataTable loadTable = new HtmlDataTable();

    public void reserveLoad(){
        FacesMessage doneMessage = null;
        try{
            controller.reserveLoad(((Load) loadTable.getRowData()).getId(),loggedInUser.getLoggedInUser());
            doneMessage = new FacesMessage("Load reserved");
        }
        catch (ServerException se){
            doneMessage = new FacesMessage("Server Side Error");
        }
        catch(LoadNotFoundException ignored){
        }
        catch (LoadAlreadyReservedException e) {
            doneMessage = new FacesMessage("The Load is already reserved");
        }
        FacesContext.getCurrentInstance().addMessage(null,doneMessage);
    }

    public String getFilter() {
        return filter;
    }

    public List<Load> getNotReservedLoads() {

        try {
            notReservedLoads = controller.getNotReservedLoadsFilteredByHarbor(filter);
        } catch (ServerException ignored) {
        } catch (LoadNotFoundException ignored) {
        }
        return notReservedLoads;
    }

    public void setNotReservedLoads(List<Load> notReservedLoads) {
        this.notReservedLoads = notReservedLoads;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public HtmlDataTable getLoadTable() {
        return loadTable;
    }

    public void setLoadTable(HtmlDataTable loadTable) {
        this.loadTable = loadTable;
    }
}
