package org.sunjoo.servlet.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ExportException;

/**
 * Created by sunjoo on 16/02/2017.
 */
@WebServlet(name = "ServletRequestDispatcher", urlPatterns = "/dispatcher/*")
public class ServletRequestDispatcher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test2.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("http://www.daum.net");
        try {
            rd.forward(request, response);
        } catch(Exception e){
            RequestDispatcher rd2 = request.getRequestDispatcher("/index.jsp");
            rd2.forward(request, response);
        }
    }
}
