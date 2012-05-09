package se.exjob.showBeans;

import se.exjob.controller.Controller;
import se.exjob.exceptions.ServerException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registerUserManageBean")
@RequestScoped
public class RegisterUserManageBean {
    private String userName;
    private String password;
    private Controller controller = new Controller();

    public String register(){
        try {
            controller.registerUser(userName,password);
        } catch (ServerException ignored) {

        }
        return "logIn.xhtml";
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
}
