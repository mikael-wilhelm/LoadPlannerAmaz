package se.exjob.showBeans;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: mikael.lof
 * Date: 2012-05-16
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
public class ErrorPageManageBean {
    public void redirect(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)
                fc.getApplication().getNavigationHandler();
        nav.performNavigation("errorPage");
    }
}
