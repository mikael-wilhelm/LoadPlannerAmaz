package se.exjob.showBeans;

import se.exjob.controller.Controller;
import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.model.UserImpl;
import se.exjob.sessionBeans.UserSessionBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logInBean")
@RequestScoped
public class LogInBean {
    private String userName;
    private String password;
    private Controller controller = new Controller();
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean loggedInUser;

    public String logIn(){
        String returnPage;
        FacesMessage doneMessage = null;
        try {
            User user = controller.authenticate(userName,password);
            loggedInUser.setLoggedInUser(user);
            returnPage= "loggedIn/showLoads.xhtml?faces-redirect=true";
            doneMessage = new FacesMessage("");
        } catch (NoSuchUserNameException e) {
            returnPage = "logIn.xhtml";
            doneMessage = new FacesMessage("There is no such UserImpl");
        } catch (PasswordException e) {
            returnPage = "logIn.xhtml";
            doneMessage = new FacesMessage("Password incorrect");
        } catch (ServerException e) {
            returnPage = "logIn.xhtml" ;
            doneMessage = new FacesMessage(e.getCause().getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null,doneMessage);
        return returnPage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setLoggedInUser(UserSessionBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
