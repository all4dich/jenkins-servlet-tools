package org.sunjoo.servlet.filters;

import jenkins.security.NonSerializableSecurityContext;
import org.acegisecurity.Authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Print access log with username
 */
public class PrintAccessLog implements Filter {
    public void init(FilterConfig config)
            throws ServletException {
        // Get init parameter
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws java.io.IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        Principal principal = servletRequest.getUserPrincipal();
        NonSerializableSecurityContext securityContext = (NonSerializableSecurityContext) servletRequest.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Get the IP address of client machine.
            String ipAddress = request.getRemoteAddr();
            String url = ((HttpServletRequest) request).getRequestURI();
            // Log the IP address and current timestamp.
            String username = "anonymous";
            if (securityContext == null) {
                if (principal != null){
                    // When using container-based authentiaiton;
                    username = principal.getName();
                }
            } else {
                Authentication auth = securityContext.getAuthentication();
                username = auth.getName();
            }
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "ACCESSLOG " + ipAddress + " " + username + " " + url);
        }
    }

    public void destroy() {
      /* Called before the Filter instance is removed
      from service by the web container*/
    }
}
