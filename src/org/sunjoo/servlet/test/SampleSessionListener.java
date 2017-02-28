package org.sunjoo.servlet.test;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * Created by sunjoo on 16/02/2017.
 */
//@WebListener("Web sesison listener")
public class SampleSessionListener implements HttpSessionListener{
    HttpSession ctx = null;
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        ctx=e.getSession();
        System.out.println("DEBUG: " + ctx.getId() + " is created:");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        System.out.println("DEBUG: " + ctx.getId() + " is destroyed:");
    }

}
