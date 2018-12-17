package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.springMvc.security.Right;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
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
        } catch (IllegalArgumentException e) {
            // empty login || not exist
            redirectAttributes.addFlashAttribute("alert_danger", "User not found");
            return "redirect:/";
        }

        if (foundUser == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "User not found");
            return "redirect:/";
        }

        if (!foundUser.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("alert_danger", "Invalid credentials");
            return "redirect:/";
        }

        userSession.setUserIsLoggedIn(true);
        userSession.setUser(foundUser);

        request.getSession(true).setAttribute("user", userSession);
        redirectAttributes.addFlashAttribute("alert_success", "Hello " + foundUser.getFirstName() + "! You have been successfully logged in");

        return "redirect:/";
    }

    @ModelAttribute(name = "userSession")
    public UserSession addUserSession(){
        return userSession;
    }
}