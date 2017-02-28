package org.sunjoo.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by sunjoo on 16/02/2017.
 */
@WebServlet(name = "ServletCookieSetter", urlPatterns = "/cookie/*")
public class ServletCookieSetter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Cookie cookie = new Cookie("tester", "sunjoo");
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        String errorPageHtml = "<b>Cookie handler</b>";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(new Date(request.getSession().getLastAccessedTime()));
        out.flush();
        */
        // Set response content type
        response.setContentType("text/html");

        // New location to be redirected
        String site = new String("http://cerberus.lge.com/");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
}
