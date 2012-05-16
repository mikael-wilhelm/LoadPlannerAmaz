package se.exjob.showBeans;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ErrorPageManageBean {
    public void redirect(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
                fc.getApplication().getNavigationHandler();
        nav.performNavigation("http://ec2-23-22-55-131.compute-1.amazonaws.com");
    }
}
