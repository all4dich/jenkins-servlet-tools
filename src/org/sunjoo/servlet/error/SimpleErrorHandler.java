package org.sunjoo.servlet.error;

/**
 * Created by sunjoo on 07/02/2017.
 */

import javax.servlet.annotation.WebServlet;

import jenkins.security.NonSerializableSecurityContext;
import org.acegisecurity.Authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Simple Error Handler",
        description = "Simple Error handler",
        urlPatterns = "/SimpleErrorHandler"
)
public class SimpleErrorHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String errorPageHtml = "<html>\n" +
                "    <head>\n" +
                "        <!-- External dependencies -->\n" +
                "        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js\"></script>\n" +
                "        <script src=\"http://cdnjs.cloudflare.com/ajax/libs/sinon.js/1.15.4/sinon.js\"></script>\n" +
                "        <script src=\"//aui-cdn.atlassian.com/aui-adg/6.0.6/js/aui.js\"></script>\n" +
                "        <script src=\"//aui-cdn.atlassian.com/aui-adg/6.0.6/js/aui-experimental.js\"></script>\n" +
                "        <script src=\"//aui-cdn.atlassian.com/aui-adg/6.0.6/js/aui-datepicker.js\"></script>\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"//aui-cdn.atlassian.com/aui-adg/6.0.6/css/aui.css\"/>\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"//aui-cdn.atlassian.com/aui-adg/6.0.6/css/aui-experimental.css\"/>\n" +
                "        <!-- / External dependencies -->\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div class=\"aui-message aui-message-error\">\n" +
                "            <p class=\"title\">\n" +
                "                <strong>_ERROR_TITLE_</strong>\n" +
                "            </p>\n" +
                "            <p>_ERROR_MESSAGE_</p>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";
        NonSerializableSecurityContext securityContext = (NonSerializableSecurityContext) request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
        String username = "anonymous";
        try {
            Authentication auth = securityContext.getAuthentication();
            username = auth.getName();
        } catch (NullPointerException e) {
            username = "anonymous";
        }

        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Authorized username= " + username);
        String errorString = "";
        String statusCode = ((Integer) request.getAttribute("javax.servlet.error.status_code")).toString();
        String errorRequestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String errorRequestUriEncodded = URLEncoder.encode(errorRequestUri, "UTF-8");
        String requestUri = request.getRequestURI();
        String requestUrl = request.getRequestURL().toString();

        String requestHost = requestUrl.replaceAll(requestUri, request.getContextPath());
        String loginUrl = requestHost + "/login?from=" + errorRequestUriEncodded;
        String loginUri = request.getContextPath() + "/login?from=" + errorRequestUriEncodded;
        String originalRequestUrl = requestUrl.replaceAll(requestUri, errorRequestUri);
        originalRequestUrl = "<a href='" + originalRequestUrl + "'>" + originalRequestUrl + "</a>";

        errorString = "Username = " + username;
        errorString += "<br/> You can't to go to " + originalRequestUrl + ". You're not authorized for it or that page doesn't exist.\n";
        errorString += " " + username;
        errorPageHtml = errorPageHtml.replaceAll("_ERROR_TITLE_", "Error Code : " + statusCode);
        errorPageHtml = errorPageHtml.replaceAll("_ERROR_MESSAGE_", errorString);
        if (username.equals("anonymous")) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Login Url= " + loginUri);
            response.sendRedirect(loginUri);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(errorPageHtml);
            out.flush();

        }
    }
}
