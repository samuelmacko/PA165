package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class UserController  {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        return "home";
    }
}
