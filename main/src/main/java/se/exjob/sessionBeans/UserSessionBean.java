package se.exjob.sessionBeans;

import se.exjob.model.User;
import se.exjob.model.UserImpl;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name="userSessionBean")
@SessionScoped
public class UserSessionBean {

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void checkUser(){
        //FacesContext fc = FacesContext.getCurrentInstance();
        if(loggedInUser == null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("http://ec2-23-22-55-131.compute-1.amazonaws.com");
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            /*ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
                    fc.getApplication().getNavigationHandler();
            nav.performNavigation("errorPage"); */
        }
    }
}
