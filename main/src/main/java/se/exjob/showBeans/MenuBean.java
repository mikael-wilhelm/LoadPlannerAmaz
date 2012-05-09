package se.exjob.showBeans;

import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name ="menuBean")
@RequestScoped
public class MenuBean {
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;


    public UserSessionBean getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
