package se.exjob.showBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@SuppressWarnings("UnusedDeclaration")
@ManagedBean(name="errorPageManageBean")
@RequestScoped
public class ErrorPageManageBean {
    private Logger logger = Logger.getLogger("ErrorPageManagBean");
    public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://ec2-184-73-16-97.compute-1.amazonaws.com");
        } catch (IOException e) {
            logger.log(SEVERE, e.getMessage());
        }
    }
}
