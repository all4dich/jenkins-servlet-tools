package org.sunjoo.servlet.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by sunjoo on 16/02/2017.
 */
@WebListener
public class SampleContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event){
        System.out.println("DEBUG: " + event.toString());
        System.out.println("DEBUG: " + event.getServletContext().getContextPath() + " is initialized.");
    }
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("DEBUG: " + event.toString());
        System.out.println("DEBUG: " + event.getServletContext().getContextPath() + " is destroyed.");
    }
    /*
    @WebListener
    public class SampleContextListenerInner implements  ServletContextListener{
        public void contextInitialized(ServletContextEvent event){
            System.out.println("DEBUG: Inner: " + event.toString());
            System.out.println("DEBUG: Inner: " + event.getServletContext().getContextPath() + " is initialized.");
        }
        public void contextDestroyed(ServletContextEvent event) {
            System.out.println("DEBUG: Inner: " + event.toString());
            System.out.println("DEBUG: Inner: " + event.getServletContext().getContextPath() + " is destroyed.");
        }
    }
    */
}
