package cz.muni.fi.pa165.blablacar.springMvc.security;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = {"/user/*", "/drives/*", "/comment/*", "/cities/*", "/city/*"})
public class EnterFilter implements Filter {

    @Autowired
    private UserSession userSession;

    private WebApplicationContext appContext;

    
    final static Logger log = LoggerFactory.getLogger(EnterFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        appContext = WebApplicationContextUtils.
                getRequiredWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        userSession = appContext.getBean(UserSession.class);

        log.debug(userSession.getLogin() + userSession.getPassword());
        if (userSession.getUserId() == null) {
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
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1>You do not have permissions to access...</body></html>");
    }
    
    
    /*
    final static Logger log = LoggerFactory.getLogger(EnterFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        String logname = creds[0];
        String password = creds[1];

        //get Spring context and UserFacade from it
        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(UserFacade.class);
        UserDTO matchingUser = userFacade.findUserByLogin(logname);
        if(matchingUser==null) {
            log.warn("no user with login {}", logname);
            response401(response);
            return;
        }
        UserDTO uDTO = new UserDTO();
        uDTO.setId(matchingUser.getId());
        uDTO.setPassword(password);
//        if (!userFacade.isAdmin(matchingUser)) {
//            log.warn("user not admin {}", matchingUser);
//            response401(response);
//            return;
//        }
        if (!userFacade.authenticate(uDTO)) {
            log.warn("wrong credentials: user={} password={}", creds[0], creds[1]);
            response401(response);
            return;
        }
        request.setAttribute("authenticatedUser", matchingUser);
        chain.doFilter(request, response);
    }


    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }*/
}
