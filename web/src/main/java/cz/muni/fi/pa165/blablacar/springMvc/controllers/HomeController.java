package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.springMvc.security.Right;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private UserSession userSession;

    @Autowired
    private UserFacade userFacade;

    final static Logger log = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping
    public String home(Model model) {
        return "home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login (@ModelAttribute("userSession") UserSession userSession, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String login = userSession.getLogin();
        String password = userSession.getPassword();

        UserDTO foundUser = null;
        try {
            foundUser = userFacade.findUserByLogin(login);
        } catch (Exception e) {
            // empty login || not exist
            redirectAttributes.addFlashAttribute("alert_danger", "User not found");
            return "redirect:/";
        }

        if (foundUser == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "User not found");
            return "redirect:/";
        }
        foundUser.setPassword(password);
        if (!userFacade.authenticate(foundUser)) {
            redirectAttributes.addFlashAttribute("alert_danger", "Invalid credentials");
            return "redirect:/";
        }

        log.debug("log{}", foundUser);

        //foundUser.setSuperUser(userFacade.isAdmin(foundUser));
        /*userSession.setUserIsLoggedIn(true);
        userSession.setUser(foundUser);
        */
        foundUser.setSuperUser(foundUser.getLogin().equals("wizzard1"));
        userSession.logInUser(foundUser.getId(), foundUser);
        request.getSession(true).setAttribute("user", userSession);
        redirectAttributes.addFlashAttribute("alert_success", "Hello " + foundUser.getFirstName() + "! You have been successfully logged in");

        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String list(Model model) {
        userSession.logoutUser();
        return "redirect:/";
    }
    
    @ModelAttribute(name = "userSession")
    public UserSession addUserSession(){
        return userSession;
    }
}