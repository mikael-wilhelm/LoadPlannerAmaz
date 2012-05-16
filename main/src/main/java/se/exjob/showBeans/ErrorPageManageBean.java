package se.exjob.showBeans;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name="errorPageManageBean")
@RequestScoped
public class ErrorPageManageBean {
    public void redirect(){
        System.out.println("test");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://ec2-23-22-55-131.compute-1.amazonaws.com");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
