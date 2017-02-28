package org.sunjoo.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sunjoo on 15/02/2017.
 */
@WebServlet(name = "PrintMessage", urlPatterns = "/print/*")
public class PrintMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorPageHtml = "<b>Hello, world</b>";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(errorPageHtml);
        out.flush();
    }
}
