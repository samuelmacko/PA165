package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.facade.CommentFacade;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController  {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;
    
    @Autowired
    private UserSession userSession;
    
    @Autowired
    private CommentFacade commentFacade;

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }
    
    @ModelAttribute(name="userSession")
    public UserSession addUserSession(){
        return userSession;
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        log.debug("user ocmments({})", commentFacade.getCommentsOfAuthor(id));
        model.addAttribute("comments", commentFacade.getCommentsOfAuthor(id));
        model.addAttribute("user", userFacade.findUserById(id));
        return "user/view";
    }
}
