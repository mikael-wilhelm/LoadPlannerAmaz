package se.exjob.sessionBeans;

import se.exjob.model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name="userSessionBean")
@SessionScoped
public class UserSessionBean {
    private Logger logger = Logger.getLogger("UserSessionBean");
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.setAttribute("start", System.currentTimeMillis());

        this.loggedInUser = loggedInUser;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void checkUser(){
        if(loggedInUser == null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ec2-184-73-16-97.compute-1.amazonaws.com");
            } catch (IOException e) {
                logger.log(Level.SEVERE,e.getMessage());
            }
        }
    }
}
