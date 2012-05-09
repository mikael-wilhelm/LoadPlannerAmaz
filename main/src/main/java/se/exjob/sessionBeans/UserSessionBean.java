package se.exjob.sessionBeans;

import se.exjob.model.User;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
        FacesContext fc = FacesContext.getCurrentInstance();
        if(loggedInUser == null){
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
                    fc.getApplication().getNavigationHandler();
            nav.performNavigation("logIn");
        }
    }
}
