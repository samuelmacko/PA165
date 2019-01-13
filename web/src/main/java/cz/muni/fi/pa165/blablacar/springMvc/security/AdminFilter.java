package cz.muni.fi.pa165.blablacar.springMvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/list/all/*", "/comment/list/*"})
public class AdminFilter implements Filter {

    @Autowired
    private UserSession userSession;

    private WebApplicationContext appContext;

    final static Logger log = LoggerFactory.getLogger(AdminFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        appContext = WebApplicationContextUtils.
                getRequiredWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        userSession = appContext.getBean(UserSession.class);

        log.debug("log{}", userSession.getUser());
        if (userSession.getUser() == null || !userSession.getUser().getSuperUser()) {
            response401((HttpServletResponse) servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1>You do not have ADMIN permissions to access...</body></html>");
    }
}