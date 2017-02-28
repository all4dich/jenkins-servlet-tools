package org.sunjoo.servlet.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by sunjoo on 16/02/2017.
 */
@WebServlet("/servletcontext/*")
public class ServletGetContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext context = getServletContext();
        Enumeration<String> e = context.getInitParameterNames();

        String str = "";
        while (e.hasMoreElements()) {
            str = e.nextElement();
            out.print("<br> " + context.getInitParameter(str));
        }
        request.getSession().invalidate();
    }
}
